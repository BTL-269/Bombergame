package Controller.graphics;


import Controller.MainGame;
import Controller.SettingGame;
import Controller.entities.*;
import Controller.entities.enemy.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board {

    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> entities = new ArrayList<>();
    public static char[][] map = new char[MainGame.HEIGHT][MainGame.WIDTH];

    public void create(String fileName) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            for (int i = 0; i < MainGame.HEIGHT; i++) {
                String s = br.readLine();
                for (int j = 0; j < s.length(); j++) {
                    map[i][j] = s.charAt(j);
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createMap(String fileName) {
        entities.clear();
        stillObjects.clear();
        Sprite.setPlayer();
        Sprite.setMap();
        MainGame.numberEnemies = 0;
        create(fileName);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                Entity object = null;
                Entity grass = new Grass(j, i, Sprite.grass.getFxImage());
                stillObjects.add(grass);
                switch (map[i][j]) {
                    case '#':
                        object = new Wall(j, i, Sprite.wall.getFxImage());
                        break;
                    case '1':
                        entities.add(new Balloon(j, i, Sprite.balloom_left1.getFxImage()));
                        break;
                    case '2':
                        entities.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                        break;
                    case '3':
                        entities.add(new Doll(j, i, Sprite.doll_left1.getFxImage()));
                        break;
                    case '4':
                        entities.add(new Minvo(j, i, Sprite.minvo_left1.getFxImage()));
                        break;
                    case '5':
                        entities.add(new Kondoria(j, i, Sprite.kondoria_left1.getFxImage()));
                        break;
                    case 'x':
                        entities.add(new Portal(j, i, Sprite.brick.getFxImage()));
                        break;
                    case '*':
                        entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case 'b':
                    case 'd':
                    case 'f':
                    case 's':
                    case 'w':
                        entities.add(new Items(j, i, Sprite.brick.getFxImage()));
                        break;
                    default:
                        break;
                }
                if (object != null) {
                    stillObjects.add(object);
                }
            }
        }
    }
}