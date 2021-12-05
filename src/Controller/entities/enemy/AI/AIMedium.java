package Controller.entities.enemy.AI;

import Controller.MainGame;
import Controller.MenuGame;
import Controller.SettingGame;
import Controller.entities.Bomber;
import Controller.entities.enemy.Enemy;
import Controller.graphics.Sprite;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

class Node {
    public int num; // gia tri cua Node
    public int x;   // toa do x
    public int y;   // toa do y
    public int h;
    public int g;
    public int f;
    public Node pre;

    public Node(int num, int y, int x) {
        this.num = num;
        this.x = x;
        this.y = y;
        f = 0;
        g = 0;
        h = 0;
        pre = null;
    }

    public int distance(Node other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }

    public boolean equal(Node other) {
        return x == other.x && y == other.y;
    }

    @Override
    public String toString() {
        return "(" + y + "," + x + ")";
    }
}

public class AIMedium extends Enemy {

    private final Bomber b = SettingGame.bomberman;
    public Node[][] matrixNode = new Node[MainGame.HEIGHT][MainGame.WIDTH]; // ma tran cac Node
    public List<Node> path = new ArrayList<>(); // đường đi ngắn nhất

    protected static final int MAX_STEPS = Sprite.DEFAULT_SIZE / SPEED;
    protected int step = 0;

    public AIMedium(int xUnit, int yUnit, Image img, char e) {
        super(xUnit, yUnit, img, e);
        direction = findDirection();
    }

    public void moveEnemy() {
        if (die) {
            afterDie();
            return;
        }

        if (step == MAX_STEPS) {
            direction = findDirection();
            step = 0;
        }

        if (direction == 0) x += SPEED;  // right
        if (direction == 1) x -= SPEED;  // left
        if (direction == 2) y += SPEED;  // down
        if (direction == 3) y -= SPEED;  // up

        if (map[y / Sprite.DEFAULT_SIZE][x / Sprite.DEFAULT_SIZE] == '-') {
            die = true;
        }

        chooseSprite(direction);
        step++;
    }

    @Override
    public int findDirection() {
        createMatrixNode();
        A_star(matrixNode[y / Sprite.DEFAULT_SIZE][x / Sprite.DEFAULT_SIZE], matrixNode[b.getYUnit()][b.getXUnit()]);

        int i = path.size() - 1;
        if (i == 1) return -1;
        if (path.get(i).x == path.get(i - 1).x) {
            if (path.get(i).y < path.get(i - 1).y) { // down
                direction = 2;
            }
            if (path.get(i).y > path.get(i - 1).y) { // up
                direction = 3;
            }
        }
        if (path.get(i).y == path.get(i - 1).y) {
            if (path.get(i).x < path.get(i - 1).x) { // right
                direction = 0;
            }
            if (path.get(i).x > path.get(i - 1).x) { // left
                direction = 1;
            }
        }
        return direction;
    }

    public void createMatrixNode() {
        for (int i = 0; i < MainGame.HEIGHT; i++) {
            for (int j = 0; j < MainGame.WIDTH; j++) {
                if (map[i][j] == '#' || map[i][j] == '+' || map[i][j] == '-') {
                    matrixNode[i][j] = new Node(0, i, j);
                } else {
                    matrixNode[i][j] = new Node(1, i, j);
                }
            }
        }
    }

    // list node ke voi node dang xet
    public void setNearList(Node node, List<Node> near) {
        near.clear();
        if (matrixNode[node.y][node.x + 1].num > 0) { // cung hang, ben phai
            near.add(matrixNode[node.y][node.x + 1]);
        }
        if (matrixNode[node.y][node.x - 1].num > 0) { // cung hang, ben trai
            near.add(matrixNode[node.y][node.x - 1]);
        }
        if (matrixNode[node.y + 1][node.x].num > 0) { // cung cot, ben duoi
            near.add(matrixNode[node.y + 1][node.x]);
        }
        if (matrixNode[node.y - 1][node.x].num > 0) { // cung cot, ben tren
            near.add(matrixNode[node.y - 1][node.x]);
        }
    }

    // tim ra node co f nho nhat
    public Node nodeHasFMin(List<Node> list) {
        Node min = list.get(0);
        for (Node node : list) {
            if (min.f > node.f) min = node;
        }
        list.remove(min);
        return min;
    }

    public void A_star(Node start, Node end) {
        if (start.equal(end)) return;

        List<Node> openList = new ArrayList<>();
        List<Node> closeList = new ArrayList<>();
        List<Node> near = new ArrayList<>(); // list cac node ke voi node dang xet

        openList.add(start);
        Node min;
        do {
            min = nodeHasFMin(openList); // lay ra node nho nhat trong openList

            closeList.add(min); // them node nay vao closeList --> khong xet node nay nua

            if (min.equal(end)) { // neu node min la end thi dung vong lap
                path.clear();
                path.add(min);
                while (min.pre != null) {
                    path.add(min.pre);
                    min = min.pre;
                }
                break;
            }

            setNearList(min, near);

            for (Node n : near) {
                if (!closeList.contains(n)) {
                    if (!openList.contains(n)) {
                        n.g = min.g + 1;
                        openList.add(n);
                    } else {
                        if (min.g + 1 < n.g) n.g = min.g + 1;
                    }
                    n.h = n.distance(end);
                    n.f = n.g + n.h;
                    n.pre = min;
                }
            }
        } while (!openList.isEmpty());
    }
}
