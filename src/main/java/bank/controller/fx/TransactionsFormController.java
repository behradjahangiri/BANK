package bank.controller.fx;

import bank.controller.TransactionController;
import bank.model.entity.Account;
import bank.model.entity.Response;
import bank.model.entity.ResponseStatus;
import bank.model.entity.Transaction;
import bank.model.tools.FormLoader;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

@Slf4j

public class TransactionsFormController implements Initializable {

    @FXML
    private TableView<Transaction> transactionsTable;

    @FXML
    private TableColumn<Transaction, Long> transactionIdColumn;

    @FXML
    private TableColumn<Transaction, String> transactionTypeColumn;

    @FXML
    private TableColumn<Transaction, Double> amountColumn;

    @FXML
    private TableColumn<Transaction, LocalDateTime> dateTimeColumn;
//
//    @FXML
//    private TableColumn<Transaction, Account> accountIdColumn;
    @FXML
    private TableColumn<Transaction, Integer> accountIdColumn;

    @FXML
    private Button backButton;

    private final FormLoader formLoader = new FormLoader();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("transactionsFormController loaded");
        backButton.setOnAction(actionEvent -> {
            try {
                formLoader.showFormAccountServices();
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.close();
            } catch (IOException e) {
                log.error("Error loading FormAccountSelection", e);
                Alert alert = new Alert(Alert.AlertType.ERROR,"connot open FormAccountSelection", ButtonType.OK);
                alert.showAndWait();
            }
        });
//        Response response = TransactionController.getInstance().findAll();
//        List<Transaction> transactions = (List<Transaction>) response.getData();
//        showDataOnTableView();
        loadTransactions();

    }
    private  void showDataOnTableView(List<Transaction> transactions) {
//        ObservableList<Transaction> transactionsObservableList = FXCollections.observableArrayList();
        ObservableList<Transaction> transactionsObservableList = FXCollections.observableArrayList(transactions);
        transactionIdColumn.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
        transactionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        accountIdColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(
                        cellData.getValue().getAccount().getAccountId()
                )
        );
        transactionsTable.setItems(transactionsObservableList);
//        accountIdColumn.setCellValueFactory(new PropertyValueFactory<>("accountId"));
    }
    private void loadTransactions() {
        Response response = TransactionController.getInstance().findAll();

        if (response.getResponseStatus() == ResponseStatus.Success) {
            List<Transaction> transactions = (List<Transaction>) response.getData();
            showDataOnTableView(transactions);
            log.info("transactions loaded");
        } else {
            log.info("transactions load failed");
            Alert alert = new Alert(Alert.AlertType.ERROR,response.getMessage(),ButtonType.OK);
            alert.showAndWait();
        }
    }
}
