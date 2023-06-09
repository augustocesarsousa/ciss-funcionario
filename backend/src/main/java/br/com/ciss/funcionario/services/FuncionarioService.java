package br.com.ciss.funcionario.services;

import br.com.ciss.funcionario.dtos.FuncionarioCreateDTO;
import br.com.ciss.funcionario.dtos.FuncionarioDTO;
import br.com.ciss.funcionario.dtos.FuncionarioUpdateDTO;
import br.com.ciss.funcionario.entities.Funcionario;
import br.com.ciss.funcionario.repositories.FuncionarioRepository;
import br.com.ciss.funcionario.repositories.FuncionarioCustomRepository;
import br.com.ciss.funcionario.utils.CopyDtoToEntity;
import br.com.ciss.funcionario.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FuncionarioService {

    private FuncionarioRepository funcionarioRepository;
    private FuncionarioCustomRepository funcionarioCustomRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, FuncionarioCustomRepository funcionarioCustomRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioCustomRepository = funcionarioCustomRepository;
    }

    @Transactional
    public FuncionarioCreateDTO create(FuncionarioCreateDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();

        CopyDtoToEntity.copyFuncionarioDtoToFuncionario(funcionarioDTO, funcionario);

        funcionario = funcionarioRepository.save(funcionario);

        return new FuncionarioCreateDTO(funcionario);
    }

    @Transactional(readOnly = true)
    public FuncionarioDTO findById(Long id) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);

        Funcionario funcionario = funcionarioOptional.orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado!"));

        return new FuncionarioDTO(funcionario);
    }

    @Transactional(readOnly = true)
    public Page<FuncionarioDTO> findByFilterPaged(String idString, String nome, String sobrenome, String email, String nis, Pageable pageable) {
        Long id = null;
        if(idString != null) id = Long.parseLong(idString);

        Page<Funcionario> page = funcionarioCustomRepository.findByFilter(id, nome, sobrenome, email, nis, pageable);

        return page.map(funcionario -> new FuncionarioDTO(funcionario));
    }

    @Transactional
    public FuncionarioUpdateDTO update(Long id, FuncionarioUpdateDTO funcionarioUpdateDTO) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);

        Funcionario funcionario = funcionarioOptional.orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado!"));

        CopyDtoToEntity.copyFuncionarioDtoToFuncionario(funcionarioUpdateDTO, funcionario);

        funcionario = funcionarioRepository.save(funcionario);

        return new FuncionarioUpdateDTO(funcionario);
    }

    @Transactional
    public void delete(Long id) {
        try {
            funcionarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Funcionário não encontrado!");
        }
    }
}
