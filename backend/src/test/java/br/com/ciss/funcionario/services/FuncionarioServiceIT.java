package br.com.ciss.funcionario.services;

import br.com.ciss.funcionario.dtos.FuncionarioDTO;
import br.com.ciss.funcionario.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class FuncionarioServiceIT {

    @Autowired
    private FuncionarioService funcionarioService;

    private FuncionarioDTO funcionarioDTO;

    @BeforeEach
    void setUp() throws Exception {
        funcionarioDTO = Factory.createFuncionarioDtoTest();
    }

    @Test
    public void createShouldReturnFuncionarioDto() {
        FuncionarioDTO result = funcionarioService.create(funcionarioDTO);

        Assertions.assertNotNull(result);
    }
}
