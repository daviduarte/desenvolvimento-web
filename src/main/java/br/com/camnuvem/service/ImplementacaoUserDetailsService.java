package br.com.camnuvem.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.camnuvem.repository.UsuarioRepository;
import br.com.camnuvem.model.Usuario;

public class ImplementacaoUserDetailsService implements UserDetailsService{

    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        // Consulta no banco de dados
        Usuario usuario = usuarioRepository.findUserByLogin(username);
        
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        
		return new User(usuario.getLogin(),
				usuario.getPassword(),
				usuario.getAuthorities());
    }

}
