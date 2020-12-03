package br.gov.sp.fatec.livraria.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import br.gov.sp.fatec.livraria.aplicacao.Sessao;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoCliente;
import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoLivro;
import br.gov.sp.fatec.livraria.domain.Cliente;
import br.gov.sp.fatec.livraria.domain.ItemVenda;
import br.gov.sp.fatec.livraria.domain.Livro;
import br.gov.sp.fatec.livraria.service.ClienteService;
import br.gov.sp.fatec.livraria.service.LivroService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {
	
	@Autowired
	private LivroService livroService;
	
	@Autowired
	private ClienteService clienteService;
	
	private List<Livro> listaLivros = new ArrayList<Livro>();
	
	private Cliente cliente;

	@GetMapping("/listar")
	public ModelAndView listarCarrinho(@SessionAttribute("sessao") Sessao sessao) {		
		ModelAndView modelAndView = new ModelAndView("/carrinho/lista");
		procuraCliente(sessao.getUsuario().getCliente());
		sessao.getUsuario().setCliente(this.cliente);
		modelAndView.addObject("carrinho", sessao.getCarrinho());
		modelAndView.addObject("valorTotal", sessao.getCarrinho().totalCarrinho());
		modelAndView.addObject("cliente", this.cliente);
		
		System.out.println("cheg qesw");
		return modelAndView;
	}
	
	@GetMapping("/adicionar/{id}")
	public ModelAndView addUnidade(@SessionAttribute("sessao") Sessao sessao, @PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("/carrinho/lista");
		carregaListaLivros();
		
		for (ItemVenda item : sessao.getCarrinho().getItensVenda()){
			if (item.getLivro().getId() == id) {
				for (Livro livro : this.listaLivros) {
					if (item.getLivro().getId() == livro.getId()) {
						if (livro.getQuantidadeEstoque() - (item.getQuantidade()+1) >= 0) {
							System.out.println(livro.getQuantidadeEstoque() - (item.getQuantidade()+1));
							item.addQuantidade();
						}
						else {
							sessao.setMsg("Não há quantidade em estoque.");
							modelAndView.addObject("msg", sessao.getMsg());
						}
					}
				}
			}
		}
				
		modelAndView.addObject("carrinho", sessao.getCarrinho());
		modelAndView.addObject("valorTotal", sessao.getCarrinho().totalCarrinho());
		modelAndView.addObject("cliente", this.cliente);
		return modelAndView;
	}
	
	@GetMapping("/remover/{id}")
	public ModelAndView removeUnidade(@SessionAttribute("sessao") Sessao sessao, @PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("/carrinho/lista");
		Boolean removeItem = false;
		
		for (ItemVenda item : sessao.getCarrinho().getItensVenda()){
			if (item.getLivro().getId() == id) {
				if (item.getQuantidade()-1 > 0) {
					item.removeQuantidade();
				}
				else {
					removeItem = true;
				}
			}
		}
		
		if (removeItem) {
			sessao.getCarrinho().removeItem(id);
		}
		
		modelAndView.addObject("carrinho", sessao.getCarrinho());
		modelAndView.addObject("valorTotal", sessao.getCarrinho().totalCarrinho());
		modelAndView.addObject("cliente", this.cliente);
		return modelAndView;
	}
	
	@GetMapping("/removerItem/{id}")
	public ModelAndView removerItem(@SessionAttribute("sessao") Sessao sessao, @PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("/carrinho/lista");		
		sessao.getCarrinho().removeItem(id);
		modelAndView.addObject("carrinho", sessao.getCarrinho());
		modelAndView.addObject("valorTotal", sessao.getCarrinho().totalCarrinho());
		modelAndView.addObject("cliente", this.cliente);
		return modelAndView;
	}
	
	public void carregaListaLivros() {
		ResultadoLivro resultado = livroService.buscarTodos();
		
		for (Livro livro : resultado.getResultadoLista()) {
			livro.carregaQuantidadeEstoque();
		}
		
		this.listaLivros = resultado.getResultadoLista();
	}
	
	public void procuraCliente(Cliente cliente) {
		ResultadoCliente resultado = clienteService.buscarPorId(cliente);
		Cliente clienteCarregado = resultado.getResultadoLista().get(0);
		this.cliente = clienteCarregado;
	}


}
