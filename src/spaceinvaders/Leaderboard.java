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

    // init leaderboard hashmap
    private final HashMap leaderboard = new HashMap();

    // method to add new scores to text file
    public void add(String name, int score) {
        try {
            FileWriter fw = new FileWriter("leaderboard.txt", true);
            fw.write(name + "," + score + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    // gets the leaderboard map to display
    public Map<String, Integer> get() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("leaderboard.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] x = line.split(",");
                // check if the name has already got a high score, if the value being checked is higher
                // than the one already added to the map, then replace it
                boolean valid = checkValid(x);
                if (valid) {
                    leaderboard.put(x[0], Integer.parseInt(x[1]));
                }
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

    private boolean checkValid(String[] x) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(leaderboard.entrySet());
        for (Map.Entry<String, Integer> entry : list) {
            if (entry.getKey() != null) {
                if (entry.getKey().equals(x[0])) {
                    return Integer.parseInt(x[1]) >= entry.getValue();
                }
            }
        }
        return true;
    }
}
