package br.com.ciss.funcionario.controller;

import br.com.ciss.funcionario.dtos.FuncionarioCreateDTO;
import br.com.ciss.funcionario.dtos.FuncionarioDTO;
import br.com.ciss.funcionario.dtos.FuncionarioUpdateDTO;
import br.com.ciss.funcionario.services.FuncionarioService;
import br.com.ciss.funcionario.services.validation.FuncionarioCreateValidator;
import br.com.ciss.funcionario.services.exceptions.ResourceNotFoundException;
import br.com.ciss.funcionario.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FuncionarioController.class)
public class FuncionarioControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FuncionarioService funcionarioService;

    private FuncionarioDTO funcionarioDTO;
    private FuncionarioCreateDTO funcionarioCreateDTO;
    private FuncionarioUpdateDTO funcionarioUpdateDTO;
    private PageImpl<FuncionarioDTO> page;
    private long existingId;
    private long noExistingId;

    @BeforeEach
    void setUp() throws Exception {
        funcionarioDTO = Factory.createFuncionarioDtoTest();
        funcionarioCreateDTO = Factory.createFuncionarioCreateDtoTest();
        funcionarioUpdateDTO = Factory.createFuncionarioUpdateDtoTest();
        page = new PageImpl<>(List.of(funcionarioDTO));
        existingId = 1L;
        noExistingId = 9999L;

        when(funcionarioService.create(any())).thenReturn(funcionarioCreateDTO);

        when(funcionarioService.findByFilterPaged(any(), any(), any(), any(), any(), any())).thenReturn(page);

        when(funcionarioService.findById(existingId)).thenReturn(funcionarioDTO);
        when(funcionarioService.findById(noExistingId)).thenThrow(ResourceNotFoundException.class);

        when(funcionarioService.update(eq(existingId), any())).thenReturn(funcionarioUpdateDTO);
        when(funcionarioService.update(eq(noExistingId), any())).thenThrow(ResourceNotFoundException.class);

        doNothing().when(funcionarioService).delete(existingId);
        doThrow(ResourceNotFoundException.class).when(funcionarioService).delete(noExistingId);
    }

//    @Test
//    public void createShouldReturnCreated() throws Exception {
//        String jsonBody = objectMapper.writeValueAsString((funcionarioCreateDTO));
//
//        mockMvc.perform(post("/funcionarios")
//                .content(jsonBody)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").exists())
//                .andExpect(jsonPath("$.nome").exists());
//    }
//
//    @Test
//    public void createShouldReturnUnprocessableEntityWhenNomeIsInvalid() throws Exception {
//        funcionarioCreateDTO.setNome("a");
//        String jsonBody = objectMapper.writeValueAsString((funcionarioCreateDTO));
//
//        mockMvc.perform(post("/funcionarios")
//                        .content(jsonBody)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isUnprocessableEntity())
//                .andExpect(jsonPath("$.errors[0].message").value("Nome precisa ter entre 2 e 30 caracteres!"));
//    }
//
//    @Test
//    public void createShouldReturnUnprocessableEntityWhenEmailIsInvalid() throws Exception {
//        funcionarioCreateDTO.setEmail("a");
//        String jsonBody = objectMapper.writeValueAsString((funcionarioCreateDTO));
//
//        mockMvc.perform(post("/funcionarios")
//                        .content(jsonBody)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isUnprocessableEntity())
//                .andExpect(jsonPath("$.errors[0].message").value("Informe um email válido!"));
//    }
//
//    @Test
//    public void createShouldReturnUnprocessableEntityWhenNisIsInvalid() throws Exception {
//        funcionarioCreateDTO.setNis("a");
//        String jsonBody = objectMapper.writeValueAsString((funcionarioCreateDTO));
//
//        mockMvc.perform(post("/funcionarios")
//                        .content(jsonBody)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isUnprocessableEntity())
//                .andExpect(jsonPath("$.errors[0].message").value("O NIS precisa ter 11 dígitos numéricos!"));
//    }

    @Test
    public void findByFilterPagedShouldReturnPage() throws Exception {
        mockMvc.perform(get("/funcionarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.pageable").exists());
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

//    @Test
//    public void updateShouldReturnFuncionarioDtoWhenIdExists() throws Exception {
//        String jsonBody = objectMapper.writeValueAsString(funcionarioUpdateDTO);
//
//        mockMvc.perform(put("/funcionarios/{id}", existingId)
//                .content(jsonBody)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").exists())
//                .andExpect(jsonPath("$.nome").exists());
//    }
//
//    @Test
//    public void updateShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
//        String jsonBody = objectMapper.writeValueAsString(funcionarioUpdateDTO);
//
//        mockMvc.perform(put("/funcionarios/{id}", noExistingId)
//                .content(jsonBody)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }

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
