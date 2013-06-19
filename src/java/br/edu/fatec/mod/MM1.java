package br.edu.fatec.mod;
import javax.faces.bean.ManagedBean;

/**
 * @author Carlos-Notebook
 */

@ManagedBean

public class MM1 extends ModeloFila {

	public MM1(double taxaChegada, double taxaSaida) {
		super(taxaChegada, taxaSaida, 1);
	}

	@Override
	public double probSistemaVazio() {
		return 1 - taxaOcupacao();
	}

	@Override
	public double probNClientesSistema(int n) {
		return Math.pow(taxaOcupacao(), n) * probSistemaVazio();
	}

	@Override
	public double probMaisNClientesSistema(int n) {
		return Math.pow(taxaOcupacao(), n);
	}

	@Override
	public double numeroMedioClientesSistema() {
		return taxaOcupacao() / probSistemaVazio();
	}

	@Override
	public double numeroMedioClientesFila() {
		return (taxaOcupacao() * taxaOcupacao()) / probSistemaVazio();
	}

	@Override
	public double tempoMedioFila() {
		return taxaOcupacao() / (getTaxaSaida() - getTaxaChegada());
	}

	@Override
	public double tempoMedioSistema() {
		return 1D / (getTaxaSaida() - getTaxaChegada());
	}

	@Override
	public double probEsperaFilaMaiorT(double t) {
		return taxaOcupacao() * Math.exp(-(getTaxaSaida() - getTaxaChegada()) * t);
	}

}
