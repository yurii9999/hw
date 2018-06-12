package group144.kuzmin;

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
        setDisableAll(true);
        try {
            game = new Client(8888);
        } catch (IOException e) {
            System.out.println("Run Server first");
            return;
        }
        setDisableAll(false);
        System.out.println("nashel");
        int[] opponentTurn;

        try { opponentTurn = game.opponentTurn();
        } catch (IOException e) {
            System.out.println("Lost connection");
            return;
        }

        Button button = super.getButtonByLocation(opponentTurn[0], opponentTurn[1]);
        me = "O";
        opponent = "X";
        button.setText(opponent);
    }
}
