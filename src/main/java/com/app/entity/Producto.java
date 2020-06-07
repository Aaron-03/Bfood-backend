package com.app.entity;

import java.util.List;
import java.util.Set;

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
    private String img;
    private String categoria;
    private int stock;
    private String status; 
    
    @ManyToOne
    @JoinColumn(name = "idvendedor")
    private Seller vendedor;
//  @JoinTable(name = "vendedor", joinColumns = @JoinColumn(name = "productoId"),
//  inverseJoinColumns = @JoinColumn(name = "vendedorId"))

}
