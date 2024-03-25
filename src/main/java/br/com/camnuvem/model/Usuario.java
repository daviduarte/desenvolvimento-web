package br.com.camnuvem.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.UniqueConstraint;

@Entity
public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String login;
    private String senha;
    private String nome;
    
    private List<String> roles = new ArrayList<String>(); /*Os papeis ou acessos*/

    public Usuario(String login, String senha, String role ){
        this.login = login;
        this.senha = senha;
        this.getRoles().add(role);

    }

    @OneToMany(mappedBy = "usuario", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Telefone> telefones = new ArrayList<Telefone>();

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable( name = "usuarios_role", 
                uniqueConstraints = @UniqueConstraint (
			         columnNames = {"usuario_id","role_id"}, 
                     name = "unique_role_user"
                ), 
	            joinColumns = @JoinColumn(
                    name = "usuario_id", 
                    referencedColumnName = "id", 
                    table = "usuario", 
                    unique = false,
	                foreignKey = @ForeignKey(
                        name = "usuario_fk", 
                        value = ConstraintMode.CONSTRAINT
                    )
                ), 
	            inverseJoinColumns = @JoinColumn (
                    name = "role_id",
                    referencedColumnName = "id", 
                    table = "role", 
                    unique = false, 
	                foreignKey = @ForeignKey (
                        name="role_fk", 
                        value = ConstraintMode.CONSTRAINT
                    )
                )
            )


    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
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
    public List<Telefone> getTelefones() {
        return telefones;
    }
    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }
	/*São os acessos do usuário ROLE_ADMIN OU ROLE_VISITANTE*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return roles;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return this.senha;
	}

	@JsonIgnore
	@Override
	public String getUsername() {
		return this.login;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}


    

}
