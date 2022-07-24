package system_controller_server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FXMLController implements Initializable {

    @FXML
    private Label ipLabel, ipValue, portLabel, portValue;
    @FXML
    private Button button;
    private Socket socket;
    private ServerSocket server;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ipLabel.setText("IP Address: ");
        ipValue.setText("000.000.000.000");
        portLabel.setText("Port: ");
        portValue.setText("0000");
        button.setText("Start");
    }

    @FXML
    private void handleButtonPress() {
        if (button.getText().equals("Start")) {
            try {
                socket = new Socket();
                // This will allow us get machine's ip address that can be used for public communications
                socket.connect(new InetSocketAddress("google.com", 80));
                ipValue.setText(socket.getLocalAddress().toString().replace("/", ""));
                server = new ServerSocket(0);
                portValue.setText(String.valueOf(server.getLocalPort()));
                button.setText("Stop");
                button.setStyle("-fx-background-color: red;");
                new Thread(() -> {
                    try {
                        server.accept();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }).start();
            } catch (IOException ex) {
                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (button.getText().equals("Stop")) {
            try {
                socket.close();
                server.close();
                ipValue.setText("000.000.000.000");
                portValue.setText("0000");
                button.setText("Start");
                button.setStyle("-fx-background-color: green;");
            } catch (IOException ex) {
                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
