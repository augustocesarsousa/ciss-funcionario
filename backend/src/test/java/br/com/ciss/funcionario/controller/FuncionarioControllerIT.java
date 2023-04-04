package br.com.ciss.funcionario.controller;

import br.com.ciss.funcionario.dtos.FuncionarioDTO;
import br.com.ciss.funcionario.tests.Factory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class FuncionarioControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private FuncionarioDTO funcionarioDTO;
    private long totalFuncionarios;
    private long existingId;
    private long noExistingId;

    @BeforeEach
    void setUp() throws Exception {
        funcionarioDTO = Factory.createFuncionarioDtoTest();
        totalFuncionarios = 15L;
        existingId = 1L;
        noExistingId = 9999L;
    }

    @Test
    public void createShouldReturnCreated() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(funcionarioDTO);

        mockMvc.perform(post("/funcionarios")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").exists());
    }

    @Test
    public void createShouldReturnUnprocessableEntityWhenNomeIsInvalid() throws Exception {
        funcionarioDTO.setNome("a");
        String jsonBody = objectMapper.writeValueAsString((funcionarioDTO));

        mockMvc.perform(post("/funcionarios")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.errors[0].message").value("Nome precisa ter entre 2 e 30 caracteres!"));
    }

    @Test
    public void createShouldReturnUnprocessableEntityWhenEmailIsInvalid() throws Exception {
        funcionarioDTO.setEmail("a");
        String jsonBody = objectMapper.writeValueAsString((funcionarioDTO));

        mockMvc.perform(post("/funcionarios")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.errors[0].message").value("Informe um email válido!"));
    }

    @Test
    public void findByFilterPagedShouldReturnSortedPageWhenSortByName() throws Exception{
        mockMvc.perform(get("/funcionarios?page=0&size=12&sort=nome,asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(totalFuncionarios))
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content[0].nome").value("Anchard"))
                .andExpect(jsonPath("$.content[1].nome").value("Anne"))
                .andExpect(jsonPath("$.content[2].nome").value("Betrich"));

    }

    @Test
    public void findByIdShouldReturnProductWhenIdExists() throws Exception{
        mockMvc.perform(get("/funcionarios/{id}", existingId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").exists());
    }

    @Test
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        mockMvc.perform(get("/funcionarios/{id}", noExistingId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateShouldReturnFuncionarioDtoWhenIdExists() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(funcionarioDTO);

        mockMvc.perform(put("/funcionarios/{id}", existingId)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").exists());
    }

    @Test
    public void updateShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        String jsonBody = objectMapper.writeValueAsString(funcionarioDTO);

        mockMvc.perform(put("/funcionarios/{id}", noExistingId)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() throws Exception {
        mockMvc.perform(delete("/funcionarios/{id}", existingId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        mockMvc.perform(delete("/funcionarios/{id}", noExistingId))
                .andExpect(status().isNotFound());
    }
}
