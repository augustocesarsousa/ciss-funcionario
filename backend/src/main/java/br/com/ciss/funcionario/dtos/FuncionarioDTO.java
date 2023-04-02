package br.com.ciss.funcionario.dtos;

import br.com.ciss.funcionario.entities.Funcionario;

import java.io.Serializable;

public class FuncionarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String nis;

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(Long id, String nome, String sobrenome, String email, String nis) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.nis = nis;
    }

    public FuncionarioDTO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.sobrenome = funcionario.getSobrenome();
        this.email = funcionario.getEmail();
        this.nis = funcionario.getNis();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }
}
