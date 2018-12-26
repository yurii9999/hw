package g244.kuzmin;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Collection;
import java.util.LinkedHashSet;

public class Server extends Application {
    @Override
    public void start(Stage theStage) throws IOException {
        theStage.setTitle( "Tanks server" );

        final int width = 300;
        final int height = 300;

        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        System.out.println("Server is on, waiting for client");

        Scene scene = new Scene(root);
        theStage.setScene(scene);
        Collection<String> codes = new LinkedHashSet<>();

        scene.setOnKeyPressed(event -> {
            String code = event.getCode().toString();
            codes.add(code);
        });

        scene.setOnKeyReleased(event -> {
            String code = event.getCode().toString();
            codes.remove(code);
        });

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        int port = 4444;
        serverSocketChannel.bind(new InetSocketAddress(port));
        SocketChannel socketChannel = serverSocketChannel.accept();

        theStage.show();

        Player tank = new Player(30);
        Player enemy = new Player(230);
        World world = new World(width, height);
        world.add(tank);
        world.add(enemy);

        DataWriterReader dwr = new DataWriterReader(socketChannel);

        ByteBuffer data = world.encodeGround();
        dwr.write(data);

        final long[] lastNanoTime = {System.nanoTime()};

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double elapsedTime = (currentNanoTime - lastNanoTime[0]) / 1000000000.0;
                lastNanoTime[0] = currentNanoTime;

                tank.control(codes);
                world.update(elapsedTime);

                try {
                    ByteBuffer updateData = world.getState();
                    DataWriterReader.write(socketChannel, updateData);
                } catch (Exception e) {
                    System.out.println("Cannot write(current state)");
                }
                ByteBuffer enemysCodesBuffer = null;
                try {
                    enemysCodesBuffer = dwr.read();
                    Collection<String> enemysCodes = KeyCodesEncoder.decode(enemysCodesBuffer);
                    enemy.control(enemysCodes);
                } catch (Exception e) {
                    System.out.println("Cannot read(codes)");
                }

                world.draw(gc);
            }
        }.start();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
