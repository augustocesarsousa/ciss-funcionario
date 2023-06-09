package br.com.ciss.funcionario.services;

import br.com.ciss.funcionario.dtos.FuncionarioCreateDTO;
import br.com.ciss.funcionario.dtos.FuncionarioDTO;
import br.com.ciss.funcionario.dtos.FuncionarioUpdateDTO;
import br.com.ciss.funcionario.entities.Funcionario;
import br.com.ciss.funcionario.repositories.FuncionarioCustomRepository;
import br.com.ciss.funcionario.repositories.FuncionarioRepository;
import br.com.ciss.funcionario.services.exceptions.ResourceNotFoundException;
import br.com.ciss.funcionario.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class FuncionarioServiceTests {

    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private FuncionarioCustomRepository funcionarioCustomRepository;

    private Funcionario funcionario;
    private FuncionarioDTO funcionarioDTO;
    private FuncionarioCreateDTO funcionarioCreateDTO;
    private FuncionarioUpdateDTO funcionarioUpdateDTO;
    private long existingId;
    private long noExistingId;
    private Pageable pageable;
    private PageImpl<Funcionario> page;

    @BeforeEach
    void setUp() throws Exception {
        funcionario = Factory.createFuncionarioTest();
        funcionarioDTO = Factory.createFuncionarioDtoTest();
        funcionarioCreateDTO = Factory.createFuncionarioCreateDtoTest();
        funcionarioUpdateDTO = Factory.createFuncionarioUpdateDtoTest();
        existingId = 1L;
        noExistingId = 9999L;
        pageable = PageRequest.of(0, 10);
        page = new PageImpl<>(List.of(funcionario));

        when(funcionarioRepository.save(any())).thenReturn(funcionario);

        when(funcionarioRepository.findById(existingId)).thenReturn(Optional.of(funcionario));

        when(funcionarioCustomRepository.findByFilter(null, null, null, null,null, pageable)).thenReturn(page);

        doNothing().when(funcionarioRepository).deleteById(existingId);
        doThrow(EmptyResultDataAccessException.class).when(funcionarioRepository).deleteById(noExistingId);
    }

    @Test
    public void createShouldReturnFuncionarioDtoWhenIdIsNull() {
        funcionarioDTO.setId(null);

        FuncionarioDTO result = funcionarioService.create(funcionarioCreateDTO);

        Assertions.assertNotNull(result.getId());
    }

    @Test
    public void findByIdShouldReturnFuncionarioDtoWhenIdExists() {
        FuncionarioDTO result = funcionarioService.findById(existingId);

        Assertions.assertNotNull(result);
        verify(funcionarioRepository, times(1)).findById(existingId);
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            funcionarioService.findById(noExistingId);
        });
        verify(funcionarioRepository, times(1)).findById(noExistingId);
    }

    @Test
    public void findByFilterPagedShouldReturnPage() {
        Page<FuncionarioDTO> result = funcionarioService.findByFilterPaged(null, null, null, null, null, pageable);

        Assertions.assertNotNull(result);
    }

    @Test
    public void updateShouldReturnFuncionarioUpdateDtoWhenIdExists() {
        FuncionarioUpdateDTO result = funcionarioService.update(existingId, funcionarioUpdateDTO);

        Assertions.assertNotNull(result);
        verify(funcionarioRepository, times(1)).save(funcionario);
    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            funcionarioService.update(noExistingId, funcionarioUpdateDTO);
        });
    }

    @Test
    public void deleteShouldDoNotNothingWhenIdExists() {
        Assertions.assertDoesNotThrow(() -> {
            funcionarioService.delete(existingId);
        });
        verify(funcionarioRepository, times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            funcionarioService.delete(noExistingId);
        });
        verify(funcionarioRepository, times(1)).deleteById(noExistingId);
    }
}
