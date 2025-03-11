package com.alfredo.batch_prueba;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nombre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Usamos IDENTITY para la generación automática del ID
    private Integer id;

    private String nombre;

    // Constructor vacío (requerido por JPA)
    public Nombre() {
    }

    // Constructor con parámetros
    public Nombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para la propiedad id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter y Setter para la propiedad nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Nombre{id=" + id + ", nombre='" + nombre + "'}";
    }
}
