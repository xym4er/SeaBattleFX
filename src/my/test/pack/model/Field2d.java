package my.test.pack.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import my.test.pack.MainApp;
import my.test.pack.control.RootLayoutController;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ChornyiUA on 15.09.2015.
 */
public class Field2d {
    public static List<List<Cell>> cellss = new ArrayList<>();
    Map<Integer, Ship> ships = new HashMap<>();
    FXMLLoader loader = new FXMLLoader();
    public static RootLayoutController controller;


    public void init() {
        loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
        controller = loader.getController();
        for (int i = 0; i < 10; i++) {
            cellss.add(new ArrayList<Cell>());
            for (int j = 0; j < 10; j++) {
                cellss.get(i).add(new Cell());
                cellss.get(i).get(j).button = controller.buttons[i][j];

            }
        }
        int n = 1;
        for (int i = 4; i > 0; i--) {
            for (int j = i; j < 5; j++) {
                ships.put(n, new Ship(i));
                setShip(ships.get(n));
                n++;
            }
        }


    }

//    void showField() {
//        for (int i = 0; i < 10; i++) {
//            Window.jTextArea.append(i + "");
//            for (int j = 0; j < 10; j++) {
//                if (cellss.get(i).get(j).cell == 'X') Window.jTextArea.append(". ");
//                else Window.jTextArea.append(cellss.get(i).get(j).cell+" ");
//            }
//            Window.jTextArea.append("          " + i);
//            for (int j = 0; j < 10; j++) {
//                Window.jTextArea.append(cellss.get(i).get(j).cell+" ");
//            }
//            Window.jTextArea.append("\n");
//        }
//    }

    public void doShoot(int x, int y, TextArea textArea) {
        textArea.appendText("ваш выстрел: " + (x + 1) + " - " + (y + 1) + "\n");
        switch (cellss.get(x).get(y).cell) {
            case '.':
                textArea.appendText("Missed\n");
                cellss.get(x).get(y).cell = '@';
                controller.buttons[x][y].setText('\u2022' + "");
                break;
            case '@':
                textArea.appendText("Уже стреляли\n");
                break;
            case 'X':
                if (cellss.get(x).get(y).ship.health == 0) {
                    textArea.appendText("Убил!\n");
                    cellss.get(x).get(y).cell = '#';
                    controller.buttons[x][y].setText('\u2718' + "");
                    cellss.get(x).get(y).ship.boooom();
                    Ship.allHealth--;
                } else {
                    textArea.appendText("Попал!\n");
                    cellss.get(x).get(y).cell = '#';
                    controller.buttons[x][y].setText('\u2718' + "");
                    cellss.get(x).get(y).ship.health--;
                    Ship.allHealth--;
                }
                break;
            default:
                textArea.appendText("ERROR\n");
        }

        if (!isNotGameOver()) {
            textArea.appendText("Поздравляю, вы победили!");
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    controller.buttons[i][j].setDisable(true);
                }
            }
        }
    }

    void setShip(Ship ship) {
        do {
            ship.positionX = (int) (Math.random() * 10);
            ship.positionY = (int) (Math.random() * 10);
            ship.isVert = Math.random() * 2 <= 1;
        } while (checkShip(ship));
        ship.drawShip();
    }

    boolean checkShip(Ship ship) {
        if (ship.maxHealth > 0) {
            if (ship.isVert) {
                return ((ship.positionX + ship.maxHealth > 9) || checkAroundPoint(ship.positionX, ship.positionY) || checkAroundPoint(ship.positionX + ship.maxHealth, ship.positionY));
            } else
                return ((ship.positionY + ship.maxHealth > 9) || checkAroundPoint(ship.positionX, ship.positionY) || checkAroundPoint(ship.positionX, ship.positionY + ship.maxHealth));
        } else {
            return (checkAroundPoint(ship.positionX, ship.positionY));
        }
    }

    static void missAroundPoint(int x, int y) {

        if (x > 0 && y > 0) {
            if (cellss.get(x - 1).get(y - 1).cell == '.') {
                cellss.get(x - 1).get(y - 1).cell = '@';
                controller.buttons[x - 1][y - 1].setText('\u2022' + "");
            }
        }
        if (x > 0) {
            if (cellss.get(x - 1).get(y).cell == '.') {
                cellss.get(x - 1).get(y).cell = '@';
                controller.buttons[x - 1][y].setText('\u2022' + "");
            }
        }
        if (x > 0 && y < 9) {
            if (cellss.get(x - 1).get(y + 1).cell == '.') {
                cellss.get(x - 1).get(y + 1).cell = '@';
                controller.buttons[x - 1][y + 1].setText('\u2022' + "");
            }
        }
        if (y > 0) {
            if (cellss.get(x).get(y - 1).cell == '.') {
                cellss.get(x).get(y - 1).cell = '@';
                controller.buttons[x][y - 1].setText('\u2022' + "");
            }
        }
        if (y < 9) {
            if (cellss.get(x).get(y + 1).cell == '.') {
                cellss.get(x).get(y + 1).cell = '@';
                controller.buttons[x][y + 1].setText('\u2022' + "");
            }
        }
        if (x < 9 && y > 0) {
            if (cellss.get(x + 1).get(y - 1).cell == '.') {
                cellss.get(x + 1).get(y - 1).cell = '@';
                controller.buttons[x + 1][y - 1].setText('\u2022' + "");
            }
        }
        if (x < 9) {
            if (cellss.get(x + 1).get(y).cell == '.') {
                cellss.get(x + 1).get(y).cell = '@';
                controller.buttons[x + 1][y].setText('\u2022' + "");
            }
        }
        if (x < 9 && y < 9) {
            if (cellss.get(x + 1).get(y + 1).cell == '.') {
                cellss.get(x + 1).get(y + 1).cell = '@';
                controller.buttons[x + 1][y + 1].setText('\u2022' + "");
            }
        }
    }

    boolean checkAroundPoint(int x, int y) {
        if (cellss.get(x).get(y).cell == 'X') return true;
        if (x > 0 && y > 0) {
            if (cellss.get(x - 1).get(y - 1).cell == 'X') return true;
        }
        if (x > 0) {
            if (cellss.get(x - 1).get(y).cell == 'X') return true;
        }
        if (x > 0 && y < 9) {
            if (cellss.get(x - 1).get(y + 1).cell == 'X') return true;
        }
        if (y > 0) {
            if (cellss.get(x).get(y - 1).cell == 'X') return true;
        }
        if (y < 9) {
            if (cellss.get(x).get(y + 1).cell == 'X') return true;
        }
        if (x < 9 && y > 0) {
            if (cellss.get(x + 1).get(y - 1).cell == 'X') return true;
        }
        if (x < 9) {
            if (cellss.get(x + 1).get(y).cell == 'X') return true;
        }
        if (x < 9 && y < 9) {
            if (cellss.get(x + 1).get(y + 1).cell == 'X') return true;
        }
        return false;
    }


    boolean isNotGameOver() {
        return (Ship.allHealth >= 1);
    }
}