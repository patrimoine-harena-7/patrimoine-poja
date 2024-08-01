package com.haren.api.service;

import com.haren.api.repository.model.PatrimoineRepository;
import com.haren.api.repository.model.PossessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.possession.Possession;

import java.io.File;
import java.util.*;

@Service
public class PossessionService {

    @Autowired
    private PatrimoineService patrimoineService;

    @Autowired
    private PossessionRepository possessionRepository;


    public List<Possession> getPatrimoinePossessions(String nomPatrimoine, Integer page, Integer pageSize) {
        Patrimoine patrimoine = patrimoineService.getPatrimoineByName(nomPatrimoine);
        if (patrimoine == null) {
            return List.of();
        }
        return possessionRepository.getPatrimoinePossessions(page, pageSize, nomPatrimoine);
    }

    public List<Possession> crupdatePatrimoinePossessions(String nomPatrimoine, List<Possession> possessions) {
        Patrimoine patrimoine = patrimoineService.getPatrimoineByName(nomPatrimoine);
        if (patrimoine == null) {
            throw new IllegalArgumentException("The heritage with the given name does not exist.");
        }
        Set<Possession> updatedPossessions = new HashSet<>(patrimoine.possessions());
        updatedPossessions.addAll(possessions);
        Patrimoine updatedPatrimoine = new Patrimoine(
                patrimoine.nom(),
                patrimoine.possesseur(),
                patrimoine.t(),
                updatedPossessions
        );
        possessionRepository.crupdatePatrimoinePossessions(new File("path-to-patrimoine-file"));
        return new ArrayList<>(updatedPossessions);
    }

    public Optional<Possession> getPatrimoinePossessionByNom(String nomPatrimoine, String nomPossession) {
        return Optional.ofNullable(possessionRepository.getPatrimoinePossessionByNom(nomPossession, nomPatrimoine));
    }

    public void deletePatrimoinePossessionByNom(String nomPatrimoine, String nomPossession) {
        possessionRepository.deletePatrimoinePossessionByNom(nomPossession, nomPatrimoine);
    }
}
