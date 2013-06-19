package br.edu.fatec.mod;


import javax.faces.bean.ManagedBean;

/**
 * @author Carlos-Notebook
 */

@ManagedBean
public abstract class ModeloFila {
	private double taxaChegada;
	private double taxaSaida;
	private int numeroServidores;
	
	//N�mero de clientes para calculo de probilidades.
	private int n;
	private String uc;
	private String ut;
	
	private double tempoFila = 10;
	
	//Precisao
	private String precisao = "4";
	
	public ModeloFila(double taxaChegada, double taxaSaida, int numeroServidores) {
		this.taxaChegada = taxaChegada;
		this.taxaSaida = taxaSaida;
		this.numeroServidores = numeroServidores;
	}
	
	public double getTaxaChegada() {
		return taxaChegada;
	}

	public void setTaxaChegada(double taxaChegada) {
		this.taxaChegada = taxaChegada;
	}

	public double getTaxaSaida() {
		return taxaSaida;
	}

	public void setTaxaSaida(double taxaSaida) {
		this.taxaSaida = taxaSaida;
	}

	public int getNumeroServidores() {
		return numeroServidores;
	}

	public void setNumeroServidores(int numeroServidores) {
		this.numeroServidores = numeroServidores;
	}
	
	/**
	 * Atribui o n�mero N de clientes para calculo de probabilidades. 
	 * @param n
	 */
	public void setN(int n) {
		this.n = n;
	}
	
	public void setTipoCliente(String value) {
		this.uc = value;
	}
	
	public void setUnidadeTempo(String unidade) {
		this.ut = unidade;
	}
	
	public void setPrecisao(int p) {
		this.precisao = String.valueOf(p);
	}
	
	public void setTempoFila(double v) {
		this.tempoFila = v;
	}
	
	//Medidas de Desempenho
	public double taxaOcupacao() {
		return taxaChegada/(taxaSaida*numeroServidores);
	}
	
	public abstract double probSistemaVazio();
	
	public abstract double probNClientesSistema(int n);
	
	public abstract double probMaisNClientesSistema(int n);
	
	public abstract double probEsperaFilaMaiorT(double t);
	
	public abstract double numeroMedioClientesSistema();
	
	public abstract double numeroMedioClientesFila();
	
	public abstract double tempoMedioFila();
	
	public abstract double tempoMedioSistema();
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("****** M/M/%d com [taxaChegada = %.{P}f] e [taxaSaida = %.{P}f] ******\n".replace("{P}", precisao) ,numeroServidores ,taxaChegada, taxaSaida));
		if (taxaOcupacao() < 1.0) {
			builder.append(String.format("Taxa Ocupacao: %.{P}f\n".replace("{P}", precisao), taxaOcupacao()));
			builder.append(String.format("Prob. Sistema Vazio: %.{P}f\n".replace("{P}", precisao), probSistemaVazio()));
			builder.append(String.format("Prob. %d Clientes: %.{P}f\n".replace("{P}", precisao), n, probNClientesSistema(n)));
			builder.append(String.format("Prob. Mais %d Clientes: %.{P}f\n".replace("{P}", precisao), n, probMaisNClientesSistema(n)));
			builder.append(String.format("Prob. Espera Fila maior %.P_f %s: %.P_f\n".replaceAll("P_", precisao), tempoFila, ut, probEsperaFilaMaiorT(tempoFila)));
			builder.append(String.format("Numero medio clientes Sistema: %.{P}f\n".replace("{P}", precisao), numeroMedioClientesSistema()));
			builder.append(String.format("Numero medio clientes Fila: %.{P}f\n".replace("{P}", precisao), numeroMedioClientesFila()));
			builder.append(String.format("Tempo medio sistema: %.{P}f %s\n".replace("{P}", precisao), tempoMedioSistema(), ut == null? "" : ut));
			builder.append(String.format("Tempo medio fila: %.{P}f %s\n".replace("{P}", precisao), tempoMedioFila(), ut == null? "" : ut));
		}else {
			builder.append(String.format("Nao e estavel ja que [taxaOcupacao >  1] => [%.{P}f > 1]".replace("{P}", precisao), taxaOcupacao()));
		}
		return builder.toString();
	}

}
