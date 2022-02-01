package io.github.taylsonmartinez;

import io.github.taylsonmartinez.entity.Cliente;
import io.github.taylsonmartinez.repository.ClientesRepository;
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
            clientesRepository.save(new Cliente("Taylson"));
            clientesRepository.save(new Cliente("Outro Cliente"));

            List<Cliente> todosClientes = clientesRepository.findAll();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientesRepository.save(c);
            });

            todosClientes = clientesRepository.findAll();
            todosClientes.forEach(System.out::println);

            System.out.println("Buscando clientes");
             clientesRepository.findByNomeLike("Cli").forEach(System.out::println);

            System.out.println("deletando clientes");
            clientesRepository.findAll().forEach(c -> {
                clientesRepository.delete(c);
            });

            todosClientes = clientesRepository.findAll();
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