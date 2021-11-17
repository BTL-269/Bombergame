package graphics;

import com.sun.org.apache.xml.internal.utils.SuballocatedByteVector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Map {

    private int level;
    private int height;
    private int width;
    private char[][] map;

    public Map(String fileName) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String s = br.readLine();
            String[] arr = s.split(" ");
            level = Integer.parseInt(arr[0]);
            height = Integer.parseInt(arr[1]);
            width = Integer.parseInt(arr[2]);
            map = new char[height][width];
            int j = 0;
            while ((s = br.readLine()) != null && j < height) {
                for (int i = 0; i < s.length(); i++) {
                    map[j][i] = s.charAt(i);
                }
                j++;
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> findIndex(char c) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] == c) {
                    arr.add(i);
                    arr.add(j);
                }
            }
        }
        return arr;
    }

    public int getLevel() {
        return level;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public char[][] getMap() {
        return map;
    }
}
