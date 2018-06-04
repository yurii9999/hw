package group144.kuzmin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    Label text;

    @FXML
    Button button_0_0;
    @FXML
    Button button_0_1;
    @FXML
    Button button_0_2;

    @FXML
    Button button_1_0;
    @FXML
    Button button_1_1;
    @FXML
    Button button_1_2;

    @FXML
    Button button_2_0;
    @FXML
    Button button_2_1;
    @FXML
    Button button_2_2;

    Game game = new Game();
    public void clicked_0_0(ActionEvent actionEvent) {
        if (game.turn(0, 0)) {
            button_0_0.setText(game.getLastTurnedPlayersName());
            updateState();
        }
    }

    public void clicked_0_1(ActionEvent actionEvent) {
        if (game.turn(0, 1)) {
            button_0_1.setText(game.getLastTurnedPlayersName());
            updateState();
        }
    }

    public void clicked_0_2(ActionEvent actionEvent) {
        if (game.turn(0, 2)) {
            button_0_2.setText(game.getLastTurnedPlayersName());
            updateState();
        }
    }

    public void clicked_1_0(ActionEvent actionEvent) {
        if (game.turn(1, 0)) {
            button_1_0.setText(game.getLastTurnedPlayersName());
            updateState();
        }
    }

    public void clicked_1_1(ActionEvent actionEvent) {
        if (game.turn(1, 1)) {
            button_1_1.setText(game.getLastTurnedPlayersName());
            updateState();
        }
    }

    public void clicked_1_2(ActionEvent actionEvent) {
        if (game.turn(1, 2)) {
            button_1_2.setText(game.getLastTurnedPlayersName());
            updateState();
        }
    }

    public void clicked_2_0(ActionEvent actionEvent) {
        if (game.turn(2, 0)) {
            button_2_0.setText(game.getLastTurnedPlayersName());
            updateState();
        }
    }

    public void clicked_2_1(ActionEvent actionEvent) {
        if (game.turn(2, 1)) {
            button_2_1.setText(game.getLastTurnedPlayersName());
            updateState();
        }
    }

    public void clicked_2_2(ActionEvent actionEvent) {
        if (game.turn(2, 2)) {
            button_2_2.setText(game.getLastTurnedPlayersName());
            updateState();
        }
    }

    public void clicked_restart(ActionEvent actionEvent) {
        game = new Game();

        setDisableAll(false);

        button_0_0.setText("  ");
        button_0_1.setText("  ");
        button_0_2.setText("  ");

        button_1_0.setText("  ");
        button_1_1.setText("  ");
        button_1_2.setText("  ");

        button_2_0.setText("  ");
        button_2_1.setText("  ");
        button_2_2.setText("  ");

        text.setText("");
    }

    private void updateState() {
        text.setText("State: " + game.state());
        if (game.state() != "PLAYING") {
            setDisableAll(true);
        }
    }

    private void setDisableAll(boolean b) {
        button_0_0.setDisable(b);
        button_0_1.setDisable(b);
        button_0_2.setDisable(b);

        button_1_0.setDisable(b);
        button_1_1.setDisable(b);
        button_1_2.setDisable(b);

        button_2_0.setDisable(b);
        button_2_1.setDisable(b);
        button_2_2.setDisable(b);
    }
}
