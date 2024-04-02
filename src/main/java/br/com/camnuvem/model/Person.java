package br.com.camnuvem.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Person")
public class Person extends Usuario{
    private String nome;
    private String sobrenome;

    public Person(){
        
    }

    public Person(String login, String senha, UserRole role, String nome, String sobrenome) {
        super.setLogin(login);
        super.setSenha(senha);
        super.setRole(role);
        this.nome = nome;
        this.sobrenome = sobrenome;
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

    

}
