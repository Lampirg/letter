package dev.lampirg.letter;

import dev.lampirg.letter.controller.LetterCountController;
import dev.lampirg.letter.logic.LetterCounter;
import lombok.SneakyThrows;
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

import java.util.Map;

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
    void test() {
        String inputJson = """
                {
                    "string": "aaaaabcccc"
                }
                """;
        String outputJson = """
                {
                    ""a": 5, "c": 4, "b": 1"
                }
                """;
        Mockito.when(letterCounter.countLetters("aaaaabcccc"))
                .thenReturn("\"a\": 5, \"c\": 4, \"b\": 1");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/count")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(inputJson)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(outputJson, true));
    }
}
