package com.haren.api.patrimoine;

import com.haren.api.endpoint.controller.patrimoine.PatrimoineController;
import com.haren.api.service.PatrimoineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

@WebMvcTest(PatrimoineController.class)
public class PatrimoineControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PatrimoineService patrimoineService;

    private List<Patrimoine> mockPatrimoines;

    @BeforeEach
    void setUp() {
        Patrimoine patrimoine1 = new Patrimoine();
        Patrimoine patrimoine2 = new Patrimoine();
        mockPatrimoines = Arrays.asList(patrimoine1, patrimoine2);

        when(patrimoineService.getPatrimoines(null, null)).thenReturn(mockPatrimoines);
        when(patrimoineService.getPatrimoineByName("someName")).thenReturn(patrimoine1);
    }

    @Test
    public void testGetPatrimoines() throws Exception {
        mockMvc.perform(get("/patrimoines"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testGetPatrimoineByName() throws Exception {
        mockMvc.perform(get("/patrimoines/someName"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    public void testCrupdatePatrimoines() throws Exception {
        mockMvc.perform(put("/patrimoines")
                        .contentType("application/json")
                        .content("[]"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

}
