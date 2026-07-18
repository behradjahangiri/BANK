package bank.controller.fx;

import bank.model.entity.Account;
import bank.model.entity.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Slf4j

public class TransactionsFormController implements Initializable {

    @FXML
    private TableView<Transaction> transactionsTable;

    @FXML
    private TableColumn<Transaction, Integer> transactionIdColumn;

    @FXML
    private TableColumn<Transaction, String> transactionTypeColumn;

    @FXML
    private TableColumn<Transaction, Double> amountColumn;

    @FXML
    private TableColumn<Transaction, LocalDate> dateTimeColumn;

    @FXML
    private TableColumn<Transaction, Account> accountIdColumn;

    @FXML
    private Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
