package bank;

import bank.model.tools.FormLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class FxApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        log.info("Starting bank.FxApp");
        FormLoader formloader =  new FormLoader();
        formloader.showFormLogin();
    }
}
