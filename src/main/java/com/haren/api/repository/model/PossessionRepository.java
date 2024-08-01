package com.haren.api.repository.model;

import com.haren.api.file.CustomBucketComponent;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;
import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.possession.Possession;
import school.hei.patrimoine.serialisation.Serialiseur;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Repository
public class PossessionRepository {
    private CustomBucketComponent customBucketComponent;
    private Serialiseur serialiseur;

    public PossessionRepository(CustomBucketComponent customBucketComponent, Serialiseur serialiseur) {
        this.customBucketComponent = customBucketComponent;
        this.serialiseur = serialiseur;
    }

    public Patrimoine crupdatePatrimoinePossessions(File file) {
        try {
            String convertPatrimoinePossessionsAsString = FileUtils.readFileToString(file, UTF_8);
            return (Patrimoine) serialiseur.deserialise(convertPatrimoinePossessionsAsString);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Possession> getPatrimoinePossessions(int page, int size, String nomPatrimoine) {
        List<File> files = customBucketComponent.loadFilesFromS3(page, size);
        List<File> filteredFile =
                files.stream().filter(file -> file.getName().contains(nomPatrimoine)).toList();

        return filteredFile.stream().map(this::crupdatePossession).toList();
    }

    public Possession crupdatePossession(File file) {
        try {
            String convertPossessionAsString = FileUtils.readFileToString(file, UTF_8);
            return (Possession) serialiseur.deserialise(convertPossessionAsString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Possession getPatrimoinePossessionByNom(String nomPossession, String nomPatrimoine) {
        File file =
                customBucketComponent
                        .loadFilesFromS3(nomPossession)
                        .orElseThrow(() -> new RuntimeException(nomPossession));

        return crupdatePossession(file);
    }

    public void deletePatrimoinePossessionByNom(String nomPossession, String nomPatrimoine) {
        customBucketComponent.deleteFileFromS3Bucket(nomPossession);
    }
}
