package io.spring.start.projetoweb.controller;


import io.spring.start.projetoweb.model.dto.MensagemDTO;
import io.spring.start.projetoweb.model.dto.ProdutoDTO;
import io.spring.start.projetoweb.service.ProdutoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/produtos")
@Slf4j
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<Object> pegarPorCategoria(@PathVariable Integer idCategoria) {
        try {
            return ResponseEntity.ok(produtoService.listarPorCategoria(idCategoria));

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }

    }

    @GetMapping("/filter")
    public ResponseEntity<Object> pegarPorNomePreco(
            @RequestParam(name = "nome", defaultValue = "") String nome,
            @RequestParam(name = "precoInicial", defaultValue = "0") BigDecimal precoInicial,
            @RequestParam(name = "precoFinal", defaultValue = "1000") BigDecimal precoFinal) {
        try {
            return ResponseEntity.ok(produtoService.filtrar(nome, precoInicial, precoFinal));

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }

    }

    @GetMapping
    public ResponseEntity<Object> listar() {

        try {
            return ResponseEntity.ok(produtoService.listar());

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> pegarUm(@PathVariable("id") Integer id) {

        try {

            return ResponseEntity.ok(produtoService.pegarPorId(id));

        } catch (EntityNotFoundException ex) {

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new MensagemDTO(ex.getMessage()));

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody @Valid ProdutoDTO produtoDTO) {

        try {

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(produtoService.criar(produtoDTO));

        } catch (Exception ex) {

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(
            @RequestBody @Valid ProdutoDTO produtoDTO,
            @PathVariable("id") Integer id) {

        try {
            return ResponseEntity.ok(
                    produtoService.editar(produtoDTO, id));

        } catch (EntityNotFoundException ex) {

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensagemDTO(ex.getMessage()));

        } catch (Exception ex) {

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(
            @PathVariable("id") Integer id) {

        try {
            produtoService.deletar(id);
            return ResponseEntity
                    .ok(new MensagemDTO("Categoria com id " + id + " removido com sucesso!"));

        } catch (EntityNotFoundException ex) {

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensagemDTO(ex.getMessage()));

        } catch (Exception ex) {

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }
}

