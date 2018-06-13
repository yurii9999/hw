package group144.kuzmin;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Controller {
    @FXML
    Label text;
    String me;
    String opponent;

    Adapter game;
    public void clicked(ActionEvent actionEvent) throws IOException {
        Button button = (Button) actionEvent.getSource();
        int[] coords = getLocation(button);
        if (game.turn(coords[0], coords[1])) {
            button.setText(me);
            setDisableAll(true);

            Runnable waitOpponent = () -> {
                try {
                    int[] location;
                    location = game.opponentTurn();
                    Button opponentTurn = getButtonByLocation(location[0], location[1]);
                    Platform.runLater(() -> {
                            opponentTurn.setText(opponent);
                            setDisableAll(false);
                            updateState();
                    });
                } catch (IOException e) {
                    System.out.println("Lost connection");
                    return;
                }
            };

            Thread thread = new Thread(waitOpponent);
            thread.start();
        }

        if (!game.state().equals("PLAYING"))
            button.setText(me);

        updateState();
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

    protected void updateState() {
        text.setText("State: " + game.state());
        if (game.state() != "PLAYING") {
            setDisableAll(true);
        }
    }

    protected void setDisableAll(boolean b) {
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

    protected Button getButtonByLocation(int row, int collum) {
        switch (row) {
            case 0:
                switch (collum) {
                    case 0: return button_0_0;
                    case 1: return button_0_1;
                    case 2: return button_0_2;
                }
            case 1:
                switch (collum) {
                    case 0: return button_1_0;
                    case 1: return button_1_1;
                    case 2: return button_1_2;
                }
            case 2:
                switch (collum) {
                    case 0: return button_2_0;
                    case 1: return button_2_1;
                    case 2: return button_2_2;
                }

            default: return null;
        }
    }
}
