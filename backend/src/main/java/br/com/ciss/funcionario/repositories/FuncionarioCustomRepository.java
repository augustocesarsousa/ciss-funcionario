package br.com.ciss.funcionario.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import br.com.ciss.funcionario.entities.Funcionario;

@Repository
public class FuncionarioCustomRepository {

    private final EntityManager entityManager;

    public FuncionarioCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Page<Funcionario> findByFilter(Long id, String nome, String sobrenome, String email, String nis,
            Pageable pageable) {
        Sort sort = pageable.getSort();

        String orderBy = sort.stream()
                .map(order -> order.getProperty() + " " + order.getDirection().name())
                .reduce((s1, s2) -> s1 + ", " + s2)
                .orElse("");

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT f FROM Funcionario f WHERE 1=1 ");

        if (id != null) {
            sql.append("AND f.id = :id ");
        }

        if (nome != null) {
            sql.append("AND LOWER(f.nome) LIKE :nome ");
        }

        if (sobrenome != null) {
            sql.append("AND LOWER(f.sobrenome) LIKE :sobrenome ");
        }

        if (email != null) {
            sql.append("AND f.email = :email ");
        }

        if (nis != null) {
            sql.append("AND f.nis = :nis ");
        }

        if (!"".equals(orderBy)) {
            sql.append("ORDER BY f." + orderBy);
        }

        var query = entityManager.createQuery(sql.toString(), Funcionario.class);

        if (id != null) {
            query.setParameter("id", id);
        }

        if (nome != null) {
            query.setParameter("nome", "%" + nome + "%");
        }

        if (sobrenome != null) {
            query.setParameter("sobrenome", "%" + sobrenome + "%");
        }

        if (email != null) {
            query.setParameter("email", email);
        }

        if (nis != null) {
            query.setParameter("nis", nis);
        }

        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<Funcionario> funcionarios = query.getResultList();
        long total = totalFuncionarios();
        return new PageImpl<>(funcionarios, pageable, total);
    }

    private long totalFuncionarios() {
        String sql = "SELECT COUNT(f) FROM Funcionario f";

        var query = entityManager.createQuery(sql, Long.class);

        return (Long) query.getSingleResult();
    }
}
