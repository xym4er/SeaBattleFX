package my.test.pack.control;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import my.test.pack.MainApp;
import javafx.scene.control.Button;



public class RootLayoutController {

    public Button[][] buttons = new Button[10][10];
    public HBox[] hboxs = new HBox[10];
    @FXML
    public Button b11;
    @FXML
    public Button b12;
    @FXML
    public VBox vbox;

    private MainApp mainApp;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }
    public void initButtons() {
        for (int j = 0; j < 10; j++) {
            hboxs[j]=(HBox)vbox.getChildren().get(j+1);
         for (int i = 0; i <10 ; i++) {
                buttons[j][i]=(Button)hboxs[j].getChildren().get(i+1);
         }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[j][i].getStyleClass().add("button2");
            }
        }
        buttons[3][3].setText('\u2718' + "");
        buttons[3][4].setText('\u2022' + "");
    }



}
