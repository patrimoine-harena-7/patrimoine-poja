package com.haren.api.service;

import org.springframework.stereotype.Service;
import school.hei.patrimoine.modele.Patrimoine;

import java.util.*;

@Service
public class PatrimoineService {

    private List<Patrimoine> patrimoineList = new ArrayList<>();

    public List<Patrimoine> getPatrimoines(Integer page, Integer pageSize) {
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, patrimoineList.size());
        if (start > end) {
            return List.of();
        }
        return patrimoineList.subList(start, end);
    }

    public List<Patrimoine> crupdatePatrimoines(List<Patrimoine> patrimoines) {

        Map<String, Patrimoine> patrimoineMap = new HashMap<>();

        for (Patrimoine existingPatrimoines  : patrimoines) {
            patrimoineMap.put(existingPatrimoines.nom(), existingPatrimoines);
        }

        for (Patrimoine newPatrimoine : patrimoines) {
            patrimoineMap.put(newPatrimoine.nom(), newPatrimoine);
        }

        return new ArrayList<>(patrimoineMap.values());
    }

    public Patrimoine getPatrimoineByNom(String nom_patrimoine) {
        return null;
    }
}
