package my.test.pack.control;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import my.test.pack.MainApp;
import javafx.scene.control.Button;
import my.test.pack.model.Field2d;


public class RootLayoutController {
    @FXML
    public VBox vbox;
    @FXML
    private TextArea textArea;

    private MainApp mainApp;
    public static Button[][] buttons = new Button[10][10];
    public HBox[] hboxs = new HBox[10];
    Field2d field2d = new Field2d();

    @FXML
    public void click(){
        field2d.doShoot();

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void init() {
        for (int j = 0; j < 10; j++) {
            hboxs[j] = (HBox) vbox.getChildren().get(j + 1);
            for (int i = 0; i < 10; i++) {
                buttons[j][i] = (Button) hboxs[j].getChildren().get(i + 1);
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j].getStyleClass().add("button2");
            }
        }

        field2d.init();
    }


    public void setMiss(int x, int y) {
        buttons[x][y].setText('\u2718' + "");
    }

    public void setHit(int x, int y) {
        buttons[x][y].setText('\u2022' + "");
    }

    public void addText(String s){
        textArea.appendText(s);
    }

    public void clearText(){
        textArea.clear();
    }

}
