package com.haren.api.endpoint.controller.patrimoine;

import com.haren.api.service.PatrimoineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hei.patrimoine.modele.Patrimoine;

import java.util.List;

@RestController
@RequestMapping("/patrimoines")
public class PatrimoineController {

    @Autowired
    private PatrimoineService patrimoineService;

    @GetMapping
    public ResponseEntity<List<Patrimoine>> getPatrimoines(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize) {
        return ResponseEntity.ok(patrimoineService.getPatrimoines(page, pageSize));
    }

    @PutMapping
    public ResponseEntity<List<Patrimoine>> crupdatePatrimoines(
            @RequestBody List<Patrimoine> patrimoines) {
        return ResponseEntity.ok(patrimoineService.crupdatePatrimoines(patrimoines));
    }

    @GetMapping("/{nom_patrimoine}")
    public ResponseEntity<Patrimoine> getPatrimoineByName(@PathVariable String nom_patrimoine) {
        return ResponseEntity.ok(patrimoineService.getPatrimoineByName(nom_patrimoine));
    }
}
