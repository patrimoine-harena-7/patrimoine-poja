package com.haren.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.possession.Possession;

import java.util.*;

@Service
public class PossessionService {
    @Autowired
    private PatrimoineService patrimoineService;

    private List<Patrimoine> patrimoineList = new ArrayList<>();

    public List<Possession> getPatrimoinePossessions(
            String nomPatrimoine, Integer page, Integer pageSize) {

        Patrimoine patrimoine = patrimoineService.getPatrimoineByName(nomPatrimoine);

        if (patrimoine == null) {
            return List.of();
        }

        Set<Possession> possessionsSet = patrimoine.possessions();
        List<Possession> possessionsList = new ArrayList<>(possessionsSet);

        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, possessionsList.size());
        if (start > end) {
            return List.of();
        }
        return possessionsList.subList(start, end);
    }

    public List<Possession> crupdatePatrimoinePossessions(
            String nomPatrimoine, List<Possession> possessions) {

        Patrimoine patrimoine = patrimoineService.getPatrimoineByName(nomPatrimoine);
        if (patrimoine == null) {
            throw new IllegalArgumentException("The heritage with the given name does not exist.");
        }
            Set<Possession> updatedPossessions = new HashSet<>(patrimoine.possessions());

            for (Possession newPossession : possessions) {
                updatedPossessions.removeIf(p -> p.getNom().equals(newPossession.getNom()));
                updatedPossessions.add(newPossession);
            }

            Patrimoine updatedPatrimoine = new Patrimoine(
                    patrimoine.nom(),
                    patrimoine.possesseur(),
                    patrimoine.t(),
                    updatedPossessions
            );

            patrimoineService.crupdatePatrimoines(List.of(updatedPatrimoine));

            return new ArrayList<>(updatedPossessions);

    }


    public Optional<Possession> getPatrimoinePossessionByNom(
            String nomPatrimoine, String nomPossession) {
        return patrimoineList.stream()
                .filter(p -> p.nom().equals(nomPatrimoine))
                .flatMap(patrimoine -> patrimoine.possessions().stream())
                .filter(possession -> possession.getNom().equals(nomPossession))
                .findFirst();
    }

    public void deletePatrimoinePossessionByNom(
            String nomPatrimoine, String nomPossession) {
    }
}
