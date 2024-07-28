package com.haren.api.projectionFuture;

import com.haren.api.endpoint.controller.projectionFuture.FluxImpossibleController;
import com.haren.api.service.FluxImpossibleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.time.LocalDate;
import java.util.Collections;

@WebMvcTest(FluxImpossibleController.class)
public class FluxImpossibleControllerTest {
    @Mock
    private FluxImpossibleService fluxImpossibleService;

    @InjectMocks
    private FluxImpossibleController fluxImpossibleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPatrimoineFluxImpossibles() {
        String nomPatrimoine = "testPatrimoine";
        LocalDate debut = LocalDate.of(2023, 1, 1);
        LocalDate fin = LocalDate.of(2023, 12, 31);
        List<FluxImpossibles> fluxImpossiblesList = Collections.emptyList();

        when(fluxImpossibleService.getPatrimoineFluxImpossibles(nomPatrimoine, debut, fin))
                .thenReturn(fluxImpossiblesList);

        ResponseEntity<List<FluxImpossibles>> response = fluxImpossibleController.getPatrimoineFluxImpossibles(nomPatrimoine, debut, fin);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(fluxImpossiblesList, response.getBody());
        verify(fluxImpossibleService, times(1))
                .getPatrimoineFluxImpossibles(nomPatrimoine, debut, fin);
    }
}
