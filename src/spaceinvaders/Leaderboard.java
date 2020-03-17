package spaceinvaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Leaderboard {

    private HashMap leaderboard = new HashMap();

    public void add(String name, int score) {
        try {
            FileWriter fw = new FileWriter("leaderboard.txt", true);
            fw.write(name + "," + score + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public Map<String, Integer> get() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("leaderboard.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] x = line.split(",");
                leaderboard.put(x[0], Integer.parseInt(x[1]));
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        Map<String, Integer> map = sortByValue(leaderboard);
        return map;
    }

    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortedMap) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(unsortedMap.entrySet());
        MergeSort.mergeSort(list, list.size());

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
