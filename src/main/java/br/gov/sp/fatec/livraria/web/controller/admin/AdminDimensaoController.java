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

import br.gov.sp.fatec.livraria.aplicacao.resultado.ResultadoDimensao;
import br.gov.sp.fatec.livraria.domain.Dimensao;
import br.gov.sp.fatec.livraria.service.DimensaoService;

@Controller
@RequestMapping("/admin/dimensoes")
public class AdminDimensaoController {

	@Autowired
	private DimensaoService dimensaoService;

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar() {
		ModelAndView modelAndView = new ModelAndView("/admin/dimensao/cadastro");
		Dimensao dimensao = new Dimensao();
		modelAndView.addObject("dimensao", dimensao);
		return modelAndView;
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		ResultadoDimensao resultado = dimensaoService.buscarTodos();
		model.addAttribute("dimensoes", resultado.getResultadoLista());
		return "/admin/dimensao/lista";
	}

	@PostMapping("/salvar")
	public String salvar(final Dimensao dimensao, RedirectAttributes attr) {
		ResultadoDimensao resultado = dimensaoService.salvar(dimensao);
		attr.addFlashAttribute(resultado.getSuccessOrFailed(), resultado.getMensagem());
		return "redirect:/admin/dimensoes/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		ResultadoDimensao resultado = dimensaoService.buscarPorId(new Dimensao(id));
		model.addAttribute("cliente", resultado.getResultadoLista().get(0));
		return "/admin/dimensao/cadastro";
	}

	@PostMapping("/editar")
	public String editar(Dimensao dimensao, RedirectAttributes attr) {
		ResultadoDimensao resultado = dimensaoService.editar(dimensao);
		attr.addFlashAttribute(resultado.getSuccessOrFailed(), resultado.getMensagem());
		return "redirect:/admin/dimensoes/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		ResultadoDimensao resultado = dimensaoService.excluir(new Dimensao(id));
		model.addAttribute(resultado.getSuccessOrFailed(), resultado.getMensagem());
		return listar(model);
	}

}
