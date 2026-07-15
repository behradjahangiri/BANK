package bank.controller.fx;

import bank.model.entity.Account;
import bank.model.tools.FormLoader;
import bank.model.tools.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
        log.info("AccountServicesFormController loaded");
        Account account = Session.getAccount();
        if (account != null) {
            balanceTextField.setText(String.valueOf(account.getBalance()));
            accountTextField.setText(String.valueOf(account.getAccountId()));
        }

        withdrawButton.setOnAction(event -> {});
        transferButton.setOnAction(event -> {
            try {
                FormLoader formloader =  new FormLoader();
                formloader.showFormTransfer();
                // بستن صفحه فعلی
                Stage stage = (Stage) transferButton.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
//                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password", ButtonType.OK);
//                    alert.show();
                throw new RuntimeException(e);
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
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
