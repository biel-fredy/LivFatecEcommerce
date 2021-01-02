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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.sp.fatec.livraria.aplicacao.Sessao;
import br.gov.sp.fatec.livraria.aplicacao.Usuario;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoCliente;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoUsuario;
import br.gov.sp.fatec.livraria.domain.Cliente;
import br.gov.sp.fatec.livraria.domain.Endereco;
import br.gov.sp.fatec.livraria.domain.Telefone;
import br.gov.sp.fatec.livraria.service.ClienteService;
import br.gov.sp.fatec.livraria.service.UsuarioService;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	private Cliente cliente;

	@GetMapping("/editar")
	public ModelAndView preEditarPerfil(@SessionAttribute("sessao") Sessao sessao) {
		System.out.println("chega aqui");
		ModelAndView modelAndView = new ModelAndView("/perfil/editar");
		procuraCliente(sessao.getUsuario().getCliente());
		sessao.getUsuario().setCliente(this.cliente);
		modelAndView.addObject("cliente", this.cliente);
		
		System.out.println(this.cliente.getEnderecos().size());
		
		modelAndView.addObject("usuario", sessao.getUsuario());
		return modelAndView;
	}
	
	@PostMapping("/editar")
	public String editarPerfil(Usuario usuario, RedirectAttributes attr) {
		String senhaEncodada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncodada);
		ResultadoUsuario resultado = usuarioService.editar(usuario);
		attr.addFlashAttribute(resultado.getSuccessOrFailed(), resultado.getMensagem());
		return "redirect:/perfil/editar";
	}
	
	@PostMapping(value = "/editar", params = { "addEnd" }, name = "addEnd")
	public String editarAddEnd(final Usuario usuario, @RequestParam("addEnd") String addRow,
			final BindingResult bindingResult) {
		Endereco endereco = new Endereco();
		usuario.getCliente().addEndereco(endereco);
		return "/perfil/editar";
	}

	@PostMapping(value = "/editar", params = { "removeEnd" })
	public String editarRemoveEnd(final Usuario usuario, final BindingResult bindingResult,
			final HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeEnd"));
		usuario.getCliente().removeEndereco(rowId.intValue());
		return "/perfil/editar";
	}

	@PostMapping(value = "/editar", params = { "addTel" }, name = "addTel")
	public String editarAddTel(final Usuario usuario, @RequestParam("addTel") String addTel,
			final BindingResult bindingResult) {
		Telefone telefone = new Telefone();
		usuario.getCliente().addTelefone(telefone);
		return "/perfil/editar";
	}

	@PostMapping(value = "/editar", params = { "removeTel" })
	public String editarRemoveTel(final Usuario usuario, final BindingResult bindingResult,
			final HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeTel"));
		usuario.getCliente().removeTelefone(rowId.intValue());
		return "/perfil/editar";
	}

	public void procuraCliente(Cliente cliente) {
		ResultadoCliente resultado = clienteService.buscarPorId(cliente);
		Cliente clienteCarregado = resultado.getResultadoLista().get(0);
		this.cliente = clienteCarregado;
	}

}
