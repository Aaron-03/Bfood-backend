package com.app.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    private String descripcion;
    private int estrellas;
    private float precio;
    private String img;
    private String categoria;
    private int stock;
    private String status; 

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idvendedor")
    private Seller vendedor;
//  @JoinTable(name = "vendedor", joinColumns = @JoinColumn(name = "productoId"),
//  inverseJoinColumns = @JoinColumn(name = "vendedorId"))

}
