package br.com.camnuvem.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.camnuvem.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long>{

}
