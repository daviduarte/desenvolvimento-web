package br.com.camnuvem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.camnuvem.model.Produto;
import br.com.camnuvem.model.Usuario;
import br.com.camnuvem.repository.ProdutoRepository;

@Controller("ProdutoController")
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping(value="/", produces="application/json")
    public ResponseEntity<List<Produto>> getAllProducts (){
        List<Produto> produtos = (List<Produto>) this.produtoRepository.findAll();

        return new ResponseEntity(produtos, HttpStatus.OK);
    }

    @PostMapping(value="/", produces="application/json")
    public ResponseEntity<Produto> insertNewProduct (@RequestBody Produto produto){

        Produto produtoSalvo = produtoRepository.save(produto);

        return new ResponseEntity(produtoSalvo, HttpStatus.OK);
    }    

}
