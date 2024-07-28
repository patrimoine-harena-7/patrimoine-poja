package com.haren.api.projectionFuture;

import com.haren.api.endpoint.controller.projectionFuture.GrapheController;
import com.haren.api.service.GrapheService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.time.LocalDate;

@WebMvcTest
public class GrapheControllerTest {
    @Mock
    private GrapheService grapheService;

    @InjectMocks
    private GrapheController grapheController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPatrimoineGraph() {
        String nomPatrimoine = "testPatrimoine";
        LocalDate debut = LocalDate.of(2023, 1, 1);
        LocalDate fin = LocalDate.of(2023, 12, 31);
        byte[] expectedImage = new byte[]{1, 2, 3};

        when(grapheService.getPatrimoineGraph(nomPatrimoine, debut, fin))
                .thenReturn(expectedImage);

        ResponseEntity<byte[]> response = grapheController.getPatrimoineGraph(nomPatrimoine, debut, fin);

        assertEquals(200, response.getStatusCodeValue());
        assertArrayEquals(expectedImage, response.getBody());
        assertEquals(MediaType.IMAGE_PNG, response.getHeaders().getContentType());
        verify(grapheService, times(1))
                .getPatrimoineGraph(nomPatrimoine, debut, fin);
    }
}
