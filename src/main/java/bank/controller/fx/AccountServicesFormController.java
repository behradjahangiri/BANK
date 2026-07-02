package bank.controller.fx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
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
        transferButton.setOnAction(event -> {});
        transactionButton.setOnAction(event -> {});
        backButton.setOnAction(event -> {});
    }
}
