package br.gov.sp.fatec.livraria.web.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoDimensao;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoLivro;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoPrecificacao;
import br.gov.sp.fatec.livraria.domain.Autor;
import br.gov.sp.fatec.livraria.domain.Capa;
import br.gov.sp.fatec.livraria.domain.Categoria;
import br.gov.sp.fatec.livraria.domain.Dimensao;
import br.gov.sp.fatec.livraria.domain.Livro;
import br.gov.sp.fatec.livraria.domain.Precificacao;
import br.gov.sp.fatec.livraria.service.DimensaoService;
import br.gov.sp.fatec.livraria.service.LivroService;
import br.gov.sp.fatec.livraria.service.PrecificacaoService;

@Controller
@RequestMapping("/admin/livros")
public class AdminLivroController {

	@Autowired
	private LivroService livroService;

	@Autowired
	private PrecificacaoService precificacaoService;

	@Autowired
	private DimensaoService dimensaoService;
	
	private List<Dimensao> listaDimensao;

	private List<Precificacao> listaPrecificacao;

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("/admin/livro/cadastro");
		carregaListaPrecificacao();
		carregaListaDimensao();
		Livro livro = new Livro();
		Autor autor = new Autor();
		Dimensao dimensoes = new Dimensao();
		Capa capa = new Capa();
		Categoria categoria = new Categoria();
		Precificacao precificacao = new Precificacao();
		livro.addAutor(autor);
		livro.setDimensoes(dimensoes);
		livro.setCapa(capa);
		livro.addCategoria(categoria);
		livro.setPrecificacao(precificacao);
		modelAndView.addObject("livro", livro);
		modelAndView.addObject("listaPrecificacao", this.listaPrecificacao);
		modelAndView.addObject("listaDimensao", this.listaDimensao);
		return modelAndView;
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		ResultadoLivro resultado = livroService.buscarTodos();
		model.addAttribute("livros", resultado.getResultadoLista());
		return "/admin/livro/lista";
	}

	@PostMapping("/salvar")
	public String salvar(final Livro livro, RedirectAttributes attr) {
		ResultadoLivro resultado = livroService.salvar(livro);
		attr.addFlashAttribute(resultado.getSuccessOrFailed(), resultado.getMensagem());
		return "redirect:/admin/livros/cadastrar";
	}

	@PostMapping(value = "/salvar", params = { "addAutor" }, name = "addAutor")
	public ModelAndView addAutor(final Livro livro, @RequestParam("addAutor") String addRow,
			final BindingResult bindingResult) {
		ModelAndView model = new ModelAndView("/admin/livro/cadastro");
		Autor autor = new Autor();
		livro.addAutor(autor);
		model.addObject("livro", livro);
		model.addObject("listaPrecificacao", this.listaPrecificacao);
		model.addObject("listaDimensao", this.listaDimensao);
		return model;
	}

	@PostMapping(value = "/salvar", params = { "removeAutor" })
	public ModelAndView removeAutor(final Livro livro, final BindingResult bindingResult,
			final HttpServletRequest req) {
		ModelAndView model = new ModelAndView("/admin/livro/cadastro");
		final Integer rowId = Integer.valueOf(req.getParameter("removeAutor"));
		livro.removeAutor(rowId.intValue());
		model.addObject("livro", livro);
		model.addObject("listaPrecificacao", this.listaPrecificacao);
		model.addObject("listaDimensao", this.listaDimensao);
		return model;
	}

	@PostMapping(value = "/salvar", params = { "addCat" }, name = "addCat")
	public ModelAndView addCategoria(final Livro livro, @RequestParam("addCat") String addCat,
			final BindingResult bindingResult) {
		ModelAndView model = new ModelAndView("/admin/livro/cadastro");
		Categoria categoria = new Categoria();
		livro.addCategoria(categoria);
		model.addObject("livro", livro);
		model.addObject("listaPrecificacao", this.listaPrecificacao);
		model.addObject("listaDimensao", this.listaDimensao);
		return model;
	}

	@PostMapping(value = "/salvar", params = { "removeCat" })
	public ModelAndView removeCategoria(final Livro livro, final BindingResult bindingResult,
			final HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeCat"));
		ModelAndView model = new ModelAndView("/admin/livro/cadastro");
		livro.removeCategoria(rowId.intValue());
		model.addObject("livro", livro);
		model.addObject("listaPrecificacao", this.listaPrecificacao);
		model.addObject("listaDimensao", this.listaDimensao);
		return model;
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		ResultadoLivro resultado = livroService.buscarPorId(new Livro(id));
		model.addAttribute("livro", resultado.getResultadoLista().get(0));
		model.addAttribute("listaDimensao", this.listaDimensao);
		model.addAttribute("listaPrecificacao", this.listaPrecificacao);
		return "/admin/livro/cadastro";
	}

	@PostMapping("/editar")
	public String editar(Livro livro, RedirectAttributes attr) {
		ResultadoLivro resultado = livroService.editar(livro);
		attr.addFlashAttribute(resultado.getSuccessOrFailed(), resultado.getMensagem());
		return "redirect:/admin/livros/cadastrar";
	}

	@PostMapping(value = "/editar", params = { "addAutor" }, name = "addAutor")
	public ModelAndView editarAddAutor(final Livro livro, @RequestParam("addAutor") String addRow,
			final BindingResult bindingResult) {
		ModelAndView model = new ModelAndView("/admin/livro/cadastro");
		Autor autor = new Autor();
		livro.addAutor(autor);		
		model.addObject("livro", livro);
		model.addObject("listaPrecificacao", this.listaPrecificacao);
		model.addObject("listaDimensao", this.listaDimensao);
		return model;
	}

	@PostMapping(value = "/editar", params = { "removeAutor" })
	public ModelAndView editarRemoveAutor(final Livro livro, final BindingResult bindingResult,
			final HttpServletRequest req) {
		ModelAndView model = new ModelAndView("/admin/livro/cadastro");
		final Integer rowId = Integer.valueOf(req.getParameter("removeAutor"));
		livro.removeAutor(rowId.intValue());
		model.addObject("livro", livro);
		model.addObject("listaPrecificacao", this.listaPrecificacao);
		model.addObject("listaDimensao", this.listaDimensao);
		return model;
	}

	@PostMapping(value = "/editar", params = { "addCat" }, name = "addCat")
	public ModelAndView editarAddCategoria(final Livro livro, @RequestParam("addCat") String addCat,
			final BindingResult bindingResult) {
		ModelAndView model = new ModelAndView("/admin/livro/cadastro");
		Categoria categoria = new Categoria();
		livro.addCategoria(categoria);
		model.addObject("livro", livro);
		model.addObject("listaPrecificacao", this.listaPrecificacao);
		model.addObject("listaDimensao", this.listaDimensao);
		return model;
	}

	@PostMapping(value = "/editar", params = { "removeCat" })
	public ModelAndView editarRemoveCategoria(final Livro livro, final BindingResult bindingResult,
			final HttpServletRequest req) {
		ModelAndView model = new ModelAndView("/admin/livro/cadastro");
		final Integer rowId = Integer.valueOf(req.getParameter("removeCat"));
		livro.removeCategoria(rowId.intValue());
		model.addObject("livro", livro);
		model.addObject("listaPrecificacao", this.listaPrecificacao);
		model.addObject("listaDimensao", this.listaDimensao);
		return model;
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		ResultadoLivro resultado = livroService.excluir(new Livro(id));
		model.addAttribute(resultado.getSuccessOrFailed(), resultado.getMensagem());
		return listar(model);
	}
	
	@GetMapping("/ativar/{id}")
	public String ativarLivro(@PathVariable("id") Long id, ModelMap model) {
		ResultadoLivro resultado = livroService.buscarPorId(new Livro(id));
		Livro livro = resultado.getResultadoLista().get(0);
		livro.setStatus(!livro.getStatus());
		System.out.println(livro.getStatus());
		livroService.editar(livro);
		return listar(model);
	}

	public void carregaListaPrecificacao() {
		ResultadoPrecificacao resultado = precificacaoService.buscarTodos();
		this.listaPrecificacao = resultado.getResultadoLista();
	}
	
	public void carregaListaDimensao() {
		ResultadoDimensao resultado = dimensaoService.buscarTodos();
		this.listaDimensao = resultado.getResultadoLista();
	}

}
