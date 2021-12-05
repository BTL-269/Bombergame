package Controller.graphics;


import Controller.MainGame;
import Controller.SettingGame;
import Controller.entities.*;
import Controller.entities.enemy.Balloon;
import Controller.entities.enemy.Doll;
import Controller.entities.enemy.Enemy;
import Controller.entities.enemy.Oneal;

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

    public static char[][] map = new char[MainGame.HEIGHT][MainGame.WIDTH];

    public void createMap(String path) {
        try {
            stillObjects.clear();
            MainGame.entities.clear();
            Sprite.setPlayer();
            Sprite.setMap();
            MainGame.numberEnemies = 0;
            URL absPath = Board.class.getResource("/" + path);
            BufferedReader in = new BufferedReader(new InputStreamReader(absPath.openStream()));
            for (int i = 0; i < MainGame.HEIGHT; i++) {
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
                            MainGame.entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        case 'x':
                            MainGame.entities.add(new Portal(j, i, Sprite.brick.getFxImage()));
                            break;
                        case '1':
                            MainGame.entities.add(new Balloon(j, i, Sprite.balloom_left1.getFxImage()));
                            MainGame.numberEnemies++;
                            break;
                        case '2':
                            MainGame.entities.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                            MainGame.numberEnemies++;
                            break;
                        case '3':
                            MainGame.entities.add(new Doll(j, i, Sprite.doll_left1.getFxImage()));
                            MainGame.numberEnemies++;
                            break;
                        case 'b':
                        case 'd':
                        case 'f':
                        case 's':
                            MainGame.entities.add(new Items(j, i, Sprite.brick.getFxImage()));
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