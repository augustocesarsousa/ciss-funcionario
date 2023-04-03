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

import java.util.Optional;

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
    private long existingId;
    private long noExistingId;

    @BeforeEach
    void setUp() throws Exception {
        funcionario = Factory.createFuncionarioTest();
        funcionarioDTO = Factory.createFuncionarioDtoTest();
        existingId = 1L;
        noExistingId = 9999L;

        when(funcionarioRepository.save(any())).thenReturn(funcionario);

        when(funcionarioRepository.findById(existingId)).thenReturn(Optional.of(funcionario));
    }

    @Test
    public void createShouldReturnFuncionarioDtoWhenIdIsNull() {
        funcionarioDTO.setId(null);

        FuncionarioDTO result = funcionarioService.create(funcionarioDTO);

        Assertions.assertNotNull(result.getId());
    }

    @Test
    public void findByIdShouldReturnFuncionarioDtoWhenIdExists() {
        FuncionarioDTO result = funcionarioService.findById(existingId);

        Assertions.assertNotNull(result);
        verify(funcionarioRepository, times(1)).findById(existingId);
    }
}
