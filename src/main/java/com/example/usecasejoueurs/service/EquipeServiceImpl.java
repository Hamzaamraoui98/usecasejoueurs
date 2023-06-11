package com.example.usecasejoueurs.service;

import com.example.usecasejoueurs.dto.EquipeDTO;
import com.example.usecasejoueurs.entity.Equipe;
import com.example.usecasejoueurs.mappers.EquipeMapper;
import com.example.usecasejoueurs.repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EquipeServiceImpl implements EquipeService{
    EquipeRepository equipeRepository;
    EquipeMapper equipeMapper;

    public EquipeServiceImpl(EquipeRepository equipeRepository, EquipeMapper equipeMapper) {
        this.equipeRepository = equipeRepository;
        this.equipeMapper = equipeMapper;
    }

    @Override
    public Page<EquipeDTO> getAllEquipes(Pageable pageable) {
        return equipeRepository.findAll(pageable).map(equipe -> equipeMapper.entityToDTO(equipe));
    }

    @Override
    public Equipe saveEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }
}
