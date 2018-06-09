package group144.kuzmin;

import com.sun.xml.internal.ws.util.ByteArrayBuffer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("O X O X O X");
        primaryStage.setScene(new Scene(root, 150, 200));
        primaryStage.show();
        primaryStage.setMinWidth(100);
        primaryStage.setMinHeight(170);
        primaryStage.setMaxWidth(300);
        primaryStage.setMaxHeight(300);
    }


    public static void main(String[] args) {
//        launch(args);
        Integer a = 1000;
//        System.out.println(Integer.valueOf(a.byteValue()));
        IntBuffer intBuffer = IntBuffer.allocate(100);
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);

    }
}
