package com.haren.api.endpoint.controller.possession;

import com.haren.api.service.PossessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hei.patrimoine.modele.possession.Possession;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patrimoines")
public class PossessionController {

    @Autowired
    private PossessionService possessionService;

    @GetMapping("/{nom_patrimoine}/possessions")
    public ResponseEntity<List<Possession>> getPatrimoinePossessions(
            @PathVariable String nom_patrimoine,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize) {
        return ResponseEntity.ok(
                possessionService.getPatrimoinePossessions(nom_patrimoine, page, pageSize));
    }

    @PutMapping("/{nom_patrimoine}/possessions")
    public ResponseEntity<List<Possession>> crupdatePatrimoinePossessions(
            @PathVariable String nom_patrimoine,
            @RequestBody List<Possession> possessions) {
        return ResponseEntity.ok(
                possessionService.crupdatePatrimoinePossessions(nom_patrimoine, possessions));
    }

    @GetMapping("/{nom_patrimoine}/possessions/{nom_possession}")
    public ResponseEntity<Optional<Possession>> getPatrimoinePossessionByNom(
            @PathVariable String nom_patrimoine,
            @PathVariable String nom_possession) {
        return ResponseEntity.ok(
                possessionService.getPatrimoinePossessionByNom(nom_patrimoine, nom_possession));
    }

    @DeleteMapping("/{nom_patrimoine}/possessions/{nom_possession}")
    public ResponseEntity<Void> deletePatrimoinePossessionByNom(
            @PathVariable String nom_patrimoine,
            @PathVariable String nom_possession) {
        possessionService.deletePatrimoinePossessionByNom(nom_patrimoine, nom_possession);
        return ResponseEntity.noContent().build();
    }
}
