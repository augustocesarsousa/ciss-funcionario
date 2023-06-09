package br.com.ciss.funcionario.repositories;

import br.com.ciss.funcionario.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Funcionario findByEmail(String email);

    Funcionario findByNis(String nis);
}
