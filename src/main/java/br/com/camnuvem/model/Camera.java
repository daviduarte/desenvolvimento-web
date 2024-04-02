package br.com.camnuvem.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;

@Entity
public class Camera {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fence;
    private String hours;
    private String name;
    private String object_detection;
    private String rtsp_address;
    private String time;


    // Usuário -> Camera (1 para muitos)
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable( name = "cameras_usuarios", 
                uniqueConstraints = @UniqueConstraint (
                    columnNames = {"camera_id","usuario_id"}, 
                    name = "unique_user_camera"
                ), 
	            joinColumns = @JoinColumn(name = "camera_id", 
                    referencedColumnName = "id", 
                    table = "camera", 
                    unique = false
                ), 
	            inverseJoinColumns = @JoinColumn (
                    name = "usuario_id", 
                    referencedColumnName = "id", 
                    table = "usuario", 
                    unique = false
                    //updatable = false,
                )
            )
    private List<Usuario> usuarios;    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFence() {
        return fence;
    }
    public void setFence(String fence) {
        this.fence = fence;
    }
    public String getHours() {
        return hours;
    }
    public void setHours(String hours) {
        this.hours = hours;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getObject_detection() {
        return object_detection;
    }
    public void setObject_detection(String object_detection) {
        this.object_detection = object_detection;
    }
    public String getRtsp_address() {
        return rtsp_address;
    }
    public void setRtsp_address(String rtsp_address) {
        this.rtsp_address = rtsp_address;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    

}
