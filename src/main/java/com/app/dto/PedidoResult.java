package com.app.dto;

import com.app.entity.DetallePedido;
import com.app.entity.Pedido;
import com.app.entity.Seller;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PedidoResult {

	private Seller seller;
	private Pedido pedido;
	private DetallePedido detallePedido;

}
