package bank.controller.fx;

import bank.model.entity.Account;
import bank.model.tools.FormLoader;
import bank.model.tools.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class AccountServicesFormController implements Initializable {
    private final FormLoader formLoader = new FormLoader();


    @FXML
    private TextField balanceTextField,accountTextField;

    @FXML
    private Button withdrawButton,transferButton,transactionButton,backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("AccountServicesFormController loaded");
        Account account = Session.getAccount();
        if (account == null) {
            log.error("No account found in session");
            Alert alert = new Alert(Alert.AlertType.ERROR, "No active account found. Returning to account selection.", ButtonType.OK);
            alert.showAndWait();
            try {
                formLoader.showFormAccountSelection();
                log.info("Returned to Account Selection form");
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.close();
            } catch (IOException e){
                log.error("Failed to return to Account Selection form", e);
                Alert alert1 = new Alert(Alert.AlertType.ERROR,"Unable to return to Account Selection. Please contact support.", ButtonType.OK);
                alert1.showAndWait();
            }
            return;
        }
        balanceTextField.setText(String.valueOf(account.getBalance()));
        accountTextField.setText(String.valueOf(account.getAccountId()));

        withdrawButton.setOnAction(event -> {});
        transferButton.setOnAction(event -> {
            try {
                formLoader.showFormTransfer();
                log.info("transfer form loaded");
                Stage stage = (Stage) transferButton.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                log.error("transfer button error",e);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot load the transfer form. Please contact support.", ButtonType.OK);
                alert.showAndWait();

            }
        });
        transactionButton.setOnAction(event -> {
            try {
                formLoader.showFormTransactions();
                log.info("transaction form loaded");
                Stage stage = (Stage) transactionButton.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                log.error("transaction button error",e);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot load the transaction form. Please contact support.", ButtonType.OK);
                alert.showAndWait();
            }
        });
        backButton.setOnAction(event -> {
            try {
                formLoader.showFormAccountSelection();
                log.info("account Selection loaded");
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                log.error("back button error",e);
                Alert alert =  new Alert(Alert.AlertType.ERROR, "Cannot load the Account Selection form. Please contact support.",ButtonType.OK);
                alert.showAndWait();
            }
        });
    }
}
