package br.com.ciss.funcionario.dtos;

import br.com.ciss.funcionario.entities.Funcionario;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class FuncionarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Long id;

    @Size(min = 2, max = 30, message = "Nome precisa ter entre 2 e 30 caracteres!")
    @NotBlank(message = "Campo obrigatório!")
    protected String nome;

    @Size(min = 2, max = 50, message = "Sobrenome precisa ter entre 2 e 50 caracteres!")
    @NotBlank(message = "Campo obrigatório!")
    protected String sobrenome;

    @Email(message = "Informe um email válido!")
    @Column(unique = true)
    protected String email;

    @Pattern(regexp = "[0-9]{11}", message = "O NIS precisa ter 11 dígitos numéricos!")
    @NotBlank(message = "Campo obrigatório")
    @Column(unique = true)
    protected String nis;

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
