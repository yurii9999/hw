import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

public class Controller {
    @FXML
    ProgressBar progressBar;
    @FXML
    Slider slider;

    public void dragged(MouseEvent mouseEvent) {
        progressBar.setProgress(slider.getValue() / 100);
    }
}
