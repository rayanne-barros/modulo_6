package io.spring.start.projetoweb.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class ProdutoDTO {
    private Integer id;
    @Size(max=80,message="Tamanho do nome ácima do permitido")
    @NotBlank(message="Nome deve conter algum valor")
    private String nome;
    private CategoriaDTO categoria;
    private BigDecimal preco;
    private BigInteger quantidadeEstoque;
    private String descricao;

}
