package br.gov.sp.fatec.livraria.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.gov.sp.fatec.livraria.aplicacao.Sessao;
import br.gov.sp.fatec.livraria.aplicacao.Usuario;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoLivro;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoUsuario;
import br.gov.sp.fatec.livraria.domain.Carrinho;
import br.gov.sp.fatec.livraria.domain.ItemVenda;
import br.gov.sp.fatec.livraria.domain.Livro;
import br.gov.sp.fatec.livraria.service.LivroService;
import br.gov.sp.fatec.livraria.service.UsuarioService;

@Controller
@SessionAttributes("sessao")
public class HomeController {

	@Autowired
	private LivroService livroService;

	@Autowired
	private UsuarioService usuarioService;

	List<Livro> listaLivros = new ArrayList<Livro>();

	@GetMapping("/")
	public ModelAndView homeDireto() {
		return home();
	}

	@GetMapping("/home")
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("/home");
		String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
		Sessao sessao = new Sessao();

		sessao.setNomeUsuario(nomeUsuario);
		sessao.setUsuario(usuarioLogado());
		sessao.setCarrinho(new Carrinho());
		
		carregaListaLivros();
		model.addObject("sessao", sessao);
		model.addObject("listaLivros", this.listaLivros);
		return model;
	}

	@GetMapping("/liv/home")
	public ModelAndView homeLiv(@ModelAttribute("sessao") Sessao sessao) {
		ModelAndView model = new ModelAndView("/liv/home");
		carregaListaLivros();
		model.addObject("sessao", sessao);
		model.addObject("listaLivros", this.listaLivros);
		return model;
	}

	@GetMapping("/carrinho/{id}")
	public ModelAndView addCarrinho(@ModelAttribute("sessao") Sessao sessao, @PathVariable("id") Long idLivro,
			final BindingResult bindingResult) {
		ModelAndView model = new ModelAndView("/liv/home");
		ItemVenda itemVenda = new ItemVenda();
		Livro livro = new Livro();
		Boolean temLivro = false;
		
		carregaListaLivros();

		for (Livro liv : this.listaLivros) {
			if (liv.getId() == idLivro) {
				livro = liv;
			}
		}
		
		for (ItemVenda item : sessao.getCarrinho().getItensVenda()) {
			if (item.getLivro().getId() == idLivro) {
				if (livro.getQuantidadeEstoque() - (item.getQuantidade()+1) >= 0) {
					item.addQuantidade();
				}
				else {
					sessao.setMsg("Atingido limite de estoque disponível.");
					model.addObject("msg", sessao.getMsg());
				}
				temLivro = true;
			}
		}
		
		if (!temLivro) {
			itemVenda.setLivro(livro);
			itemVenda.setValorVenda(livro.getPrecoVenda());
			itemVenda.setQuantidade(1);
			sessao.getCarrinho().addItem(itemVenda);
		}
		
		model.addObject("listaLivros", this.listaLivros);
		model.addObject("sessao", sessao);
		return model;
	}

	public void carregaListaLivros() {
		ResultadoLivro resultado = livroService.buscarTodos();
		
		for (Livro livro : resultado.getResultadoLista()) {
			livro.carregaQuantidadeEstoque();
		}
		
		this.listaLivros = resultado.getResultadoLista();
	}

	public Usuario usuarioLogado() {
		ResultadoUsuario resultado = new ResultadoUsuario();
		Usuario usuario = new Usuario();

		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		if (username != "anonymousUser") {
			resultado = usuarioService.buscarPorNomeUsuario(username);
			if (!resultado.getResultadoLista().isEmpty()) {
				usuario = resultado.getResultadoLista().get(0);
			}
		}

		return usuario;
	}

}
