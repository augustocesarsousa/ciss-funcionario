package br.com.ciss.funcionario.repositories;

import br.com.ciss.funcionario.entities.Funcionario;
import br.com.ciss.funcionario.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class FuncionarioRepositoryTest {

    private FuncionarioRepository funcionarioRepository;
    private long totalFuncionarios;

    @Autowired
    public FuncionarioRepositoryTest(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @BeforeEach
    void setUp() throws Exception {
        totalFuncionarios = 15;
    }

    @Test
    public void saveShouldPersistsWithAutoincrementWhenIdIsNull() {
        Funcionario funcionario = Factory.createFuncionarioTest();
        funcionario.setId(null);

        funcionario = funcionarioRepository.save(funcionario);

        Assertions.assertNotNull(funcionario.getId());
        Assertions.assertEquals(totalFuncionarios + 1, funcionario.getId());
    }

}
