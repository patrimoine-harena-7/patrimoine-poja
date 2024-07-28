package com.haren.api.service;
import org.springframework.stereotype.Service;
import school.hei.patrimoine.modele.Patrimoine;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatrimoineService {
    private List<Patrimoine> patrimoineList = new ArrayList<>();

    public List<Patrimoine> getPatrimoines(Integer page, Integer pageSize) {

        return List.of();
    }

    public List<Patrimoine> crupdatePatrimoines(List<Patrimoine> patrimoines) {

        return List.of();
    }

    public Patrimoine getPatrimoineByNom(String nom_patrimoine) {
        return null;
    }

}
