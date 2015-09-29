package my.test.pack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import my.test.pack.control.RootLayoutController;

import java.io.IOException;


/**
 * Created by ChornyiUA on 28.09.2015.
 */
public class MainApp extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;



    public MainApp() {
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (AnchorPane)loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            controller.initButtons();

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


}

