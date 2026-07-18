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


    @FXML
    private TextField balanceTextField,accountTextField;

    @FXML
    private Button withdrawButton,transferButton,transactionButton,backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FormLoader formloader =  new FormLoader();
        log.info("AccountServicesFormController loaded");
        Account account = Session.getAccount();
        if (account != null) {
            balanceTextField.setText(String.valueOf(account.getBalance()));
            accountTextField.setText(String.valueOf(account.getAccountId()));
        }

        withdrawButton.setOnAction(event -> {});
        transferButton.setOnAction(event -> {
            try {
                formloader.showFormTransfer();
                log.info("Transfer Form loaded");
                // بستن صفحه فعلی
                Stage stage = (Stage) transferButton.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                log.error("transfer error",e);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password", ButtonType.OK);
                alert.showAndWait();

            }
        });
//        transactionButton.setOnAction(event -> {
//            try {
//                FormLoader formloader =  new FormLoader();
//                formloader.showFormAccountServices();
//            } catch (IOException e) {
////                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password", ButtonType.OK);
////                    alert.show();
//                throw new RuntimeException(e);
//            }
//        });
        backButton.setOnAction(event -> {
            try {
                FormLoader formLoader = new FormLoader();
                formLoader.showFormAccountSelection();
                log.info("Account Selection loaded");
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                log.error("back button error",e);
                Alert alert =  new Alert(Alert.AlertType.ERROR, "connot load AccountServices call suport",ButtonType.OK);
                alert.showAndWait();
            }
        });
    }
}
