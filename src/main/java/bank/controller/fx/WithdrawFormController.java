package bank.controller.fx;

import bank.controller.AccountController;
import bank.controller.TransactionController;
import bank.model.entity.Account;
import bank.model.entity.TransactionType;
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
public class WithdrawFormController implements Initializable {

    @FXML
    private TextField amountTextField,accountTextField,balanceTextField;

    @FXML
    private Button backButton, withdrawButton;

    Account account = Session.getAccount();
    Integer sourceAccountId = account.getAccountId();
    Double sourceAccountBalance = account.getBalance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        withdrawButton.setOnMouseClicked(event -> {
            try {
                double amount = Double.parseDouble(amountTextField.getText());
                AccountController.getInstance().withdraw(sourceAccountId,amount);
                TransactionController.getInstance().save(TransactionType.Withdraw,amount,account);
            } catch (Exception e) {
                log.error("withdraw did not work");
                Alert alert = new Alert(Alert.AlertType.ERROR,"withdraw did not work",ButtonType.OK);
                alert.showAndWait();
            }
        });
        backButton.setOnMouseClicked(event -> {
            try {
                FormLoader formLoader = new FormLoader();
                formLoader.showFormAccountServices();
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.close();
            }  catch (IOException e) {
                log.error("cannot load AccountService in back Button",e);
                Alert alert = new Alert(Alert.AlertType.ERROR,"cannot load AccountService Form call support", ButtonType.OK);
                alert.showAndWait();
            }
        });


    }
}
