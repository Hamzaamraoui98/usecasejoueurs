package com.example.usecasejoueurs.mappers;

import com.example.usecasejoueurs.dto.EquipeDTO;
import com.example.usecasejoueurs.entity.Equipe;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface EquipeMapper {
    Equipe dtoToEntity(EquipeDTO equipeDTO);
    EquipeDTO entityToDTO(Equipe equipe);

}
