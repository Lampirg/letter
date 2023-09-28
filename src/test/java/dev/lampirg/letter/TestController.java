package dev.lampirg.letter;

import dev.lampirg.letter.controller.LetterCountController;
import dev.lampirg.letter.json.Symbol;
import dev.lampirg.letter.json.Symbols;
import dev.lampirg.letter.logic.LetterCounter;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

@WebMvcTest(LetterCountController.class)
@DisplayName("Test LetterCountController class with LetterCounter being mocked")
public class TestController {

    @MockBean
    private LetterCounter letterCounter;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test controller")
    @SneakyThrows
    void testSimpleString() {
        String inputJson = """
                {
                    "string": "aaaaabcccc"
                }
                """;
        Mockito.when(letterCounter.countLetters("aaaaabcccc"))
                .thenReturn(new Symbols(List.of(
                        new Symbol('a', 5),
                        new Symbol('c', 4),
                        new Symbol('b', 1)
                )));
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/count")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputJson)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols[0].value", Matchers.is("a")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols[0].frequency", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols[1].value", Matchers.is("c")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols[1].frequency", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols[2].value", Matchers.is("b")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols[2].frequency", Matchers.is(1)));
    }

    @Test
    @DisplayName("Test controller")
    @SneakyThrows
    void testStringWithQuotes() {
        String inputJson = """
                {
                    "string": "aaaaab\\"cccc\\""
                }
                """;
        Mockito.when(letterCounter.countLetters("aaaaab\"cccc\""))
                .thenReturn(new Symbols(List.of(
                        new Symbol('a', 5),
                        new Symbol('c', 4),
                        new Symbol('"', 2),
                        new Symbol('b', 1)
                )));
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/count")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputJson)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols[0].value", Matchers.is("a")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols[0].frequency", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols[1].value", Matchers.is("c")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols[1].frequency", Matchers.is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols[2].value", Matchers.is("\"")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols[2].frequency", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols[3].value", Matchers.is("b")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols[3].frequency", Matchers.is(1)));;
    }
}
