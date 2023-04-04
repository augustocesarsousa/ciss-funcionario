package br.com.ciss.funcionario.services;

import br.com.ciss.funcionario.dtos.FuncionarioDTO;
import br.com.ciss.funcionario.services.exceptions.ResourceNotFoundException;
import br.com.ciss.funcionario.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Transactional
public class FuncionarioServiceIT {

    @Autowired
    private FuncionarioService funcionarioService;

    private FuncionarioDTO funcionarioDTO;
    private long existingId;
    private long noExistingId;

    private long totalFuncionarios;

    @BeforeEach
    void setUp() throws Exception {
        funcionarioDTO = Factory.createFuncionarioDtoTest();
        existingId = 1L;
        noExistingId = 9999L;
        totalFuncionarios = 15L;
    }

    @Test
    public void createShouldReturnFuncionarioDto() {
        FuncionarioDTO result = funcionarioService.create(funcionarioDTO);

        Assertions.assertNotNull(result);
    }

    @Test
    public void findByIdShouldReturnFuncionarioDtoWhenIdExists() {
        FuncionarioDTO result = funcionarioService.findById(existingId);

        Assertions.assertNotNull(result);
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            funcionarioService.findById(noExistingId);
        });
    }

    @Test
    public void findByFilterPagedShouldReturnPageWhenPage0Size10() {
        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<FuncionarioDTO> result = funcionarioService.findByFilterPaged(null, null, null, null, null, pageRequest);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(0, result.getNumber());
        Assertions.assertEquals(10, result.getSize());
        Assertions.assertEquals(totalFuncionarios, result.getTotalElements());
    }

    @Test
    public void findByFilterPagedShouldReturnEmptyPageWhenPageDoesNotExists() {
        PageRequest pageRequest = PageRequest.of(100, 10);

        Page<FuncionarioDTO> result = funcionarioService.findByFilterPaged(null, null, null, null, null, pageRequest);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void updateShouldReturnFuncionarioDtoWhenIdExists() {
        FuncionarioDTO result = funcionarioService.update(existingId, funcionarioDTO);

        Assertions.assertNotNull(result);
    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            funcionarioService.update(noExistingId, funcionarioDTO);
        });
    }
}
