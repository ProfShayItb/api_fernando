package br.edu.fieb.itb.belval.inf2an._7.api_pizzaria.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fieb.itb.belval.inf2an._7.api_pizzaria.domain.Produto;

import br.edu.fieb.itb.belval.inf2an._7.api_pizzaria.service.ProdutoService;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutosController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        return new ResponseEntity<>(service.listarProdutos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<Produto> get(@PathVariable("id") Long id) {
        return service.getProdutoById(id);
    }

    @GetMapping("/comcategoria")
    public List<Map<String, Object>> obterProdutosComCategoria() {
        return service.obterProdutosComCategoria();
    }

    // Incluir novo produto
    @PostMapping
    public ResponseEntity<Produto> incluir(@RequestBody Produto produto) {
        Produto novoProduto = service.incluir(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    // Atualizar produto
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        Produto produtoAtualizado = service.atualizar(id, produto);
        if (produtoAtualizado != null) {
            return ResponseEntity.ok(produtoAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    // Excluir produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
