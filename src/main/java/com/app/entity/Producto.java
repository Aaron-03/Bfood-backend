package com.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productoId")
    private int id;
    private String nombre;
    private float precio;
    
    @NotNull
	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "negocio_producto", joinColumns = @JoinColumn(name = "negocioId"),
    inverseJoinColumns = @JoinColumn(name = "productoId"))
    private Negocio negocio;


}
