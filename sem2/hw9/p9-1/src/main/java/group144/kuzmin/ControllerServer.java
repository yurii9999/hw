package group144.kuzmin;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerServer extends Controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDisableAll(true);
        System.out.println("Server is on, waiting client");
        try {
            game = new Server(8888);
        } catch (IOException e) {
            System.out.println("Incorrect port");
        }

        setDisableAll(false);
        System.out.println("Connected, game is on");
        me = "X";
        opponent = "O";
    }
}
