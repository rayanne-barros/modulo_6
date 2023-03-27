package io.spring.start.projetoweb.model.mapper;

import io.spring.start.projetoweb.model.dto.ProdutoDTO;
import io.spring.start.projetoweb.model.entity.ProdutoEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoMapper {

    private CategoriaMapper categoriaMapper = new CategoriaMapper();

    public ProdutoDTO update(ProdutoEntity produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(produto.getId());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setDescricao(produto.getDescricao());
        produtoDTO.setCategoria(categoriaMapper.update(produto.getCategoria()));
        produtoDTO.setPreco(produto.getPreco());
        produtoDTO.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        return produtoDTO;
    }

    public ProdutoEntity update(ProdutoDTO produto) {
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(produto.getId());
        produtoEntity.setNome(produto.getNome());
        produtoEntity.setDescricao(produto.getDescricao());
        produtoEntity.setCategoria(categoriaMapper.update(produto.getCategoria()));
        produtoEntity.setPreco(produto.getPreco());
        produtoEntity.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        return produtoEntity;
    }

    public List<ProdutoEntity> updateListEntity(List<ProdutoDTO> listaDTO){
        return listaDTO.stream()
                .map(produtoDTO->
                        this.update(produtoDTO))
                .toList();
    }

    public List<ProdutoDTO> updateListDTO(List<ProdutoEntity> listaEntity){
        return listaEntity.stream()
                .map(produtoEntity->
                        this.update(produtoEntity))
                .toList();
    }
}

