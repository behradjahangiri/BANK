package bank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class FxApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        log.info("Starting bank.FxApp");

        Scene scene = new Scene(
                FXMLLoader.load(getClass().getResource("/view/login.fxml"))
        );
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }
}
