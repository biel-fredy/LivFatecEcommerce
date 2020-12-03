package br.gov.sp.fatec.livraria.aplicacao.resultado;

public abstract class Resultado {

	private String mensagem;
	private String successOrFailed;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getSuccessOrFailed() {
		return successOrFailed;
	}

	public void setSuccessOrFailed(String successOrFailed) {
		this.successOrFailed = successOrFailed;
	}

}
