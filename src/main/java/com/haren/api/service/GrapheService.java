package com.haren.api.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class GrapheService {

    public byte[] getPatrimoineGraph(String nom_patrimoine, LocalDate debut, LocalDate fin) {
        return new byte[0];
    }
}
