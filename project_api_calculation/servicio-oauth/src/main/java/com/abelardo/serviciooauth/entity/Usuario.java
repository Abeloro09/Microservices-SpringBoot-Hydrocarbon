package com.abelardo.serviciooauth.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(
        name = "usuarios"
)
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            unique = true,
            length = 20
    )
    private String username;
    @Column(
            length = 60
    )
    private String password;
    private Boolean enabled;
    private String nombre;
    private String apellido;
    @Column(
            unique = true,
            length = 100
    )
    private String email;
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = {@JoinColumn(
                    name = "usuario_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "role_id"
            )},
            uniqueConstraints = {@UniqueConstraint(
                    columnNames = {"usuario_id", "role_id"}
            )}
    )
    private List<Role> roles;
    private static final long serialVersionUID = 4002221912401133094L;

    public Usuario() {
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
