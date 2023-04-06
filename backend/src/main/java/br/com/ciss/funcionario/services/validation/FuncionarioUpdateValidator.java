package br.com.ciss.funcionario.services.validation;

import br.com.ciss.funcionario.controller.exceptions.FieldMessage;
import br.com.ciss.funcionario.dtos.FuncionarioCreateDTO;
import br.com.ciss.funcionario.dtos.FuncionarioUpdateDTO;
import br.com.ciss.funcionario.entities.Funcionario;
import br.com.ciss.funcionario.repositories.FuncionarioRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioUpdateValidator implements ConstraintValidator<FuncionarioUpdateValid, FuncionarioUpdateDTO> {

    private FuncionarioRepository funcionarioRepository;

    public FuncionarioUpdateValidator(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public void initialize(FuncionarioUpdateValid ann) {
    }

    @Override
    public boolean isValid(FuncionarioUpdateDTO funcionarioUpdateDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Funcionario funcionario = funcionarioRepository.findByEmail(funcionarioUpdateDTO.getEmail());

        if (funcionario != null) {
            list.add((new FieldMessage("email" , "Email já cadastrado para o funcionário id = " + funcionario.getId() + "!")));
        }

        funcionario = funcionarioRepository.findByNis(funcionarioUpdateDTO.getNis());

        if (funcionario != null) {
            list.add((new FieldMessage("nis" , "NIS já cadastrado para o funcionário id = " + funcionario.getId() + "!")));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
