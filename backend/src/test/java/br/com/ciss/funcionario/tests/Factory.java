package br.com.ciss.funcionario.tests;

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
}
