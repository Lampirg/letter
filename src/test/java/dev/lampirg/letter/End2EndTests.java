package dev.lampirg.letter;

import dev.lampirg.letter.json.Symbol;
import dev.lampirg.letter.json.Symbols;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Test e2e")
class End2EndTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Test application")
    @SneakyThrows
    void testSimpleString() {
        String inputJson = """
                {
                    "string": "aaaaabcccc"
                }
                """;
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
    @DisplayName("Test application with quotes in input")
    @SneakyThrows
    void testStringWithQuotes() {
        String inputJson = """
                {
                    "string": "aaaaab\\"cccc\\""
                }
                """;
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

    @Test
    @DisplayName("Test application with single quotes in input")
    @SneakyThrows
    void testStringWithSingleQuotes() {
        String inputJson = """
                {
                    "string": "aaaaab'cccc'"
                }
                """;
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols[2].value", Matchers.is("'")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols[2].frequency", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols[3].value", Matchers.is("b")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.symbols[3].frequency", Matchers.is(1)));;
    }

}
