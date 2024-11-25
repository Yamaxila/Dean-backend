package by.vstu.dean.core.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    public static <Q> List<List<Q>> splitList(List<Q> originalList, int chunkSize) {
        List<List<Q>> chunks = new ArrayList<>();
        for (int i = 0; i < originalList.size(); i += chunkSize) {
            int end = Math.min(i + chunkSize, originalList.size());
            chunks.add(new ArrayList<>(originalList.subList(i, end)));
        }
        return chunks;
    }

}
