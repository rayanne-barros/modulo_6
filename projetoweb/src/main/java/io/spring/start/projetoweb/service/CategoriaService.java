package io.spring.start.projetoweb.service;

import io.spring.start.projetoweb.model.dto.CategoriaDTO;
import io.spring.start.projetoweb.model.entity.CategoriaEntity;
import io.spring.start.projetoweb.model.mapper.CategoriaMapper;
import io.spring.start.projetoweb.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;
    @Autowired
    private CategoriaMapper mapper;

    public CategoriaDTO pegarPorId(Integer id) {
        Optional<CategoriaEntity> categoriaEntityOp = repository.findById(id);
        if(categoriaEntityOp.isPresent()) {
            CategoriaEntity categoriaEntity = categoriaEntityOp.get();
            return mapper.update(categoriaEntity);
        }
        throw new EntityNotFoundException("Categoria não encontrada");

    }

    public CategoriaDTO criar(CategoriaDTO categoriaDTO) {
        CategoriaEntity categoria = mapper.update(categoriaDTO);
        categoria = repository.save(categoria);
        return mapper.update(categoria);
    }

    public CategoriaDTO editar(CategoriaDTO categoriaDTO, Integer id) {
        if(repository.existsById(id)){
            CategoriaEntity categoriaEntity = mapper.update(categoriaDTO);
            categoriaEntity.setId(id);
            repository.save(categoriaEntity);
            return mapper.update(categoriaEntity);
        }

        throw new EntityNotFoundException("Categoria não encontrada");
    }

    public void deletar(Integer id) {
        Optional<CategoriaEntity> categoriaEntityOp = repository.findById(id);
        if(categoriaEntityOp.isPresent()) {
            CategoriaEntity categoriaEntity = categoriaEntityOp.get();
            repository.delete(categoriaEntity);
            return;
        }
        throw new EntityNotFoundException("Categoria não encontrada");
    }

    public List<CategoriaDTO> listar() {
        List<CategoriaEntity> listaEntities =  repository.findAll();
        return mapper.updateListDTO(listaEntities);

    }





}
