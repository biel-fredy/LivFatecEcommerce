package br.gov.sp.fatec.livraria.web.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.sp.fatec.livraria.aplicacao.Papel;
import br.gov.sp.fatec.livraria.aplicacao.Usuario;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoCliente;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoUsuario;
import br.gov.sp.fatec.livraria.domain.Cliente;
import br.gov.sp.fatec.livraria.domain.Endereco;
import br.gov.sp.fatec.livraria.domain.Telefone;
import br.gov.sp.fatec.livraria.service.ClienteService;
import br.gov.sp.fatec.livraria.service.UsuarioService;

@Controller
@RequestMapping("/admin/clientes")
public class AdminClienteController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("/admin/cliente/cadastro");
		Usuario usuario = new Usuario();
		Cliente cliente = new Cliente();
		Endereco endereco = new Endereco();
		Telefone telefone = new Telefone();
		cliente.addEndereco(endereco);
		cliente.addTelefone(telefone);
		usuario.setCliente(cliente);
		cliente.setUsuario(usuario);
		modelAndView.addObject("usuario", usuario);
		return modelAndView;
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		ResultadoCliente resultado = clienteService.buscarTodos();
		model.addAttribute("clientes", resultado.getResultadoLista());
		return "/admin/cliente/lista";
	}

	@PostMapping("/salvar")
	public String salvar(Usuario usuario, RedirectAttributes attr) {
		String senhaEncodada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncodada);
		usuario.setPapel(new Papel(2L, "Cliente"));
		ResultadoUsuario resultado = usuarioService.salvar(usuario);
		attr.addFlashAttribute(resultado.getSuccessOrFailed(), resultado.getMensagem());
		return "redirect:/admin/clientes/cadastrar";
	}

	@PostMapping(value = "/salvar", params = { "addEnd" }, name = "addEnd")
	public String addEnd(final Usuario usuario, @RequestParam("addEnd") String addRow,
			final BindingResult bindingResult) {
		Endereco endereco = new Endereco();
		usuario.getCliente().addEndereco(endereco);
		return "/admin/cliente/cadastro";
	}

	@PostMapping(value = "/salvar", params = { "removeEnd" })
	public String removeEnd(final Usuario usuario, final BindingResult bindingResult, final HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeEnd"));
		usuario.getCliente().removeEndereco(rowId.intValue());
		return "/admin/cliente/cadastro";
	}

	@PostMapping(value = "/salvar", params = { "addTel" }, name = "addTel")
	public String addTel(final Usuario usuario, @RequestParam("addTel") String addTel,
			final BindingResult bindingResult) {
		Telefone telefone = new Telefone();
		usuario.getCliente().addTelefone(telefone);
		return "/admin/cliente/cadastro";
	}

	@PostMapping(value = "/salvar", params = { "removeTel" })
	public String removeTel(final Usuario usuario, final BindingResult bindingResult, final HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeTel"));
		usuario.getCliente().removeTelefone(rowId.intValue());
		return "/admin/cliente/cadastro";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		Cliente cliente = new Cliente(id);
		ResultadoCliente resultado = clienteService.buscarPorId(cliente);
		cliente = resultado.getResultadoLista().get(0);
		model.addAttribute("usuario", cliente.getUsuario());
		return "/admin/cliente/cadastro";
	}

	@PostMapping("/editar")
	public String editar(Usuario usuario, RedirectAttributes attr) {
		ResultadoUsuario resultado = usuarioService.editar(usuario);
		attr.addFlashAttribute(resultado.getSuccessOrFailed(), resultado.getMensagem());
		return "redirect:/admin/clientes/cadastrar";
	}

	@PostMapping(value = "/editar", params = { "addEnd" }, name = "addEnd")
	public String editarAddEnd(final Usuario usuario, @RequestParam("addEnd") String addRow,
			final BindingResult bindingResult) {
		Endereco endereco = new Endereco();
		usuario.getCliente().addEndereco(endereco);
		return "/admin/cliente/cadastro";
	}

	@PostMapping(value = "/editar", params = { "removeEnd" })
	public String editarRemoveEnd(final Usuario usuario, final BindingResult bindingResult,
			final HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeEnd"));
		usuario.getCliente().removeEndereco(rowId.intValue());
		return "/admin/cliente/cadastro";
	}

	@PostMapping(value = "/editar", params = { "addTel" }, name = "addTel")
	public String editarAddTel(final Usuario usuario, @RequestParam("addTel") String addTel,
			final BindingResult bindingResult) {
		Telefone telefone = new Telefone();
		usuario.getCliente().addTelefone(telefone);
		return "/admin/cliente/cadastro";
	}

	@PostMapping(value = "/editar", params = { "removeTel" })
	public String editarRemoveTel(final Usuario usuario, final BindingResult bindingResult,
			final HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeTel"));
		usuario.getCliente().removeTelefone(rowId.intValue());
		return "/admin/cliente/cadastro";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		Cliente cliente = new Cliente(id);
		ResultadoCliente resultadoCliente = clienteService.buscarPorId(cliente);
		cliente = resultadoCliente.getResultadoLista().get(0);
		System.out.println(cliente.getPrimeiroNome());
		ResultadoUsuario resultadoUsuario = usuarioService.excluir(cliente.getUsuario());
		model.addAttribute(resultadoUsuario.getSuccessOrFailed(), resultadoUsuario.getMensagem());
		return listar(model);
	}

	@GetMapping("/ativar/{id}")
	public String ativarLivro(@PathVariable("id") Long id, ModelMap model) {
		ResultadoCliente resultado = clienteService.buscarPorId(new Cliente(id));
		Cliente cliente = resultado.getResultadoLista().get(0);
		cliente.setStatus(!cliente.getStatus());
		clienteService.editar(cliente);
		return listar(model);
	}

}
