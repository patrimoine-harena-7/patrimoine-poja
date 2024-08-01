package com.haren.api.repository.model;


import static java.nio.charset.StandardCharsets.UTF_8;

import com.haren.api.file.CustomBucketComponent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;
import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.serialisation.Serialiseur;

@Repository
public class PatrimoineRepository {
    private CustomBucketComponent customBucketComponent;
    private Serialiseur serialiseur;

    public PatrimoineRepository(CustomBucketComponent customBucketComponent) {
        this.customBucketComponent = customBucketComponent;
        this.serialiseur = new Serialiseur<Patrimoine>();
    }

    public Patrimoine crupdatePatrimoines(File file) {
        try {
            String convertPatrimoineAsString = FileUtils.readFileToString(file, UTF_8);
            return (Patrimoine) serialiseur.deserialise(convertPatrimoineAsString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Patrimoine> getListPatrimoines(int page, int size) {
        List<File> files = customBucketComponent.loadFilesFromS3(page, size);
        return files.stream().map(this::crupdatePatrimoines).toList();
    }

    public Patrimoine getPatrimoineByNom(String nomPatrimoine) {
        File file =
                customBucketComponent
                        .loadFilesFromS3(nomPatrimoine)
                        .orElseThrow(() -> new RuntimeException(nomPatrimoine));

        return crupdatePatrimoines(file);
    }
}
