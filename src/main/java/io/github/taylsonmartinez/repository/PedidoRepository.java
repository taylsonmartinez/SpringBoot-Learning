package io.github.taylsonmartinez.repository;

import io.github.taylsonmartinez.entity.Cliente;
import io.github.taylsonmartinez.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}
