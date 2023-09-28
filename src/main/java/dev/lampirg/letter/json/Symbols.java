package dev.lampirg.letter.json;

import java.util.List;
import java.util.Map;

public record Symbols(List<Symbol> symbols) {
    public Symbols(Map<Character, Integer> map) {
        this(
                map.entrySet().stream()
                .map(entry -> new Symbol(entry.getKey(), entry.getValue()))
                .toList()
        );
    }
}
