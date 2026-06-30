package bank.controller.fx;

import bank.model.entity.Account;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

@Slf4j
public class AccountSelectionFormController implements Initializable {

    @FXML
    private Button logoutButton, transactionButton,freezeButton;

    @FXML
    private TableView<Account> accountTable;

    @FXML
    private TableColumn<Account, Integer> idColumn;

    @FXML
    private TableColumn<Account, Double> balanceColumn;

    @FXML
    private TableColumn<Account, Date> openDateColumn;

    @FXML
    private TableColumn<Account, String> statusColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("AccountSelectionFormController loaded");
    }
}
