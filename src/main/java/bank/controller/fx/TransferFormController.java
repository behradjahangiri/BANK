package bank.controller.fx;

import bank.controller.AccountController;
import bank.controller.TransactionController;
import bank.model.entity.Account;
import bank.model.entity.TransactionType;
import bank.model.tools.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class TransferFormController implements Initializable {

    @FXML
    private TextField sourceAccountTextField,sourceAccountBalanceTextField,destinationAccountTextField,amountTextField;

    @FXML
    private Button transferButton,backButton;

    Account account = Session.getAccount();
    Integer sourceAccount  = account.getAccountId();
    Double sourceAccountBalance = account.getBalance();
    Integer destinationAccount = Integer.valueOf(destinationAccountTextField.getText());
    Double amount = Double.valueOf(String.valueOf(amountTextField));

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        transferButton.setOnAction(event ->
        {
            if (sourceAccountTextField.getText().isEmpty() || destinationAccountTextField.getText().isEmpty() || amountTextField.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.WARNING,"Please fill all the fields", ButtonType.OK);
                alert.showAndWait();
            }
            else if (AccountController.getInstance().findById(destinationAccount) == null)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING,"Account does not exist", ButtonType.OK);
            }
            else if (sourceAccountBalance < amount)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING,"You dont have this amount in this Account",ButtonType.OK);
                alert.showAndWait();
            }
            else if(sourceAccountBalance > amount )
            {
                AccountController.getInstance().withdraw(sourceAccount,amount);
                AccountController.getInstance().deposit(destinationAccount,amount);
                TransactionController.getInstance().save(TransactionType.Withdraw,amount,account);
                TransactionController.getInstance().save(TransactionType.Deposit, amount, (Account) AccountController.getInstance().findById(destinationAccount).getData());
            }

        });
        backButton.setOnAction(event -> {});
    }
}
