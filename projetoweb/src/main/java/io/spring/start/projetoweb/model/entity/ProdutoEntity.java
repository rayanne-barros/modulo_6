package io.spring.start.projetoweb.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name="produto")
@ToString
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="categoria", nullable=false)
    private CategoriaEntity categoria;

    @Column(name = "nome",nullable = false,unique = true)
    private String nome;

    @Column(name = "preco",nullable = false)
    private BigDecimal preco;

    @Column(name="quantidade_estoque",nullable=false)
    private BigInteger quantidadeEstoque;

    @UpdateTimestamp
    @Column(name="data_ultima_atualizacao",nullable=false)
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "descricao")
    private String descricao;

}
