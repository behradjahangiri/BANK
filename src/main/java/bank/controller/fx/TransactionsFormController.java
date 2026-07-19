package bank.controller.fx;

import bank.model.entity.Account;
import bank.model.entity.Transaction;
import bank.model.tools.FormLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
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

    }
}
