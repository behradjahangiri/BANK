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
public class TransferFormController implements Initializable {

    @FXML
    private TextField sourceAccountTextField,sourceAccountBalanceTextField,destinationAccountTextField,amountTextField;

    @FXML
    private Button transferButton,backButton;


    private final FormLoader formLoader = new FormLoader();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Account account = Session.getAccount();
        Integer sourceAccount  = account.getAccountId();
        Double sourceAccountBalance = account.getBalance();
        sourceAccountBalanceTextField.setText(String.valueOf(sourceAccountBalance));


        transferButton.setOnAction(event ->
        {
            if (sourceAccountTextField.getText().isEmpty() || destinationAccountTextField.getText().isEmpty() || amountTextField.getText().isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.WARNING,"Please fill all the fields", ButtonType.OK);
                alert.showAndWait();
            }
            else {
                try {
                    Integer destinationAccount = Integer.valueOf(destinationAccountTextField.getText());
                    Double amount = Double.valueOf(amountTextField.getText());
                    if (AccountController.getInstance().findById(destinationAccount) == null)
                    {
                        Alert alert = new Alert(Alert.AlertType.WARNING,"Account does not exist", ButtonType.OK);
                        alert.showAndWait();
                    }
                    else if (amount <= 0)
                    {
                        Alert alert = new Alert(Alert.AlertType.WARNING,"amount can not be - or 0 ",ButtonType.OK);
                        alert.showAndWait();
                    }
                    else if (sourceAccountBalance < amount)
                    {
                        Alert alert = new Alert(Alert.AlertType.WARNING,"You dont have this amount in this Account",ButtonType.OK);
                        alert.showAndWait();
                    }
                    else if (sourceAccount.equals(destinationAccount))
                    {
                        Alert alert = new Alert(Alert.AlertType.WARNING,"Source and destination accounts cannot be the same",ButtonType.OK);
                        alert.showAndWait();
                    }
                    else {
                        AccountController.getInstance().withdraw(sourceAccount,amount);
                        AccountController.getInstance().deposit(destinationAccount,amount);
                        TransactionController.getInstance().save(TransactionType.Withdraw,amount,account);
                        TransactionController.getInstance().save(TransactionType.Deposit, amount, (Account) AccountController.getInstance().findById(destinationAccount).getData());
                        try {
                            formLoader.showFormAccountServices();
                            Stage stage = (Stage) transferButton.getScene().getWindow();
                            stage.close();
                        } catch (IOException e) {
                            log.error("cannot load AccountService in transfer Button",e);
                            Alert alert = new Alert(Alert.AlertType.ERROR,"cannot load AccountService Form call support", ButtonType.OK);
                            alert.showAndWait();
                        }

                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING,"Please enter valid numbers", ButtonType.OK);
                    alert.showAndWait();
                }

            }


        });
        backButton.setOnAction(event -> {
            try {
                formLoader.showFormAccountServices();
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                log.error("cannot load AccountService in back Button",e);
                Alert alert = new Alert(Alert.AlertType.ERROR,"cannot load AccountService Form call support", ButtonType.OK);
                alert.showAndWait();
            }
        });
    }
}
