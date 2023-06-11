package com.example.usecasejoueurs.dto;

import javax.validation.constraints.NotEmpty;

public class JoueurDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
