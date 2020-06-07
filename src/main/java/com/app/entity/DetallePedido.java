package com.app.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int iddetalle;
	private int idproducto;
	private int idfactura;
	private int cantidad;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idpedido")
	private Pedido pedido;
	
}
