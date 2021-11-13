package fileAnalyzer;

import java.util.List;

public class Print {
    static void printResultFromFileAnalyzer(List<String> stringList, int count) {
        System.out.printf("Count = %d \n", count);
        for (int i = 0; i < stringList.size(); i++) {
            System.out.printf("%d) %s \n", i + 1, stringList.get(i));
        }
    }
}
