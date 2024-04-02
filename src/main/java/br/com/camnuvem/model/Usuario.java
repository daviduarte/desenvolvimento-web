package br.com.camnuvem.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.UniqueConstraint;
import br.com.camnuvem.model.Camera;


//@MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Usuario_Type")
public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String login;
    private String senha;
    private UserRole role;

	//@JsonIgnore
	//@org.hibernate.annotations.ForeignKey(name = "camera_id")
    @JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( name = "cameras_usuarios", 
                uniqueConstraints = @UniqueConstraint (
                    columnNames = {"camera_id","usuario_id"}, 
                    name = "unique_user_camera"
                ), 
	            joinColumns = @JoinColumn(name = "usuario_id", 
                    referencedColumnName = "id", 
                    table = "usuario", 
                    unique = false
                ), 
	            inverseJoinColumns = @JoinColumn (
                    name = "camera_id", 
                    referencedColumnName = "id", 
                    table = "camera", 
                    unique = false
                )
            )    
	private List<Camera> cameras = new ArrayList<Camera>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Telefone> telefones = new ArrayList<Telefone>();    

    public Usuario(){
        
    }

    public Usuario(String login, String senha, String nome, UserRole role){
        this.login = login;
        this.senha = senha;
        this.role = role;
    }

    public Usuario(String login, String senha, UserRole role){
        this.login = login;
        this.senha = senha;
        this.role = role;
    }



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

    public List<Telefone> getTelefones() {
        return telefones;
    }
    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    

    public List<Camera> getCameras() {
        return cameras;
    }

    public void setCameras(List<Camera> cameras) {
        this.cameras = cameras;
    }

    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // O Spring Security já tem algumas roles implementadas. Repare que 
        // nesse método o retorno é uma colection, então cada usuário pode ter
        // vários papéis (roles). Por exemplo, um ADMIN é ao mesmo tempo USER
        // norma. Um CHEFE é ao mesmo tempo ADMIN e USER normal, ...
        if (this.role == UserRole.ADMIN){
            return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),   // Admin
                new SimpleGrantedAuthority("ROLE_USER")     // é ao mesmo tempo user normal
                );
        }else{
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }
    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return this.getSenha();
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.getLogin();
    }
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }



    

}
