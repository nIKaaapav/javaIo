package fileAnalyzer;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileAnalyzerTest {

    @Test
    public void testSplitTextIntoSentencesWorkCorrect() throws IOException {
        String[] stringsResult = {"Hi world!", "Nika,test Pavlenko.", "test test!", "text."};

        File file = new File("data/story2.txt");
        FileInputStream fis = new FileInputStream(file);
        List<String> splitTextIntoSentences = FileAnalyzer.splitTextIntoSentences(fis);
        assertEquals(stringsResult[0], splitTextIntoSentences.get(0));
        assertEquals(stringsResult[1], splitTextIntoSentences.get(1));
        assertEquals(stringsResult[2], splitTextIntoSentences.get(2));
        assertEquals(stringsResult[3], splitTextIntoSentences.get(3));
    }

    @Test
    public void testContainsWithValueContaintsInArray() {
        char[] sentencesSeparators = {'!', '?', '.', ';'};

        boolean containsOne = FileAnalyzer.contains(sentencesSeparators, '!');
        boolean containsTwo = FileAnalyzer.contains(sentencesSeparators, '?');
        boolean containsThree = FileAnalyzer.contains(sentencesSeparators, '.');
        boolean containsFour = FileAnalyzer.contains(sentencesSeparators, ';');

        assertTrue(containsOne);
        assertTrue(containsTwo);
        assertTrue(containsThree);
        assertTrue(containsFour);
    }

    @Test
    public void testContainsWithValueNotContaintsInArray() {
        char[] sentencesSeparators = {'!', '?', '.', ';'};

        boolean contains = FileAnalyzer.contains(sentencesSeparators, ',');

        assertFalse(contains);
    }

    @Test
    public void testGetSentencesWithWordWhichContainsInList() {
        String[] strings = {"Hi world!", "Nika Pavlenko test.", "test, test!", "text."};
        List<String> stringsList = Arrays.asList(strings);

        String[] stringsActual = {"Nika Pavlenko test.", "test, test!"};

        List<String> result = FileAnalyzer.getSentencesWithWord(stringsList, "test");

        assertEquals(2, result.size());
        assertEquals(stringsActual[0], result.get(0));
        assertEquals(stringsActual[1], result.get(1));

    }

    @Test
    public void testGetSentencesWithWordWhichNotContainsInList() {
        String[] strings = {"Hi world!", "Nika Pavlenko test.", "test, test!", "text."};
        List<String> stringsList = Arrays.asList(strings);

        List<String> result = FileAnalyzer.getSentencesWithWord(stringsList, "result");

        assertEquals(0, result.size());
    }

    @Test
    public void getCountWordInStingFromEnd() {
        int countWordInSting = FileAnalyzer.getCountWordInSting("Nika Pavlenko test.", "test");

        assertEquals(1, countWordInSting);
    }

    @Test
    public void getCountWordInStingFromStart() {
        String strings = "Test method from start";
        int countWordInSting = FileAnalyzer.getCountWordInSting(strings, "test");

        assertEquals(1, countWordInSting);
    }

    @Test
    public void getCountWordInStingFromMiddle() {
        String strings = " method from test, test start";
        int countWordInSting = FileAnalyzer.getCountWordInSting(strings, "test");

        assertEquals(2, countWordInSting);
    }

    @Test
    public void getCountWordWitchNoContainsOnString() {
        String strings = " method from start";
        int countWordInSting = FileAnalyzer.getCountWordInSting(strings, "test");

        assertEquals(0, countWordInSting);
    }

    @Test
    public void getCountWordInTextAndSentenceWithThisWord() throws IOException {
        String[] stringsExpected = {"Hi world!", "Nika,test Pavlenko.", "test test!", "text."};
        Result resultExpected = FileAnalyzer.getCountWordInTextAndSentenceWithThisWord("data/story2.txt", "test");

        assertEquals(3, resultExpected.count);
        assertEquals(stringsExpected[1], resultExpected.sentence.get(0));
        assertEquals(stringsExpected[2], resultExpected.sentence.get(1));
        assertEquals(2, resultExpected.sentence.size());
    }
}
