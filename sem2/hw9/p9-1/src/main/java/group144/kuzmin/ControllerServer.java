package group144.kuzmin;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ControllerServer extends Controller {
    public ControllerServer() throws IOException {
        System.out.println("Server is on, waiting client");
        game = new Server(8888);
        System.out.println("Connected, game is on");
        me = "X";
        opponent = "O";
    }
}
