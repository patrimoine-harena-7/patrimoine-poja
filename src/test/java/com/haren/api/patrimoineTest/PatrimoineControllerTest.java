package com.haren.api.patrimoineTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haren.api.config.JacksonConfig;
import com.haren.api.endpoint.controller.patrimoine.PatrimoineController;
import com.haren.api.service.PatrimoineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Argent;
import school.hei.patrimoine.modele.possession.Possession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PatrimoineController.class)
public class PatrimoineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatrimoineService patrimoineService;

    private ObjectMapper objectMapper;

    private LocalDate date = LocalDate.now();

    @BeforeEach
    public void setUp() {
        objectMapper = JacksonConfig.createObjectMapper();
    }

    @Test
    public void testGetPatrimoineByName() throws Exception {
        Devise devise = new Devise("Euro", 4500, LocalDate.now(), 0.8);

        Set<Possession> possessions = new HashSet<>();
        possessions.add(new Argent("Argent", date, 1000, devise));

        Patrimoine patrimoine = new Patrimoine(
                "patrimoine1",
                new Personne("Zety"),
                LocalDate.now(),
                possessions
        );

        Mockito.when(patrimoineService.getPatrimoineByName("patrimoine1")).thenReturn(patrimoine);

        mockMvc.perform(get("/patrimoines/{nom_patrimoine}", "patrimoine1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nom").value("patrimoine1"))
                .andExpect(jsonPath("$.possesseur.nom").value("Zety"))
                .andExpect(jsonPath("$.possessions[0].nom").value("Argent"))
                .andExpect(jsonPath("$.possessions[0].valeurComptable").value(1000));
    }

    @Test
    public void testGetPatrimoines() throws Exception {

        Devise devise = new Devise("Euro", 4500, LocalDate.now(), 0.8);

        Set<Possession> possessions = new HashSet<>();
        possessions.add(new Argent("Argent", LocalDate.now(), 1000, devise));

        List<Patrimoine> patrimoinesList = new ArrayList<>();
        patrimoinesList.add(new Patrimoine(
                "patrimoine1",
                new Personne("Zety"),
                LocalDate.now(),
                possessions
        ));

        Mockito.when(patrimoineService.getPatrimoines(0, 10)).thenReturn(patrimoinesList);

        mockMvc.perform(get("/patrimoines?page=0&pageSize=10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nom").value("patrimoine1"))
                .andExpect(jsonPath("$[0].possesseur.nom").value("Zety"))
                .andExpect(jsonPath("$[0].possessions[0].nom").value("Argent"))
                .andExpect(jsonPath("$[0].possessions[0].valeurComptable").value(1000));
    }


    @Test
    public void testCrupdatePatrimoines() throws Exception {
        Devise devise = new Devise("Euro", 4500, date, 0.8);

        Set<Possession> possessions = new HashSet<>();
        possessions.add(new Argent("Argent", LocalDate.now(), 1000, devise));

        List<Patrimoine> patrimoines = new ArrayList<>();
        patrimoines.add(new Patrimoine(
                "patrimoine1",
                new Personne("Zety"),
                LocalDate.now(),
                possessions
        ));

        Mockito.when(patrimoineService.crupdatePatrimoines(patrimoines)).thenReturn(patrimoines);

        mockMvc.perform(MockMvcRequestBuilders.put("/patrimoines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patrimoines)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nom").value("patrimoine1"))
                .andExpect(jsonPath("$[0].possesseur.nom").value("Zety"))
                .andExpect(jsonPath("$[0].possessions[0].nom").value("Argent"))
                .andExpect(jsonPath("$[0].possessions[0].valeurComptable").value(1000));

    }
}
