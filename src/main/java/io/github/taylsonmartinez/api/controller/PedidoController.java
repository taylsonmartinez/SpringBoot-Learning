package io.github.taylsonmartinez.api.controller;

import io.github.taylsonmartinez.api.dto.PedidoDTO;
import io.github.taylsonmartinez.entity.Pedido;
import io.github.taylsonmartinez.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save( @RequestBody PedidoDTO dto ){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }
}