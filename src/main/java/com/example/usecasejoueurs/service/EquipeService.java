package com.example.usecasejoueurs.service;

import com.example.usecasejoueurs.dto.EquipeDTO;
import com.example.usecasejoueurs.entity.Equipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface EquipeService {
    Page<EquipeDTO> getAllEquipes(Pageable pageable);
    Equipe saveEquipe(Equipe equipe);
}
