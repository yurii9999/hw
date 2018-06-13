package group144.kuzmin;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerServer extends Controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Server is on, waiting client");
        Runnable waitConnection = () -> {
            try {
                game = new Server(8888);
                setDisableAll(false);
                System.out.println("Connected, game is on");
            } catch (IOException e) {
                System.out.println("Incorrect port");
                return;
            }
        };

        Thread wait = new Thread(waitConnection);
        setDisableAll(true);
        wait.start();

        me = "X";
        opponent = "O";
    }
}
