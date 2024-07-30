package com.abelardo.serviciooauth.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(
        name = "roles"
)
public class Role implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            unique = true,
            length = 30
    )
    private String nombre;
    private static final long serialVersionUID = 4467531611801172710L;

    public Role() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
