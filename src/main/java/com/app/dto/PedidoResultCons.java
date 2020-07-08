package com.app.dto;

import com.app.entity.Consumidor;
import com.app.entity.DetallePedido;
import com.app.entity.Pedido;

import lombok.AllArgsConstructor;
import lombok.Data;

 

@AllArgsConstructor
@Data
public class PedidoResultCons {

 

    private Consumidor cons;
    private Pedido pedido;
    private DetallePedido detallePedido;

}