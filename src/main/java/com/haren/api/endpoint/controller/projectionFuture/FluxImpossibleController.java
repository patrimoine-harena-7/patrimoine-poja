package com.haren.api.endpoint.controller.projectionFuture;

import com.haren.api.service.FluxImpossibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hei.patrimoine.modele.FluxImpossibles;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/patrimoines/{nom_patrimoine}/flux-impossibles")
public class FluxImpossibleController {

    @Autowired
    private FluxImpossibleService fluxImpossibleService;

    @GetMapping
    public ResponseEntity<List<FluxImpossibles>> getPatrimoineFluxImpossibles(@PathVariable String nom_patrimoine,
                                                                              @RequestParam LocalDate debut,
                                                                              @RequestParam LocalDate fin) {
        return ResponseEntity.ok(fluxImpossibleService.getPatrimoineFluxImpossibles(nom_patrimoine, debut, fin));
    }
}

