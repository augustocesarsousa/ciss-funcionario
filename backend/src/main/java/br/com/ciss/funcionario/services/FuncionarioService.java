package br.com.ciss.funcionario.services;

import br.com.ciss.funcionario.dtos.FuncionarioDTO;
import br.com.ciss.funcionario.entities.Funcionario;
import br.com.ciss.funcionario.repositories.FuncionarioRepository;
import br.com.ciss.funcionario.utils.CopyDtoToEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class FuncionarioService {

    private FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public FuncionarioDTO create(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();

        CopyDtoToEntity.copyFuncionarioDtoToFuncionario(funcionarioDTO, funcionario);

        funcionario = funcionarioRepository.save(funcionario);

        return new FuncionarioDTO(funcionario);
    }

}
