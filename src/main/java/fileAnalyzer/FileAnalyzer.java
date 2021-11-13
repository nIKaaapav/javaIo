package fileAnalyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class FileAnalyzer {
    private static char[] sentencesSeparators = {'!', '?', '.', ';'};

    static Result getCountWordInTextAndSentenceWithThisWord(String patch, String word) throws IOException {
        File file = new File(patch);

        FileInputStream fis = new FileInputStream(file);

        int countWordInFile = 0;

        List<String> splitTextIntoSentences = splitTextIntoSentences(fis);

        List<String> sentencesWithWordFromArray = getSentencesWithWord(splitTextIntoSentences, word);
        for (String sentence : sentencesWithWordFromArray) {
            countWordInFile += getCountWordInSting(sentence, word);
        }

        return new Result(countWordInFile, sentencesWithWordFromArray);
    }

    static List<String> splitTextIntoSentences(FileInputStream fis) throws IOException {
        int value = 0;
        String strSentences = "";
        ArrayList<String> arrayString = new ArrayList<String>();

        while ((value = fis.read()) != -1) {
            strSentences += (char) value;
            if (contains(sentencesSeparators, (char) value)) {
                arrayString.add(strSentences.trim());
                strSentences = "";

            }
        }
        return arrayString;
    }

    static boolean contains(char[] array, char value) {
        for (char element: array) {
            if (Objects.equals(element, value)) {
                return true;
            }
        }
        return false;
    }

    static int getCountWordInSting(String str, String word) {
        int count = 0;
        String[] strings = str.
                replace(',', ' ')
                .replace('!', ' ')
                .replace('?', ' ')
                .replace('.', ' ')
                .split(" ");

        for (String value: strings) {
            if (value.toLowerCase().equals(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    static List<String> getSentencesWithWord(List<String> stringList, String word) {
        ArrayList<String> arraySentencesWithWord = new ArrayList<>();

        for (String strung: stringList) {
            String currentString = strung;
            if (currentString.contains(word)) {
                arraySentencesWithWord.add(currentString.trim());
            }
        }
        return arraySentencesWithWord;
    }
}
