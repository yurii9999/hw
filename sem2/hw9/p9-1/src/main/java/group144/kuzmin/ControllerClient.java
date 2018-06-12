package group144.kuzmin;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;

public class ControllerClient extends Controller {
    public ControllerClient() throws IOException {
        try {
            game = new Client(8888);
        } catch (IOException e) {
            System.out.println("Run Server first");
            return;
        }
        System.out.println("nashel");
        int[] opponentTurn = game.opponentTurn();
        Button button = super.getButtonByLocation(opponentTurn[0], opponentTurn[1]);
        me = "O";
        opponent = "X";
        // что то придумать с начальным состоянием хз пока что как это чинить
//        button.setText(opponent);
    }
}
