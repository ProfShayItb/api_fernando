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
        List<Produto> produtos = service.listarProdutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> get(@PathVariable("id") Long id) {
        Optional<Produto> produto = service.getProdutoById(id);
        return produto.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/comcategoria")
    public ResponseEntity<List<Map<String, Object>>> obterProdutosComCategoria() {
        List<Map<String, Object>> produtosComCategoria = service.obterProdutosComCategoria();
        return new ResponseEntity<>(produtosComCategoria, HttpStatus.OK);
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
        return produtoAtualizado != null 
               ? ResponseEntity.ok(produtoAtualizado) 
               : ResponseEntity.notFound().build();
    }

    // Excluir produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            service.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
