package io.github.taylsonmartinez.repository;

import io.github.taylsonmartinez.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto, Integer> {

}
