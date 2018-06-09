package group144.kuzmin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.util.function.Consumer;
import java.util.function.Function;

public class Controller {
    @FXML
    Label text;

    Game game = new Game();
    public void clicked(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        int[] coords = getLocation(button);
        if (game.turn(coords[0], coords[1])) {
            button.setText(game.getLastTurnedPlayersName());
            updateState();
        }
    }

    public void clicked_restart(ActionEvent actionEvent) {
        game = new Game();

        setDisableAll(false);
        forAllButtons(button -> button.setText("  "));

        text.setText("");
    }

    private int[] getLocation(Button button) {
        final int FROM_CHAR_TO_INT = '0';
        String location = button.getId();
        if (location.length() < 10)
            return new int[] {0, 0};

        int[] coords = {location.charAt(7) - FROM_CHAR_TO_INT, location.charAt(9) - FROM_CHAR_TO_INT};

        if (coords[0] < 0 || coords[1] < 0)
            return new int[]{ 0, 0};

        return coords;
    }

    private void updateState() {
        text.setText("State: " + game.state());
        if (game.state() != "PLAYING") {
            setDisableAll(true);
        }
    }

    private void setDisableAll(boolean b) {
        forAllButtons(button -> button.setDisable(b));
    }

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

    private void forAllButtons(Consumer<Button> consumer) {
        consumer.accept(button_0_0);
        consumer.accept(button_0_1);
        consumer.accept(button_0_2);

        consumer.accept(button_1_0);
        consumer.accept(button_1_1);
        consumer.accept(button_1_2);

        consumer.accept(button_2_0);
        consumer.accept(button_2_1);
        consumer.accept(button_2_2);
    }
}
