package spaceinvaders;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MergeSort {

    public static void mergeSort(List<Map.Entry<String, Integer>> list, int size) {
        if (size < 2) {
            return;
        }
        int mid = size / 2;
        List<Map.Entry<String, Integer>> leftArray = new LinkedList<>();
        List<Map.Entry<String, Integer>> rightArray = new LinkedList<>();

        for (int i = 0; i < mid; i++) {
            leftArray.add(i, list.get(i));
        }
        for (int i = mid; i < size; i++) {
            rightArray.add(i - mid, list.get(i));
        }

        mergeSort(leftArray, mid);
        mergeSort(rightArray, size - mid);

        merge(list, leftArray, rightArray, mid, size - mid);
    }

    public static void merge(List<Map.Entry<String, Integer>> list, List<Map.Entry<String, Integer>> leftArray, List<Map.Entry<String, Integer>> rightArray, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {

            if (leftArray.get(i).getValue() <= rightArray.get(j).getValue()) {
                list.set(k++, (Map.Entry<String, Integer>) leftArray.get(i++));
            } else {
                list.set(k++, (Map.Entry<String, Integer>) rightArray.get(j++));
            }
        }
        while (i < left) {
            list.set(k++, (Map.Entry<String, Integer>) leftArray.get(i++));
        }
        while (j < right) {
            list.set(k++, (Map.Entry<String, Integer>) rightArray.get(j++));
        }
    }
}
