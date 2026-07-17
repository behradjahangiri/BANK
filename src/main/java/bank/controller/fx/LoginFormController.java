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




                try {
                    Integer  userId = Integer.parseInt(userIdTextField.getText());
                    String password = passwordTextField.getText();
                    if (login(userId, password)) {
                        Session.setCustomerId(userId);
                        formloader.showFormAccountSelection();
                        Stage stage = (Stage) loginButton.getScene().getWindow();
                        stage.close();
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR,"Invalid username or password",ButtonType.OK);
                        alert.showAndWait();
                    }

                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot load account selection page", ButtonType.OK);
                    alert.showAndWait();


                } catch (NumberFormatException e){
                  Alert alert = new Alert(Alert.AlertType.WARNING, "User ID must be a number", ButtonType.OK);
                  alert.showAndWait();
                }
        });
    }


    private boolean login(Integer userId, String password) {

        if(userId == null || userId.equals(0) || password == null || password.isEmpty() ) {
            return false;
        }
        Customer customer = (Customer) CustomerController.getInstance().findById(userId).getData();

        if (customer == null)
        {
            return false;
        }
        else return customer.getPassword().equals(password);
    }
}
