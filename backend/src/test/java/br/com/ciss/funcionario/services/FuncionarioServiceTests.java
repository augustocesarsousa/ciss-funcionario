package br.com.ciss.funcionario.services;

import br.com.ciss.funcionario.dtos.FuncionarioDTO;
import br.com.ciss.funcionario.entities.Funcionario;
import br.com.ciss.funcionario.repositories.FuncionarioRepository;
import br.com.ciss.funcionario.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class FuncionarioServiceTests {

    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    private Funcionario funcionario;
    private FuncionarioDTO funcionarioDTO;
    private long totalFuncionarios;

    @BeforeEach
    void setUp() throws Exception {
        funcionario = Factory.createFuncionarioTest();
        funcionarioDTO = Factory.createFuncionarioDtoTest();

        when(funcionarioRepository.save(any())).thenReturn(funcionario);
    }

    @Test
    public void createShouldReturnFuncionarioDtoWhenIdIsNull() {
        funcionarioDTO.setId(null);

        FuncionarioDTO result = funcionarioService.create(funcionarioDTO);

        Assertions.assertNotNull(result.getId());
    }
}
