package com.app.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpedido")
	private int idpedido;
	private Date fecha;
	private String descripcion;
	@ManyToOne
	@JoinColumn(name = "id_consumidor",nullable = false,foreignKey = @ForeignKey(name="pedido_consumidor_fk"))
	private Consumidor consumidor;
	private double total;
	private String status;
	@OneToMany(mappedBy = "pedido",cascade = {CascadeType.ALL},orphanRemoval = true)
	private List<DetallePedido> detalles;
	
	public Pedido(Date fecha, Consumidor consumidor, double total, String status, List<DetallePedido> detalles) {
		super();
		this.fecha = fecha;
		this.consumidor = consumidor;
		this.total = total;
		this.status = status;
		this.detalles = detalles;
	}
	
	
}


/*create table pedidos(
	no_pedido varchar(10),
    descripcion varchar(100),
    id_ven int,
    ruc_ven varchar(12),
    id_cli int,
    cantidad int,
    primary key(no_pedido),
    CONSTRAINT FK_ven3 FOREIGN KEY (id_ven) references vendedor(id_ven),
    CONSTRAINT FK_cli2 FOREIGN KEY (id_cli) references clientes(id_cli)
);*/
