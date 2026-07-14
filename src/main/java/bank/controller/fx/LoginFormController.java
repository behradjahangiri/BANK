package bank.controller.fx;

import bank.controller.CustomerController;
import bank.model.entity.Customer;
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

public class LoginFormController implements Initializable {
    @FXML
    private TextField userIdTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button loginButton;

    FormLoader formloader =  new FormLoader();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Login Form loaded");
        loginButton.setOnAction(event -> {
            Integer  userId = Integer.parseInt(userIdTextField.getText());
            String password = passwordTextField.getText();
            if (login(userId, password))
            {
                try {
                    Session.customerId = userId;
                    formloader.showFormAccountSelection();
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password", ButtonType.OK);
                    alert.show();
                    throw new RuntimeException(e);
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Invalid username or password",ButtonType.OK);
                alert.showAndWait();
            }
        });
    }


    private boolean login(Integer userId, String password) {
        Customer customer = (Customer) CustomerController.getInstance().findById(userId).getData();
        if(userId == 0 || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password", ButtonType.OK);
            alert.show();
            return false;
        }
        if (CustomerController.getInstance().findById(userId).getData() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password", ButtonType.OK);
            alert.show();
            return false;
        }
        else if (customer.getPassword().equals(password) && customer.getId() == userId) {
            return  true;
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password", ButtonType.OK);
            alert.show();
            return false;
        }
    }
}
