package io.spring.start.projetoweb.repository;

import io.spring.start.projetoweb.model.entity.CategoriaEntity;
import io.spring.start.projetoweb.model.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity,Integer> {

    //SELECT * FROM produto WHERE nome = 'Arro' OR (preco BETWEEN 2 AND 10);

    @Query("SELECT p FROM ProdutoEntity p "
            + "WHERE UPPER(p.nome) LIKE CONCAT('%',UPPER(:nome),'%') "
            + "OR (p.preco BETWEEN :fromPreco AND :toPreco)")
    List<ProdutoEntity> findByNomeOrPreco(@Param("nome") String nome, BigDecimal fromPreco, BigDecimal toPreco);

    List<ProdutoEntity> findByCategoria(CategoriaEntity categoria);

}
