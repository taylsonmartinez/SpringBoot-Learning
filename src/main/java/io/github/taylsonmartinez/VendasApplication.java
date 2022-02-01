package io.github.taylsonmartinez;

import io.github.taylsonmartinez.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientesRepository clientesRepository){
        return args -> {
            System.out.println("Salvando clientes");
            clientesRepository.salvar(new Cliente("Taylson"));
            clientesRepository.salvar(new Cliente("Outro Cliente"));

            List<Cliente> todosClientes = clientesRepository.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientesRepository.atualizar(c);
            });

            todosClientes = clientesRepository.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Buscando clientes");
            clientesRepository.buscarPorNome("Cli").forEach(System.out::println);

            System.out.println("deletando clientes");
            clientesRepository.obterTodos().forEach(c -> {
                clientesRepository.deletar(c);
            });

            todosClientes = clientesRepository.obterTodos();
            if(todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado.");
            }else{
                todosClientes.forEach(System.out::println);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}