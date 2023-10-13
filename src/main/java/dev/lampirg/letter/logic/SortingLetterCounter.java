package dev.lampirg.letter.logic;

import dev.lampirg.letter.json.Symbol;
import dev.lampirg.letter.json.Symbols;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SortingLetterCounter implements LetterCounter {
    @Override
    public Symbols countLetters(String input) {
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
                .map(entry -> new Symbol(entry.getKey(), entry.getValue()))
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                Symbols::new
                        )
                );
    }
}
