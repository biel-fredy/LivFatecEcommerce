package br.gov.sp.fatec.livraria.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.livraria.aplicacao.Papel;
import br.gov.sp.fatec.livraria.aplicacao.Usuario;
import br.gov.sp.fatec.livraria.service.UsuarioService;

@SuppressWarnings("unused")
@Service
public class DbInit implements CommandLineRunner{
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		
		
		/*Papel papel = new Papel(1L, "ROLE_ADMIN");
				
		Usuario usuario = new Usuario(null, "ADMIN", passwordEncoder.encode("59862653"), null, papel);
				
		usuarioService.salvar(usuario);
		*/
		
	}

}
