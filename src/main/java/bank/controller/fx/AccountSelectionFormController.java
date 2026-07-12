package bank.controller.fx;

import bank.controller.AccountController;
import bank.model.entity.Account;
import bank.model.entity.Response;
import bank.model.entity.ResponseStatus;
import bank.model.tools.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.slf4j.Slf4j;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Slf4j
public class AccountSelectionFormController implements Initializable {

    @FXML
    private Button logoutButton, transactionButton,freezeButton;

    @FXML
    private TextField accountTextField;

    @FXML
    private TableView<Account> accountTable;

    @FXML
    private TableColumn<Account, Integer> idColumn;

    @FXML
    private TableColumn<Account, Double> balanceColumn;

    @FXML
    private TableColumn<Account, LocalDate> openDateColumn;

    @FXML
    private TableColumn<Account, String> statusColumn;

    Account account = new Account();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("AccountSelectionFormController loaded");
        transactionButton.setOnAction(event -> {
            if (accountTable.getSelectionModel().getSelectedItem() != null) {
                Session.setAccount(accountTable.getSelectionModel().getSelectedItem());
//                اینجا باید اکانت انتخاب شده به صفحه ی سرویس ها بره
            }
        });
        freezeButton.setOnAction(event -> {});
        logoutButton.setOnAction(event -> {});
        accountTable.setOnMouseReleased(event -> {
            Account account = accountTable.getSelectionModel().getSelectedItem();
            if (account != null) {
                accountTextField.setText(String.valueOf(account.getAccountId()));
            }
        });
        resetTableView();
    }


    private void showDataOnTableView(List<Account> accountList){

        ObservableList<Account> accountObservableList = FXCollections.observableList(accountList);
        idColumn.setCellValueFactory(new PropertyValueFactory<Account,Integer>("accountId"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<Account,Double>("balance"));
        openDateColumn.setCellValueFactory(new PropertyValueFactory<Account, LocalDate>("openDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("status"));
        accountTable.setItems(accountObservableList);
    }
//        showDataOnTableView((List<Account>) AccountController.getInstance().findAll());

    private void resetTableView(){
        accountTable.getItems().clear();
        showDataOnTableView((List<Account>) AccountController.getInstance().findAll().getData());

    }

    private void showAlert(Response response)
    {
        if (response.getResponseStatus().equals(ResponseStatus.Success))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,response.getMessage(), ButtonType.OK);
            alert.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,response.getMessage(), ButtonType.OK);
            alert.show();
        }
    }


}
