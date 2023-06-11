package com.example.usecasejoueurs.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class EquipeDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String acronym;
    @NotNull
    private Double budget;
    @Valid
    private Set<JoueurDTO> joueurs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Set<JoueurDTO> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(Set<JoueurDTO> joueurs) {
        this.joueurs = joueurs;
    }
}
