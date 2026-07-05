package bank.controller.fx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class AccountServicesFormController implements Initializable {

    @FXML
    private TextField balanceTextField,accountTextField;

    @FXML
    private Button withdrawButton,transferButton,transactionButton,backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("AccountServicesFormController loaded");
        withdrawButton.setOnAction(event -> {});
        transferButton.setOnAction(event -> {
            try {
                Scene scene = new Scene(
                        FXMLLoader.load(getClass().getResource("/view/Transfer.fxml"))
                );
            } catch (IOException e) {
//                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password", ButtonType.OK);
//                    alert.show();
                throw new RuntimeException(e);
            }
        });
        transactionButton.setOnAction(event -> {
            try {
                Scene scene = new Scene(
                        FXMLLoader.load(getClass().getResource("/view/Transaction.fxml"))
                );
            } catch (IOException e) {
//                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password", ButtonType.OK);
//                    alert.show();
                throw new RuntimeException(e);
            }
        });
        backButton.setOnAction(event -> {});
    }
}
