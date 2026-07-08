package bank.model.tools;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FormLoader {

    public void showFormLogin() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/login.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    public void showFormAccountSelection() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/accountSelection.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    public void showFormAccountServices() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/accountServices.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    public void showFormTransfer() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/Transfer.fxml")));
        stage.setScene(scene);
        stage.show();
    }


}