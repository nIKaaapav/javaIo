package fileAnalyzer;

import java.util.List;

public class Result {
    final int count;
    final List<String> sentence;

    public Result(int count, List<String> sentence) {
        this.count = count;
        this.sentence = sentence;
    }
}
