package dev.lampirg.letter.logic;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SortingLetterCounter implements LetterCounter {
    @Override
    public String countLetters(String input) {
        return input.chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.toMap(
                        ch -> ch,
                        ch -> 1,
                        Integer::sum,
                        HashMap::new
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(entry -> String.format("\"%s\": %d", entry.getKey(), entry.getValue()))
                .collect(
                        Collectors.joining(", ")
                );
    }
}
