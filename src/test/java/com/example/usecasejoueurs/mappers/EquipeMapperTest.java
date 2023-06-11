package com.example.usecasejoueurs.mappers;

import com.example.usecasejoueurs.dto.EquipeDTO;
import com.example.usecasejoueurs.dto.JoueurDTO;
import com.example.usecasejoueurs.entity.Equipe;
import com.example.usecasejoueurs.entity.Joueur;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EquipeMapperTest {

    private final EquipeMapper equipeMapper = new EquipeMapperImpl();

    @Test
    public void testEntityToDTO() {
        // Arrange
        Equipe equipeWithouthJoueurs = new Equipe();
        equipeWithouthJoueurs.setId(1L);
        equipeWithouthJoueurs.setName("Team");
        equipeWithouthJoueurs.setAcronym("T");
        equipeWithouthJoueurs.setBudget(1000.0);
        equipeWithouthJoueurs.setJoueurs(null);
        Equipe equipeWithJoueurs = new Equipe();
        equipeWithJoueurs.setId(1L);
        equipeWithJoueurs.setName("Team");
        equipeWithJoueurs.setAcronym("T");
        equipeWithJoueurs.setBudget(1000.0);
        Joueur joueur1 = new Joueur();
        joueur1.setId(1L);
        joueur1.setName("Joueur1");
        joueur1.setPosition("Position1");
        Joueur joueur2 = new Joueur();
        joueur2.setId(2L);
        joueur2.setName("Joueur2");
        joueur2.setPosition("Position2");
        equipeWithJoueurs.setJoueurs(Set.of(joueur1, joueur2));

        // Act
        EquipeDTO equipeDTOWithouthJoueurs = equipeMapper.entityToDTO(equipeWithouthJoueurs);
        EquipeDTO equipeDTOWithJoueurs = equipeMapper.entityToDTO(equipeWithJoueurs);
        // Assert withouth joueurs
        assertEquals(equipeWithouthJoueurs.getName(), equipeDTOWithouthJoueurs.getName());
        assertEquals(equipeWithouthJoueurs.getAcronym(), equipeDTOWithouthJoueurs.getAcronym());
        assertEquals(equipeWithouthJoueurs.getBudget(), equipeDTOWithouthJoueurs.getBudget());
        assertEquals(equipeWithouthJoueurs.getJoueurs(), equipeDTOWithouthJoueurs.getJoueurs());
        // Assert with joueurs
        assertEquals(equipeWithJoueurs.getName(), equipeDTOWithJoueurs.getName());
        assertEquals(equipeWithJoueurs.getAcronym(), equipeDTOWithJoueurs.getAcronym());
        assertEquals(equipeWithJoueurs.getBudget(), equipeDTOWithJoueurs.getBudget());
        assertEquals(equipeWithJoueurs.getJoueurs().size(), equipeDTOWithJoueurs.getJoueurs().size());
        assertEquals(equipeWithJoueurs.getJoueurs().iterator().next().getName(), equipeDTOWithJoueurs.getJoueurs().iterator().next().getName());



    }

    @Test
    public void testDTOToEntity() {
        // Arrange
        EquipeDTO equipeDTO = new EquipeDTO();
        equipeDTO.setName("Team");
        equipeDTO.setAcronym("T");
        equipeDTO.setBudget(1000.0);
        JoueurDTO joueur1 = new JoueurDTO();
        joueur1.setName("Joueur1");
        joueur1.setPosition("Position1");
        JoueurDTO joueur2 = new JoueurDTO();
        joueur2.setName("Joueur2");
        joueur2.setPosition("Position2");

        equipeDTO.setJoueurs(Set.of(joueur1, joueur2));

        // Act
        Equipe equipe = equipeMapper.dtoToEntity(equipeDTO);

        // Assert
        assertEquals(equipeDTO.getName(), equipe.getName());
        assertEquals(equipeDTO.getAcronym(), equipe.getAcronym());
        assertEquals(equipeDTO.getBudget(), equipe.getBudget());
        assertEquals(equipeDTO.getJoueurs().size(), equipe.getJoueurs().size());
        assertEquals(equipeDTO.getJoueurs().iterator().next().getName(), equipe.getJoueurs().iterator().next().getName());

    }
}
