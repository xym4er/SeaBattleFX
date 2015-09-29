package my.test.pack.model;

import javafx.fxml.FXMLLoader;
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
                cellss.get(i).add( new Cell());
                cellss.get(i).get(j).button=controller.buttons[i][j];

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

    public void doShoot(int y, int x) {
        controller.addText("ваш выстрел: " + y + " - " + x + "\n");




        switch (cellss.get(x).get(y).cell) {
            case '.':
                controller.addText("Missed\n");
                cellss.get(x).get(y).cell = '@';
                controller.setMiss(x,y);
                break;
            case '@':
                controller.addText("Уже стреляли\n");
                break;
            case 'X':
                if (cellss.get(x).get(y).ship.health == 0) {
                    controller.addText("Убил!\n");
                    cellss.get(x).get(y).cell = '#';
                    controller.setHit(x,y);
                    cellss.get(x).get(y).ship.boooom();
                    Ship.allHealth--;
                } else {
                    controller.addText("Попал!");
                    cellss.get(x).get(y).cell = '#';
                    controller.setHit(x,y);
                    cellss.get(x).get(y).ship.health--;
                    Ship.allHealth--;
                }
                break;
            default:
                controller.addText("ERROR\n");
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
            if (cellss.get(x - 1).get(y - 1).cell == '.') {cellss.get(x - 1).get(y - 1).cell = '@';controller.setMiss(x-1,y-1);}
        }
        if (x > 0) {
            if (cellss.get(x - 1).get(y).cell == '.') {cellss.get(x - 1).get(y).cell = '@';controller.setMiss(x-1,y);}
        }
        if (x > 0 && y < 9) {
            if (cellss.get(x - 1).get(y + 1).cell == '.') {cellss.get(x - 1).get(y + 1).cell = '@';controller.setMiss(x-1,y+1);}
        }
        if (y > 0) {
            if (cellss.get(x).get(y - 1).cell == '.') {cellss.get(x).get(y - 1).cell = '@';controller.setMiss(x,y-1);}
        }
        if (y < 9) {
            if (cellss.get(x).get(y + 1).cell == '.') {cellss.get(x).get(y + 1).cell = '@';controller.setMiss(x,y+1);}
        }
        if (x < 9 && y > 0) {
            if (cellss.get(x + 1).get(y - 1).cell == '.') {cellss.get(x + 1).get(y - 1).cell = '@';controller.setMiss(x+1,y-1);}
        }
        if (x < 9) {
            if (cellss.get(x + 1).get(y).cell == '.') {cellss.get(x + 1).get(y).cell = '@';controller.setMiss(x+1,y);}
        }
        if (x < 9 && y < 9) {
            if (cellss.get(x + 1).get(y + 1).cell == '.') {cellss.get(x + 1).get(y + 1).cell = '@';controller.setMiss(x+1,y+1);}
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