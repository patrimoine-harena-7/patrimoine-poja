package com.haren.api.service;

import com.haren.api.repository.model.PatrimoineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.hei.patrimoine.modele.Patrimoine;

import java.util.*;

@Service
public class PatrimoineService {

    @Autowired
    private PatrimoineRepository patrimoineRepository;

    public List<Patrimoine> getPatrimoines(Integer page, Integer pageSize) {
        return patrimoineRepository.getListPatrimoines(page, pageSize);
    }

    public Patrimoine getPatrimoineByName(String nomPatrimoine) {
        return patrimoineRepository.getPatrimoineByNom(nomPatrimoine);
    }

    public List<Patrimoine> crupdatePatrimoines(List<Patrimoine> patrimoines) {

        Map<String, Patrimoine> patrimoineMap = new HashMap<>();

        for (Patrimoine existingPatrimoines  : patrimoines) {
            patrimoineMap.put(existingPatrimoines.nom(), existingPatrimoines);
        }

        for (Patrimoine newPatrimoine : patrimoines) {
            patrimoineMap.put(newPatrimoine.nom(), newPatrimoine);
        }

        return patrimoines;
    }
}
