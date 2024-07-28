package com.haren.api.endpoint.controller.projectionFuture;

import com.haren.api.service.GrapheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/patrimoines/{nom_patrimoine}/graphe")
public class GrapheController {

    @Autowired
    private GrapheService grapheService;

    @GetMapping
    public ResponseEntity<byte[]> getPatrimoineGraph(@PathVariable String nom_patrimoine,
                                                     @RequestParam LocalDate debut,
                                                     @RequestParam LocalDate fin) {
        byte[] image = grapheService.getPatrimoineGraph(nom_patrimoine, debut, fin);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
}
