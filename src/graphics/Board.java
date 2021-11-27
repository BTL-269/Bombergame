package graphics;

import Controller.*;
import entities.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Entity> stillObjects = new ArrayList<Entity>();

    public List<Entity> getStillObjects() {
        return stillObjects;
    }

    public static char[][] map = new char[SettingGame.HEIGHT][SettingGame.WIDTH];

    public void createMap(String path) {
        try {
            Sprite.setPlayer();
            Sprite.setMap();
            SettingGame.numberEnemies = 0;
            URL absPath = Board.class.getResource("/" + path);
            BufferedReader in = new BufferedReader(new InputStreamReader(absPath.openStream()));
            for (int i = 0; i < SettingGame.HEIGHT; i++) {
                String s = in.readLine();
                s.trim();
                for (int j = 0; j < s.length(); j++) {
                    Entity object = null;
                    Entity grass = new Grass(j, i, Sprite.grass.getFxImage());
                    if (grass != null) {
                        stillObjects.add(grass);
                    }
                    map[i][j] = s.charAt(j);
                    switch (s.charAt(j)) {
                        case '#':
                            object = new Wall(j, i, Sprite.wall.getFxImage());
                            break;
                        case '*':
                            SettingGame.entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        case 'x':
                            SettingGame.entities.add(new Portal(j, i, Sprite.brick.getFxImage()));
                            break;
                        case '1':
                            SettingGame.entities.add(new Enemies(j, i, Sprite.balloom_left1.getFxImage()));
                            SettingGame.numberEnemies++;
                            break;
                        case '2':
                            SettingGame.entities.add(new Enemies(j, i, Sprite.oneal_left1.getFxImage()));
                            SettingGame.numberEnemies++;
                            break;
                        case '3':
                            SettingGame.entities.add(new Enemies(j, i, Sprite.minvo_left1.getFxImage()));
                            SettingGame.numberEnemies++;
                            break;
                        case '4':
                            SettingGame.entities.add(new Enemies(j, i, Sprite.doll_left1.getFxImage()));
                            SettingGame.numberEnemies++;
                            break;
                        case '5':
                            SettingGame.entities.add(new Enemies(j, i, Sprite.kondoria_left1.getFxImage()));
                            SettingGame.numberEnemies++;
                            break;
                        case 'b':
                        case 'd':
                        case 'f':
                        case 's':
                        case 'w':
                            SettingGame.entities.add(new Items(j, i, Sprite.brick.getFxImage()));
                            break;
                        default:
                            break;
                    }
                    if (object != null) {
                        stillObjects.add(object);
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}