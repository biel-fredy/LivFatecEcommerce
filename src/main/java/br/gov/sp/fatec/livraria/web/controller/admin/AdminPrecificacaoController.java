package br.gov.sp.fatec.livraria.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoPrecificacao;
import br.gov.sp.fatec.livraria.domain.Precificacao;
import br.gov.sp.fatec.livraria.service.PrecificacaoService;

@Controller
@RequestMapping("/admin/precificacoes")
public class AdminPrecificacaoController {

	@Autowired
	private PrecificacaoService precificacaoService;

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("/admin/precificacao/cadastro");
		Precificacao precificacao = new Precificacao();
		modelAndView.addObject("precificacao", precificacao);
		return modelAndView;
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		ResultadoPrecificacao resultado = precificacaoService.buscarTodos();
		model.addAttribute("precificacoes", resultado.getResultadoLista());
		return "/admin/precificacao/lista";
	}

	@PostMapping("/salvar")
	public String salvar(final Precificacao precificacao, RedirectAttributes attr) {
		ResultadoPrecificacao resultado = precificacaoService.salvar(precificacao);
		attr.addFlashAttribute(resultado.getSuccessOrFailed(), resultado.getMensagem());
		return "redirect:/admin/precificacoes/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		ResultadoPrecificacao resultado = precificacaoService.buscarPorId(new Precificacao(id));
		model.addAttribute("precificacao", resultado.getResultadoLista().get(0));
		return "/admin/precificacao/cadastro";
	}

	@PostMapping("/editar")
	public String editar(Precificacao precificacao, RedirectAttributes attr) {
		ResultadoPrecificacao resultado = precificacaoService.editar(precificacao);
		attr.addFlashAttribute(resultado.getSuccessOrFailed(), resultado.getMensagem());
		return "redirect:/admin/precificacoes/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		ResultadoPrecificacao resultado = precificacaoService.excluir(new Precificacao(id));
		model.addAttribute(resultado.getSuccessOrFailed(), resultado.getMensagem());
		return listar(model);
	}

}
