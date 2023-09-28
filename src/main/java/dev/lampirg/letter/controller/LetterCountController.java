package dev.lampirg.letter.controller;

import dev.lampirg.letter.logic.LetterCounter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/count")
public class LetterCountController {

    private LetterCounter letterCounter;

    public LetterCountController(LetterCounter letterCounter) {
        this.letterCounter = letterCounter;
    }

    @PostMapping
    public Map<String, String> countLetters(@RequestBody Map<String, String> json) {
        return letterCounter.countLetters(Objects.requireNonNull(json.get("string")));
    }
}
