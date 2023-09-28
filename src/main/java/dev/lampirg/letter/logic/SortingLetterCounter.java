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
    public Map<Character, Integer> countLetters(String input) {
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
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (v1, v2) -> {
                                    throw new IllegalStateException("duplicates in entry stream");
                                },
                                LinkedHashMap::new
                        )
                );
    }
}
