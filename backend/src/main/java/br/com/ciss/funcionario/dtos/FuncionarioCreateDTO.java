package br.com.ciss.funcionario.dtos;

import br.com.ciss.funcionario.entities.Funcionario;
import br.com.ciss.funcionario.services.validation.FuncionarioCreateValid;

@FuncionarioCreateValid
public class FuncionarioCreateDTO extends FuncionarioDTO {

    private static final long serialVersionUID = 1L;

    public FuncionarioCreateDTO() {
        super();
    }

    public FuncionarioCreateDTO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.sobrenome = funcionario.getSobrenome();
        this.email = funcionario.getEmail();
        this.nis = funcionario.getNis();
    }
}
