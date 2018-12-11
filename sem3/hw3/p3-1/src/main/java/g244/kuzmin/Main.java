package g244.kuzmin;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.LinkedHashSet;

public class Main extends Application {
    @Override
    public void start(Stage theStage){
        theStage.setTitle( "tanks" );

        final int width = 300;
        final int height = 300;

        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
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

        theStage.show();
        Player tank = new Player();
        World world = new World(width, height);
        world.add(tank);

        world.add(new PeacefulEnemy(10.0, Color.RED));
        world.add(new PeacefulEnemy(10.0, Color.GREEN));
        world.add(new PeacefulEnemy(10.0, Color.BLUE));
        world.add(new PeacefulEnemy(10.0, Color.YELLOW));

        final long[] lastNanoTime = {System.nanoTime()};

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double elapsedTime = (currentNanoTime - lastNanoTime[0]) / 1000000000.0;
                lastNanoTime[0] = currentNanoTime;

                tank.control(codes);
                world.update(elapsedTime);
                world.draw(gc);
            }
        }.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
