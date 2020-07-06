package com.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productoId")
    private int id;
 
    private String nombre;
    private String descripcion;

    private float precio;
    private int position;
    
    
    @ManyToOne
    @JoinColumn(name="idcategoria")
    private Categoria categoria;
    
    private int stock;
    private String img;
    private boolean publish;
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "idvendedor")
    private Seller vendedor;

	
    
    

}
