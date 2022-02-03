package io.github.taylsonmartinez.service.impl;

import io.github.taylsonmartinez.repository.PedidoRepository;
import io.github.taylsonmartinez.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository repository;

    public PedidoServiceImpl(PedidoRepository repository) {
        this.repository = repository;
    }
}