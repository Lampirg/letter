package dev.lampirg.letter;

import dev.lampirg.letter.logic.LetterCounter;
import dev.lampirg.letter.logic.SortingLetterCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;

@DisplayName("Test SortingLetterCounter class")
public class TestSortingLetterCounter {

    LetterCounter letterCounter = new SortingLetterCounter();

    @Test
    @DisplayName("Test counting letters in string")
    void testSimpleString() {
        String input = "aaaaabcccc";
        String expected = "\"a\": 5, \"c\": 4, \"b\": 1";
        String actual = letterCounter.countLetters(input)
                .entrySet()
                .stream()
                .map(entry -> String.format("\"%s\": %d", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", "));
        Assertions.assertEquals(expected, actual);
    }
}
