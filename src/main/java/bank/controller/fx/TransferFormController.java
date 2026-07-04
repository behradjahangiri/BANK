package bank.controller.fx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TransferFormController implements Initializable {

    @FXML
    private TextField sourceAccountTextField,sourceAccountBalanceTextField,destinationAccountTextField,amountTextField;

    @FXML
    private Button transferButton,backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Integer sourceAccountBalance ; //  باید از فرم قبلی به اینجا بیاد
        Double sourceAccountBalanceDouble; // باید از فرم قبلی بیاد
        Integer destinationAccountBalance;
        Double amount = Double.valueOf(String.valueOf(amountTextField));
        transferButton.setOnAction(event ->
        {
            if (sourceAccountTextField.getText().isEmpty() || destinationAccountTextField.getText().isEmpty() || amountTextField.getText().isEmpty())
            {

            }

        });
        backButton.setOnAction(event -> {});
    }
    private void transfer(Integer sourceAccountId, Integer destinationAccountId, Double amount) throws Exception{}
}
