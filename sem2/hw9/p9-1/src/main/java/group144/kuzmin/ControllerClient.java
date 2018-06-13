package group144.kuzmin;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerClient extends Controller implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        me = "O";
        opponent = "X";
        setDisableAll(true);
        Runnable waitConnection = () -> {
            try {
                game = new Client(8888);
                System.out.println("Game is on");

                int[] opponentTurn;
                opponentTurn = game.opponentTurn();
                Platform.runLater(() -> {
                        Button button = super.getButtonByLocation(opponentTurn[0], opponentTurn[1]);
                        button.setText(opponent);
                        setDisableAll(false);
                        updateState();
                });
            } catch (IOException e) {
                System.out.println("Run Server first");
                return;
            }
        };
        Thread wait = new Thread(waitConnection);
        wait.start();

    }
}
