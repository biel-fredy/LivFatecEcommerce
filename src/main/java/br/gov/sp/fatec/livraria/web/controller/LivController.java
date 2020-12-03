package br.gov.sp.fatec.livraria.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.sp.fatec.livraria.aplicacao.Papel;
import br.gov.sp.fatec.livraria.aplicacao.Usuario;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoUsuario;
import br.gov.sp.fatec.livraria.domain.Cliente;
import br.gov.sp.fatec.livraria.domain.Endereco;
import br.gov.sp.fatec.livraria.domain.Telefone;
import br.gov.sp.fatec.livraria.service.UsuarioService;

@Controller
@RequestMapping("/liv")
@SessionAttributes("usuario")
public class LivController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/entrar")
	public ModelAndView entrar() {
		ModelAndView model = new ModelAndView("/liv/entrar");
		return model;
	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView model = new ModelAndView("/liv/cadastro");
		Usuario usuario = new Usuario();
		Cliente cliente = new Cliente();
		Endereco endereco = new Endereco();
		Telefone telefone = new Telefone();
		cliente.addEndereco(endereco);
		cliente.addTelefone(telefone);
		usuario.setCliente(cliente);
		cliente.setUsuario(usuario);
		model.addObject("usuario", usuario);
		return model;
	}

	@PostMapping("/salvar")
	public String salvar(Usuario usuario, RedirectAttributes attr) {
		String senhaEncodada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncodada);
		usuario.setPapel(new Papel(2L, "Cliente"));
		ResultadoUsuario resultado = usuarioService.salvar(usuario);
		attr.addFlashAttribute(resultado.getSuccessOrFailed(), resultado.getMensagem());
		return "redirect:/liv/cadastrar";
	}

	@PostMapping(value = "/salvar", params = { "addEnd" }, name = "addEnd")
	public String addEnd(final Usuario usuario, @RequestParam("addEnd") String addRow,
			final BindingResult bindingResult) {
		Endereco endereco = new Endereco();
		usuario.getCliente().addEndereco(endereco);
		return "/liv/cadastro";
	}

	@PostMapping(value = "/salvar", params = { "removeEnd" })
	public String removeEnd(final Usuario usuario, final BindingResult bindingResult, final HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeEnd"));
		usuario.getCliente().removeEndereco(rowId.intValue());
		return "/liv/cadastro";
	}

	@PostMapping(value = "/salvar", params = { "addTel" }, name = "addTel")
	public String addTel(final Usuario usuario, @RequestParam("addTel") String addTel,
			final BindingResult bindingResult) {
		Telefone telefone = new Telefone();
		usuario.getCliente().addTelefone(telefone);
		return "/liv/cadastro";
	}

	@PostMapping(value = "/salvar", params = { "removeTel" })
	public String removeTel(final Usuario usuario, final BindingResult bindingResult, final HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeTel"));
		usuario.getCliente().removeTelefone(rowId.intValue());
		return "/liv/cadastro";
	}

}
