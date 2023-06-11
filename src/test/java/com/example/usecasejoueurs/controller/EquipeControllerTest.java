package com.example.usecasejoueurs.controller;

import com.example.usecasejoueurs.dto.EquipeDTO;
import com.example.usecasejoueurs.dto.JoueurDTO;
import com.example.usecasejoueurs.entity.Equipe;
import com.example.usecasejoueurs.repository.EquipeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class EquipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EquipeRepository equipeRepository;

    @Test
    public void testGetAllEquipes() throws Exception {
        // Arrange
        Equipe equipe1 = new Equipe();
        equipe1.setName("Team 1");
        equipe1.setBudget(1000000.0);
        equipe1.setAcronym("T1");
        equipeRepository.save(equipe1);

        Equipe equipe2 = new Equipe();
        equipe2.setName("Team 2");
        equipe2.setBudget(2000000.0);
        equipe2.setAcronym("T2");
        equipeRepository.save(equipe2);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/equipes/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name", Matchers.is("Team 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].name", Matchers.is("Team 2")));
    }

    @Test
    public void testSaveEquipeWithoutJoueurs() throws Exception {
        // Arrange
        EquipeDTO equipeDTO = new EquipeDTO();
        equipeDTO.setName("New Team");
        equipeDTO.setBudget(1000000.0);
        equipeDTO.setAcronym("NT");


        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/equipes/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(equipeDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("New Team")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.budget", Matchers.is(1000000.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.acronym", Matchers.is("NT")));


        // Verify that the equipe was saved in the database
        List<Equipe> equipes = equipeRepository.findAll();
        assertEquals(1, equipes.size());
        assertEquals("New Team", equipes.get(0).getName());
        assertEquals(1000000.0, equipes.get(0).getBudget());
        assertEquals("NT", equipes.get(0).getAcronym());
    }
    @Test
public void testSaveEquipeWithJoueurs() throws Exception {
        // Arrange
        EquipeDTO equipeDTO = new EquipeDTO();
        equipeDTO.setName("New Team");
        equipeDTO.setBudget(1000000.0);
        equipeDTO.setAcronym("NT");
        JoueurDTO joueurDTO1 = new JoueurDTO();
        joueurDTO1.setName("Joueur 1");
        joueurDTO1.setPosition("Position 1");
        JoueurDTO joueurDTO2 = new JoueurDTO();
        joueurDTO2.setName("Joueur 2");
        joueurDTO2.setPosition("Position 2");
        equipeDTO.setJoueurs(Set.of(joueurDTO1, joueurDTO2));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/equipes/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(equipeDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("New Team")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.budget", Matchers.is(1000000.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.acronym", Matchers.is("NT")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.joueurs", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.joueurs[0].name", Matchers.is("Joueur 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.joueurs[0].position", Matchers.is("Position 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.joueurs[1].name", Matchers.is("Joueur 2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.joueurs[1].position", Matchers.is("Position 2")));
    }

}
