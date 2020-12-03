package br.gov.sp.fatec.livraria.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoEstoque;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoLivro;
import br.gov.sp.fatec.livraria.domain.Estoque;
import br.gov.sp.fatec.livraria.domain.Livro;
import br.gov.sp.fatec.livraria.service.EstoqueService;
import br.gov.sp.fatec.livraria.service.LivroService;

@Controller
@RequestMapping("/admin/estoques")
public class AdminEstoquesController {

	@Autowired
	private EstoqueService estoqueService;
	
	@Autowired
	private LivroService livroService;
	
	private List<Livro> listaLivros;

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("/admin/estoque/cadastro");
		Estoque estoque = new Estoque();
		carregaListaLivros();
		modelAndView.addObject("estoque", estoque);
		modelAndView.addObject("listaLivros", this.listaLivros);
		return modelAndView;
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		ResultadoEstoque resultado = estoqueService.buscarTodos();
		model.addAttribute("estoques", resultado.getResultadoLista());
		return "/admin/estoque/lista";
	}

	@PostMapping("/salvar")
	public String salvar(final Estoque estoque, RedirectAttributes attr) {
		ResultadoEstoque resultado = estoqueService.salvar(estoque);
		attr.addFlashAttribute(resultado.getSuccessOrFailed(), resultado.getMensagem());
		return "redirect:/admin/estoques/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		ResultadoEstoque resultado = estoqueService.buscarPorId(new Estoque(id));
		model.addAttribute("estoque", resultado.getResultadoLista().get(0));
		model.addAttribute("listaLivros", this.listaLivros);
		return "/admin/estoque/cadastro";
	}

	@PostMapping("/editar")
	public String editar(Estoque estoque, RedirectAttributes attr) {
		ResultadoEstoque resultado = estoqueService.editar(estoque);
		attr.addFlashAttribute(resultado.getSuccessOrFailed(), resultado.getMensagem());
		return "redirect:/admin/estoques/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		ResultadoEstoque resultado = estoqueService.excluir(new Estoque(id));
		model.addAttribute(resultado.getSuccessOrFailed(), resultado.getMensagem());
		return listar(model);
	}
	
	public void carregaListaLivros() {
		ResultadoLivro resultado = livroService.buscarTodos();
		this.listaLivros = resultado.getResultadoLista();
		System.out.println(this.listaLivros.size());
	}

}
