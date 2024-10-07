package br.edu.fieb.itb.belval.inf2an._7.api_pizzaria.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.edu.fieb.itb.belval.inf2an._7.api_pizzaria.domain.Produto;
import br.edu.fieb.itb.belval.inf2an._7.api_pizzaria.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Produto> listarProdutos() {
        return (List<Produto>) produtoRepository.findAll();
    }

    public Optional<Produto> getProdutoById(Long id) {
        return produtoRepository.findById(id);
    }

    public List<Map<String, Object>> obterProdutosComCategoria() {
        String sql = "SELECT p.nome AS nomeProduto, c.nome AS nomeCategoria " +
                     "FROM Produto p " +
                     "LEFT JOIN Categoria c ON p.categoria_id = c.id";
        return jdbcTemplate.queryForList(sql);
    }

    // Incluir novo produto
    public Produto incluir(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Atualizar produto
    public Produto atualizar(Long id, Produto produto) {
        if (produtoRepository.existsById(id)) {
            produto.setId(id);
            return produtoRepository.save(produto);
        }
        return null; // ou lançar uma exceção
    }

    // Excluir produto
    public void excluir(Long id) {
        produtoRepository.deleteById(id);
    }
}
