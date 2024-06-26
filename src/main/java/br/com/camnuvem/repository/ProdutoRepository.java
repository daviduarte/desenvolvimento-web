package br.com.camnuvem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.camnuvem.model.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long>{

}
