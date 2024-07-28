package com.haren.api.service;

import org.springframework.stereotype.Service;
import school.hei.patrimoine.modele.FluxImpossibles;
import java.time.LocalDate;
import java.util.List;

@Service
public class FluxImpossibleService {

    public List<FluxImpossibles> getPatrimoineFluxImpossibles(String nomPatrimoine, LocalDate debut, LocalDate fin) {
        return List.of();
    }
}
