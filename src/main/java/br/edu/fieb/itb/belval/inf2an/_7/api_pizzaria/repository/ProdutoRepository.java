package br.edu.fieb.itb.belval.inf2an._7.api_pizzaria.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.edu.fieb.itb.belval.inf2an._7.api_pizzaria.domain.Produto;

public interface ProdutoRepository extends CrudRepository<Produto,Long>{

    
    
}
