package io.github.taylsonmartinez.service.impl;

import io.github.taylsonmartinez.api.dto.ItemPedidoDTO;
import io.github.taylsonmartinez.api.dto.PedidoDTO;
import io.github.taylsonmartinez.entity.Cliente;
import io.github.taylsonmartinez.entity.ItemPedido;
import io.github.taylsonmartinez.entity.Pedido;
import io.github.taylsonmartinez.entity.Produto;
import io.github.taylsonmartinez.exception.RegraNegocioException;
import io.github.taylsonmartinez.repository.ClientesRepository;
import io.github.taylsonmartinez.repository.ItemPedidoRepository;
import io.github.taylsonmartinez.repository.PedidoRepository;
import io.github.taylsonmartinez.repository.ProdutosRepository;
import io.github.taylsonmartinez.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;
    private final ClientesRepository clientesRepository;
    private final ProdutosRepository produtosRepository;
    private final ItemPedidoRepository itemsPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar( PedidoDTO dto ) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: "+ idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    }
}