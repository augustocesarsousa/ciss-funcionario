package br.com.ciss.funcionario.tests;

import br.com.ciss.funcionario.dtos.FuncionarioCreateDTO;
import br.com.ciss.funcionario.dtos.FuncionarioDTO;
import br.com.ciss.funcionario.dtos.FuncionarioUpdateDTO;
import br.com.ciss.funcionario.entities.Funcionario;

public class Factory {

    public static Funcionario createFuncionarioTest() {
        return new Funcionario(
                1L,
                "FuncionarioTest",
                "SobrenomeTest",
                "email_test@email.com.br",
                "70707070707"
        );
    }

    public static FuncionarioDTO createFuncionarioDtoTest() {
        return new FuncionarioDTO(createFuncionarioTest());
    }

    public static FuncionarioCreateDTO createFuncionarioCreateDtoTest() {
        return new FuncionarioCreateDTO(createFuncionarioTest());
    }

    public static FuncionarioUpdateDTO createFuncionarioUpdateDtoTest() {
        return new FuncionarioUpdateDTO(createFuncionarioTest());
    }
}
