package dev.lampirg.letter;

import dev.lampirg.letter.json.Symbol;
import dev.lampirg.letter.json.Symbols;
import dev.lampirg.letter.logic.LetterCounter;
import dev.lampirg.letter.logic.SortingLetterCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DisplayName("Test SortingLetterCounter class")
public class TestSortingLetterCounter {

    LetterCounter letterCounter = new SortingLetterCounter();

    @Test
    @DisplayName("Test counting letters in string")
    void testSimpleString() {
        String input = "aaaaabcccc";
        Symbols expected = new Symbols(List.of(
                new Symbol('a', 5),
                new Symbol('c', 4),
                new Symbol('b', 1)
        ));
        Symbols actual = letterCounter.countLetters(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test counting symbols in string with quotes")
    void testStringWithQuotes() {
        String input = "aaaaab\"cccc\"";
        Symbols expected = new Symbols(List.of(
                new Symbol('a', 5),
                new Symbol('c', 4),
                new Symbol('"', 2),
                new Symbol('b', 1)
        ));
        Symbols actual = letterCounter.countLetters(input);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test counting symbols in string with single quotes")
    void testStringWithSingleQuotes() {
        String input = "aaaaab'cccc'";
        Symbols expected = new Symbols(List.of(
                new Symbol('a', 5),
                new Symbol('c', 4),
                new Symbol('\'', 2),
                new Symbol('b', 1)
        ));
        Symbols actual = letterCounter.countLetters(input);
        Assertions.assertEquals(expected, actual);
    }
}
