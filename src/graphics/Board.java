package graphics;

import Controller.*;
import entities.*;
import entities.emeny.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Board {

    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> entities = new ArrayList<>();
    public static char[][] map = new char[SettingGame.HEIGHT][SettingGame.WIDTH];

    public void createMap(String fileName) {
        try {
            Sprite.setPlayer();
            Sprite.setMap();
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            for (int i = 0; i < SettingGame.HEIGHT; i++) {
                String s = br.readLine();
                for (int j = 0; j < s.length(); j++) {
                    Entity object = null;
                    Entity grass = new Grass(j, i, Sprite.grass.getFxImage());
                    stillObjects.add(grass);
                    map[i][j] = s.charAt(j);
                    switch (s.charAt(j)) {
                        case '#':
                            object = new Wall(j, i, Sprite.wall.getFxImage());
                            break;
                        case '*':
                            entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        case 'x':
                            entities.add(new Portal(j, i, Sprite.brick.getFxImage()));
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
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}