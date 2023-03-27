package io.spring.start.projetoweb.service;

import io.spring.start.projetoweb.model.dto.ProdutoDTO;
import io.spring.start.projetoweb.model.entity.CategoriaEntity;
import io.spring.start.projetoweb.model.entity.ProdutoEntity;
import io.spring.start.projetoweb.model.mapper.ProdutoMapper;
import io.spring.start.projetoweb.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ProdutoMapper mapper;

    public ProdutoDTO pegarPorId(Integer id) {

        Optional<ProdutoEntity> produtoEntityOp =
                repository.findById(id);

        if(produtoEntityOp.isPresent()) {
            ProdutoEntity produtoEntity = produtoEntityOp.get();
            return mapper.update(produtoEntity);
        }

        throw new EntityNotFoundException("Produto não encontrado");
    }

    public ProdutoDTO criar(ProdutoDTO produtoDTO) {

        ProdutoEntity categoria = mapper.update(produtoDTO);

        System.out.println(categoria);

        categoria = repository.save(categoria);

        return mapper.update(categoria);
    }

    public ProdutoDTO editar(ProdutoDTO produtoDTO, Integer id) {

        if(repository.existsById(id)) {

            ProdutoEntity produtoEntity = mapper.update(produtoDTO);
            produtoEntity.setId(id);
            produtoEntity = repository.save(produtoEntity);

            return mapper.update(produtoEntity);
        }

        throw new EntityNotFoundException("Categoria não encontrada");
    }

    public void deletar(Integer id){
        Optional<ProdutoEntity> produtoEntityOp =
                repository.findById(id);

        if(produtoEntityOp.isPresent()) {
            ProdutoEntity produtoEntity = produtoEntityOp.get();
            repository.delete(produtoEntity);
            return;
        }

        throw new EntityNotFoundException("Categoria não encontrada");
    }

    public List<ProdutoDTO> listar() {
        List<ProdutoEntity> listaEntities =  repository.findAll();
        return mapper.updateListDTO(listaEntities);
    }

    public List<ProdutoDTO> listarPorCategoria(Integer idCategoria) {
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setId(idCategoria);
        List<ProdutoEntity> listaEntities =  repository.findByCategoria(categoria);
        return mapper.updateListDTO(listaEntities);
    }

    public List<ProdutoDTO> filtrar(String nome, BigDecimal precoInicial, BigDecimal precoFinal) {
        List<ProdutoEntity> listaEntities =  repository.findByNomeOrPreco(nome, precoInicial, precoFinal);
        return mapper.updateListDTO(listaEntities);
    }

}
