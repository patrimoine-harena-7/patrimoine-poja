package com.haren.api.possession;

import com.haren.api.endpoint.controller.possession.PossessionController;
import com.haren.api.service.PossessionService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PossessionController.class)
public class PossessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PossessionService possessionService;

    private List<Possession> mockPossessions;

    @BeforeEach
    void setUp() {
        Possession possession1 = new Possession();
        possession1.setId(1L);
        possession1.setName("Possession1");

        Possession possession2 = new Possession();
        possession2.setId(2L);
        possession2.setName("Possession2");

        mockPossessions = Arrays.asList(possession1, possession2);

        when(possessionService.getPatrimoinePossessions("somePatrimoine", null, null))
                .thenReturn(mockPossessions);
    }

    @Test
    public void testGetPatrimoinePossessions() throws Exception {
        mockMvc.perform(get("/patrimoines/somePatrimoine/possessions"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Possession1\"},{\"id\":2,\"name\":\"Possession2\"}]"));
    }

    @Test
    public void testCrupdatePatrimoinePossessions() throws Exception {
        when(possessionService.crupdatePatrimoinePossessions("somePatrimoine", mockPossessions))
                .thenReturn(mockPossessions);

        String possessionsJson = "[{\"id\":1,\"name\":\"Possession1\"},{\"id\":2,\"name\":\"Possession2\"}]";

        mockMvc.perform(put("/patrimoines/somePatrimoine/possessions")
                        .contentType("application/json")
                        .content(possessionsJson))
                .andExpect(status().isOk())
                .andExpect(content().json(possessionsJson));
    }

    @Test
    public void testGetPatrimoinePossessionByNom() throws Exception {
        Possession possession = new Possession();
        possession.setId(1L);
        possession.setName("Possession1");

        when(possessionService.getPatrimoinePossessionByNom("somePatrimoine", "somePossession"))
                .thenReturn(Optional.of(possession));

        mockMvc.perform(get("/patrimoines/somePatrimoine/possessions/somePossession"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Possession1\"}"));
    }

    @Test
    public void testDeletePatrimoinePossessionByNom() throws Exception {
        Mockito.doNothing().when(possessionService)
                .deletePatrimoinePossessionByNom("somePatrimoine", "somePossession");

        mockMvc.perform(delete("/patrimoines/somePatrimoine/possessions/somePossession"))
                .andExpect(status().isNoContent());
    }
}
