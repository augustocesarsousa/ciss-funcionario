package br.com.ciss.funcionario.utils;

import br.com.ciss.funcionario.dtos.FuncionarioDTO;
import br.com.ciss.funcionario.entities.Funcionario;

public abstract class CopyDtoToEntity {

    public static void copyFuncionarioDtoToFuncionario(FuncionarioDTO funcionarioDTO, Funcionario funcionario) {
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setSobrenome(funcionarioDTO.getSobrenome());
        funcionario.setEmail(funcionarioDTO.getEmail());
        funcionario.setNis(funcionarioDTO.getNis());
    }
}
