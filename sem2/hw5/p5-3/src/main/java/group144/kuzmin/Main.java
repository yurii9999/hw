package group144.kuzmin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root, 200, 230));
        primaryStage.show();
        primaryStage.setMaxWidth(300);
        primaryStage.setMaxHeight(300);
        primaryStage.setMinWidth(100);
        primaryStage.setMinHeight(150);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
