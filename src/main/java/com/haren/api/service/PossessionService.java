package com.haren.api.service;

import org.springframework.stereotype.Service;
import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.possession.Possession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PossessionService {

    private List<Patrimoine> patrimoineList = new ArrayList<>();
    public List<Possession> getPatrimoinePossessions(String nomPatrimoine, Integer page, Integer pageSize) {

        return List.of();
    }

    public List<Possession> crupdatePatrimoinePossessions(String nomPatrimoine, List<Possession> possessions) {

        return List.of();
    }

    public Optional<Possession> getPatrimoinePossessionByNom(String nomPatrimoine, String nomPossession) {
        return patrimoineList.stream()
                .filter(p -> p.nom().equals(nomPatrimoine))
                .flatMap(patrimoine -> patrimoine.possessions().stream())
                .filter(possession -> possession.getNom().equals(nomPossession))
                .findFirst();
    }

    public void deletePatrimoinePossessionByNom(String nomPatrimoine, String nomPossession) {
    }
}
