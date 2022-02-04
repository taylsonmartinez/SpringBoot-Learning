package io.github.taylsonmartinez.service;

import io.github.taylsonmartinez.api.dto.PedidoDTO;
import io.github.taylsonmartinez.entity.Pedido;
import io.github.taylsonmartinez.enums.StatusPedido;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
