package fileAnalyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class FileAnalyzer {
    public static File file;
    static String word;
    private static char[] sentencesSeparators = {'!', '?', '.', ';'};

    static void startApp(String patch) throws IOException {
        file = new File("data/story.txt");

        FileInputStream fis = new FileInputStream(file);

        int countWordInFile = 0;

        List<String> splitTextIntoSentences = splitTextIntoSentences(fis);

        List<String> sentencesWithWordFromArray = getSentencesWithWordFromArray(splitTextIntoSentences);
        for (int i = 0; i < sentencesWithWordFromArray.size(); i++) {
            countWordInFile += getCountWordInSting(sentencesWithWordFromArray.get(i));
        }
        printResult(sentencesWithWordFromArray, countWordInFile);
    }

    static List<String> splitTextIntoSentences(FileInputStream fis) throws IOException {
        int value = 0;
        String strSentences = "";
        ArrayList<String> arrayString  = new ArrayList<String>();
        while (value != -1) {
            value = fis.read();
            strSentences += (char) value;
            if (contains(sentencesSeparators, (char) value)){
                arrayString.add(strSentences);
                strSentences = "";

            }
        }
        return arrayString;
    }

    static void printResult(List<String> stringList, int count){
        System.out.printf("Count = %d \n", count);
        for (int i = 0; i < stringList.size(); i++) {
            System.out.printf("%d) %s \n", i+1, stringList.get(i));
        }
    }

    static boolean contains(char[] array, char value){
        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(array[i], value)){
                return true;
            }
        }
        return false;
    }

    static int getCountWordInSting(String str){
        int count = 0;
        String[] strings = str.
                replace(',', ' ')
                .replace('!', ' ')
                .replace('?', ' ')
                .replace('.', ' ')
                .split(" ");
        for (int i = 0; i < strings.length; i++) {
             if (strings[i].equals(word)){
                 count++;
             }
        }
        return count;
    }

    static List<String> getSentencesWithWordFromArray(List<String> str){
        ArrayList<String> arraySentencesWithWord = new ArrayList<>();

        for (int i = 0; i < str.size(); i++) {
            String currentString = str.get(i);
            if (currentString.contains(word)){
                arraySentencesWithWord.add(currentString);
            }
        }
        return arraySentencesWithWord;
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new StringIndexOutOfBoundsException("the App needs two parameters: first - patch, second - word");
        }
        String path = args[0];
        word = args[1];
        System.out.printf("path = %s", path);
        System.out.printf("word = %s", word);
        startApp("data/story.txt");
    }
}
