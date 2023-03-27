package io.spring.start.projetoweb.model.entity;


import lombok.Data;
import lombok.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name="categoria")
@ToString
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "categoria")
    private List<ProdutoEntity> produtos;
    @Column(name = "nome",nullable = false,unique = true)
    private String nome;
    @Column(name = "descricao")
    private String descricao;


}
