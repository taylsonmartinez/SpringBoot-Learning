package io.github.taylsonmartinez.service;

import io.github.taylsonmartinez.api.dto.PedidoDTO;
import io.github.taylsonmartinez.entity.Pedido;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
}
