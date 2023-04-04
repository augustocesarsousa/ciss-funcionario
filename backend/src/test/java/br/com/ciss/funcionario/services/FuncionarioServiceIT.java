package br.com.ciss.funcionario.services;

import br.com.ciss.funcionario.dtos.FuncionarioDTO;
import br.com.ciss.funcionario.services.exceptions.ResourceNotFoundException;
import br.com.ciss.funcionario.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @BeforeEach
    void setUp() throws Exception {
        funcionarioDTO = Factory.createFuncionarioDtoTest();
        existingId = 1L;
        noExistingId = 9999L;
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
}
