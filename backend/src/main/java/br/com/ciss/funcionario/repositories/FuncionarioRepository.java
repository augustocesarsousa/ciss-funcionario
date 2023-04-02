package br.com.ciss.funcionario.repositories;

import br.com.ciss.funcionario.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Long, Funcionario> {
}
