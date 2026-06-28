package controller.fx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class loginFormController implements Initializable {
    @FXML
    private TextField usernameTextField, passwordTextField;
    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
