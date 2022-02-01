package io.github.taylsonmartinez;

import io.github.taylsonmartinez.entity.Cliente;
import io.github.taylsonmartinez.entity.Pedido;
import io.github.taylsonmartinez.repository.ClientesRepository;
import io.github.taylsonmartinez.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired ClientesRepository clientesRepository,
            @Autowired PedidoRepository pedidosRepository
    ) {
        return args -> {
            System.out.println("Salvando clientes");
            Cliente fulano = new Cliente("Fulano");
            clientesRepository.save(fulano);

            Pedido p = new Pedido();
            p.setCliente(fulano);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidosRepository.save(p);

            pedidosRepository.findByCliente(fulano).forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}