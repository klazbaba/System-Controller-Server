package system_controller_server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLController implements Initializable {

    @FXML
    private Label ipLabel, ipValue;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ipLabel.setText("IP Address: ");
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("google.com", 80));
            ipValue.setText(socket.getLocalAddress().toString().replace("/", ""));
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
