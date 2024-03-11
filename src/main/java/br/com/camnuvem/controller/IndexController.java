package br.com.camnuvem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.camnuvem.model.Usuario;
import br.com.camnuvem.repository.UsuarioRepository;

@Controller("IndexController")
public class IndexController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Manipulando parâmetros
    @GetMapping(value="/", produces="application/json")
    public ResponseEntity init (@RequestParam(value="nome") String nome){
        return new ResponseEntity("Olá Usuário! "+nome, HttpStatus.OK);
    }

    // Retornando objetos
    @GetMapping(value="/endpoint2", produces="application/json")
    public ResponseEntity init2 (){

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setLogin("user@gmail.com");
        usuario.setNome("Usuario");
        usuario.setSenha("123");

        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setLogin("user2@gmail.com");
        usuario2.setNome("Usuario 2");
        usuario2.setSenha("123");        

        List<Usuario> list = new ArrayList<Usuario>();
        list.add(usuario);
        list.add(usuario2);

        return new ResponseEntity(list, HttpStatus.OK);
    }

    // Retornando registros do BD
    @GetMapping(value="/endpoint3/{id}", produces="application/json")
    public ResponseEntity<Usuario> init3 (@PathVariable(value="id") Long id){

        try{
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            return new ResponseEntity(usuario.get(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("no such user", HttpStatus.NOT_FOUND);
        }        
        
    }    

}
