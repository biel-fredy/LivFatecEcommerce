package br.gov.sp.fatec.livraria.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.livraria.aplicacao.Usuario;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoUsuario;
import br.gov.sp.fatec.livraria.service.UsuarioService;

@Service
public class UserDetailsImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ResultadoUsuario resultado = usuarioService.buscarPorNomeUsuario(username);
		Usuario usuario = new Usuario();		
		usuario = resultado.getResultadoLista().get(0);
		return usuario;
	}

}
