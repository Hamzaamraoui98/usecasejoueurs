package com.example.usecasejoueurs.controller;

import com.example.usecasejoueurs.dto.EquipeDTO;
import com.example.usecasejoueurs.dto.JoueurDTO;
import com.example.usecasejoueurs.entity.Equipe;
import com.example.usecasejoueurs.entity.Joueur;
import com.example.usecasejoueurs.mappers.EquipeMapper;
import com.example.usecasejoueurs.mappers.JoueurMapper;
import com.example.usecasejoueurs.service.EquipeService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/equipes")
public class EquipeController {
    private final EquipeService equipeService;
    private final EquipeMapper equipeMapper;
    private final JoueurMapper joueurMapper;


    public EquipeController(EquipeService equipeService, EquipeMapper equipeMapper, JoueurMapper joueurMapper) {
        this.equipeService = equipeService;
        this.equipeMapper=equipeMapper;
        this.joueurMapper=joueurMapper;
    }

    @GetMapping("/")
    ResponseEntity<Page<EquipeDTO>> getAllEquipes(Pageable pageable) {
        Page<EquipeDTO> equipes = equipeService.getAllEquipes(pageable);
        return ResponseEntity.ok(equipes);
    }

    @PostMapping("/save")
    public ResponseEntity<EquipeDTO> saveEquipe(@RequestBody @Valid EquipeDTO equipeDTO) {
        Equipe equipe = this.equipeMapper.dtoToEntity(equipeDTO);
        equipe = equipeService.saveEquipe(equipe);
        EquipeDTO savedEquipeDTO = this.equipeMapper.entityToDTO(equipe);
        return ResponseEntity.ok(savedEquipeDTO);
    }

}

