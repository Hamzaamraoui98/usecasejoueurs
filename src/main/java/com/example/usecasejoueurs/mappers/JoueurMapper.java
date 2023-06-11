package com.example.usecasejoueurs.mappers;

import com.example.usecasejoueurs.dto.JoueurDTO;
import com.example.usecasejoueurs.entity.Joueur;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface JoueurMapper {
    JoueurDTO entityToDto(Joueur joueurDTO);
    Joueur dtoToEntity(JoueurDTO joueur);
}
