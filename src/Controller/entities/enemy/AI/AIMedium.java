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
    // '4' : xuyen brick, '2' : khong xuyen brick

    private final Bomber b = SettingGame.bomberman;
    private Node[][] matrixNode = new Node[MainGame.HEIGHT][MainGame.WIDTH]; // ma tran cac Node
    private List<Node> path = new ArrayList<>(); // đường đi ngắn nhất
    public boolean hasPath = true;

    public AIMedium(int xUnit, int yUnit, Image img, char e) {
        super(xUnit, yUnit, img, e);
        direction = findDirection();
    }

    @Override
    public void moveEnemy() {
        if (findDirection() == -1) {
            if (!move()) {
                direction = rd.nextInt(4);
            }
        } else {
            move();
        }
    }

    public int findDirection() {
        createMatrixNode();
        if (direction == 1) {
            A_star(matrixNode[y / Sprite.DEFAULT_SIZE][(x + 31) / Sprite.DEFAULT_SIZE], matrixNode[b.getYUnit()][b.getXUnit()]);
        } else if (direction == 3) {
            A_star(matrixNode[(y + 31) / Sprite.DEFAULT_SIZE][x / Sprite.DEFAULT_SIZE], matrixNode[b.getYUnit()][b.getXUnit()]);
        } else {
            A_star(matrixNode[y / Sprite.DEFAULT_SIZE][x / Sprite.DEFAULT_SIZE], matrixNode[b.getYUnit()][b.getXUnit()]);
        }
        if (!hasPath) return -1;
        int i = path.size() - 1;
        if (i <= 1) return -1;
        if ((b.bomb1.check && path.contains(matrixNode[b.bomb1.getYUnit()][b.bomb1.getXUnit()]))
                || (b.bomb2.check && path.contains(matrixNode[b.bomb2.getYUnit()][b.bomb2.getXUnit()]))) {
            return -1;
        }
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
                if (enemy == '2') {
                    if (map[i][j] == '#' || map[i][j] == '*' || map[i][j] == 'x' || map[i][j] == 'b'
                            || map[i][j] == 'f' || map[i][j] == 's' || map[i][j] == 'd') {
                        matrixNode[i][j] = new Node(0, i, j);
                    } else {
                        matrixNode[i][j] = new Node(1, i, j);
                    }
                } else {
                    if (map[i][j] == '#') {
                        matrixNode[i][j] = new Node(0, i, j);
                    } else {
                        matrixNode[i][j] = new Node(1, i, j);
                    }
                }
            }
        }
    }

    // list node ke voi node dang xet
    public void setNearList(Node node, List<Node> near) {
        near.clear();
        if (node.x < MainGame.WIDTH - 1 && matrixNode[node.y][node.x + 1].num > 0) { // cung hang, ben phai
            near.add(matrixNode[node.y][node.x + 1]);
        }
        if (node. x > 1 && matrixNode[node.y][node.x - 1].num > 0) { // cung hang, ben trai
            near.add(matrixNode[node.y][node.x - 1]);
        }
        if (node.y < MainGame.HEIGHT - 1 && matrixNode[node.y + 1][node.x].num > 0) { // cung cot, ben duoi
            near.add(matrixNode[node.y + 1][node.x]);
        }
        if (node.y > 1 && matrixNode[node.y - 1][node.x].num > 0) { // cung cot, ben tren
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
        hasPath = true;
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
        if (path.isEmpty()) hasPath = false;
    }
}
