package Controller.graphics;


import Controller.MainGame;
import Controller.SettingGame;
import Controller.entities.*;

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

    public void createMap(String path) {
        try {

            URL absPath = Board.class.getResource("/" + path);
            BufferedReader in = new BufferedReader(new InputStreamReader(absPath.openStream()));
            for (int i = 0; i < MainGame.HEIGHT; i++) {
                String s = in.readLine();
                s.trim();
                for (int j = 0; j < s.length(); j++) {
                    Entity object = null;
                    if (SettingGame.map == 1) {
                        object = new Grass(j, i, Sprite.grass1.getFxImage());
                    } else if (SettingGame.map == 2) {
                        object = new Grass(j, i, Sprite.grass2.getFxImage());
                    } else if (SettingGame.map == 3) {
                        object = new Grass(j, i, Sprite.grass3.getFxImage());
                    } else {
                        object = new Grass(j, i, Sprite.grass4.getFxImage());
                    }
                    stillObjects.add(object);
                    switch (s.charAt(j)) {
                        case '#':
                            if (SettingGame.map == 1) {
                                object = new Wall(j, i, Sprite.wall1.getFxImage());
                            } else if (SettingGame.map == 2) {
                                object = new Wall(j, i, Sprite.wall2.getFxImage());
                            } else if (SettingGame.map == 3) {
                                object = new Wall(j, i, Sprite.wall3.getFxImage());
                            } else {
                                object = new Wall(j, i, Sprite.wall4.getFxImage());
                            }
                            break;
                        case '*':
                            if (SettingGame.map == 1) {
                                object = new Brick(j, i, Sprite.brick1.getFxImage());
                            } else if (SettingGame.map == 2) {
                                object = new Brick(j, i, Sprite.brick2.getFxImage());
                            } else if (SettingGame.map == 3) {
                                object = new Brick(j, i, Sprite.brick3.getFxImage());
                            } else {
                                object = new Brick(j, i, Sprite.brick4.getFxImage());
                            }
                            break;
                        case 'x':
                            object = new Portal(j, i, Sprite.portal.getFxImage());
                            stillObjects.add(object);
                            if (SettingGame.map == 1) {
                                object = new Brick(j, i, Sprite.brick1.getFxImage());
                            } else if (SettingGame.map == 2) {
                                object = new Brick(j, i, Sprite.brick2.getFxImage());
                            } else if (SettingGame.map == 3) {
                                object = new Brick(j, i, Sprite.brick3.getFxImage());
                            } else {
                                object = new Brick(j, i, Sprite.brick4.getFxImage());
                            }
                            break;
                        case '1':
                            object = new Enemies(j, i, Sprite.balloom_left1.getFxImage());
                            break;
                        case '2':
                            object = new Enemies(j, i, Sprite.oneal_left1.getFxImage());
                            break;
                        case '3':
                            object = new Enemies(j, i, Sprite.minvo_left1.getFxImage());
                            break;
                        case '4':
                            object = new Enemies(j, i, Sprite.doll_left1.getFxImage());
                            break;
                        case '5':
                            object = new Enemies(j, i, Sprite.kondoria_left1.getFxImage());
                            break;
                        case 'b':
                            object = new Items(j, i, Sprite.bomb.getFxImage());
                            stillObjects.add(object);
                            object = new Items(j, i, Sprite.gift.getFxImage());
                            break;
                        case 'd':
                            object = new Items(j, i, Sprite.powerup_detonator.getFxImage());
                            stillObjects.add(object);
                            object = new Items(j, i, Sprite.gift.getFxImage());
                            break;
                        case 'f':
                            object = new Items(j, i, Sprite.powerup_flames.getFxImage());
                            stillObjects.add(object);
                            object = new Items(j, i, Sprite.gift.getFxImage());
                            break;
                        case 's':
                            object = new Items(j, i, Sprite.powerup_speed.getFxImage());
                            stillObjects.add(object);
                            object = new Items(j, i, Sprite.gift.getFxImage());
                            break;
                        case 'w':
                            object = new Items(j, i, Sprite.powerup_wallpass.getFxImage());
                            stillObjects.add(object);
                            object = new Items(j, i, Sprite.gift.getFxImage());
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