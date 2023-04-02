package br.com.ciss.funcionario.repositories;

import br.com.ciss.funcionario.entities.Funcionario;
import br.com.ciss.funcionario.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class FuncionarioRepositoryTest {

    private FuncionarioRepository funcionarioRepository;
    private long totalFuncionarios;
    private long existingId;
    private long noExistigId;

    @Autowired
    public FuncionarioRepositoryTest(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @BeforeEach
    void setUp() throws Exception {
        totalFuncionarios = 15;
        existingId = 1L;
        noExistigId = 9999L;
    }

    @Test
    public void saveShouldPersistsWithAutoincrementWhenIdIsNull() {
        Funcionario funcionario = Factory.createFuncionarioTest();
        funcionario.setId(null);

        funcionario = funcionarioRepository.save(funcionario);

        Assertions.assertNotNull(funcionario.getId());
        Assertions.assertEquals(totalFuncionarios + 1, funcionario.getId());
    }

    @Test
    public void findByIdShouldReturnNotNullObjectWhenIdExists() {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(existingId);

        Assertions.assertTrue(funcionario.isPresent());
    }

    @Test
    public void findByIdShouldReturnNullObjectWhenIdDoesNotExists() {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(noExistigId);

        Assertions.assertFalse(funcionario.isPresent());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        funcionarioRepository.deleteById(existingId);

        Optional<Funcionario> funcionario = funcionarioRepository.findById(noExistigId);

        Assertions.assertFalse(funcionario.isPresent());
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
           funcionarioRepository.deleteById(noExistigId);
        });
    }
}
