package g244.kuzmin;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Collection;
import java.util.LinkedHashSet;

public class Client extends Application {
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage theStage) throws Exception {
        theStage.setTitle("Tanks client");
        Group root = new Group();
        Canvas canvas = new Canvas(300, 300);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Scene scene = new Scene(root);
        theStage.setScene(scene);
        theStage.show();


        Collection<String> codes = new LinkedHashSet<>();

        scene.setOnKeyPressed(event -> {
            String code = event.getCode().toString();
            codes.add(code);
        });

        scene.setOnKeyReleased(event -> {
            String code = event.getCode().toString();
            codes.remove(code);
        });


        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(4444));


            DataWriterReader dwr = new DataWriterReader(socketChannel);
            ByteBuffer data = dwr.read();
            ClientWorld cw = new ClientWorld(data);
            cw.draw(gc);

            new AnimationTimer()
            {
                public void handle(long currentNanoTime)
                {
                    ByteBuffer updateData = null;
                    try {
                        updateData = dwr.read();
                        cw.update(updateData);
                        cw.draw(gc);
                    } catch (Exception e) {
                        System.out.println("Cannot read(current state)");
                        this.stop();
                    }

                    try {
                        dwr.write(KeyCodesEncoder.encode(codes));
                    } catch (Exception e) {
                        System.out.println("Cannon write(codes)");
                        this.stop();
                    }
                }
            }.start();
        } catch (IOException e) {
            System.out.println("Run server first");
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setTextBaseline(VPos.CENTER);
            gc.fillText(
                    "Run server first",
                    Math.round(canvas.getWidth()  / 2),
                    Math.round(canvas.getHeight() / 2)
            );
        }
    }
}
