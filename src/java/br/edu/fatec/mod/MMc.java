package br.edu.fatec.mod;

import br.edu.fatec.util.MathUtil;
import javax.faces.bean.ManagedBean;

/**
 * @author Carlos-Notebook
 */

@ManagedBean

public class MMc extends ModeloFila {

    private double r;

    public MMc(double taxaChegada, double taxaSaida, int numeroServidores) {
        super(taxaChegada, taxaSaida, numeroServidores);
        this.r = taxaChegada / taxaSaida;
    }

    @Override
    public double probSistemaVazio() {
        double soma = 0D;   //c = 3; n = 0,1,2
        for (int n = 0; n < getNumeroServidores(); n++) {
            soma += Math.pow(r, n) / (double) MathUtil.fatorial(n);
        }
        int c = getNumeroServidores();
        double b = (c * Math.pow(r, c)) / (MathUtil.fatorial(c) * (c - r));
        return Math.pow((soma + b), -1);
    }

    @Override
    public double probNClientesSistema(int n) {
        //      numerador
        // F = ------------
        //     denominador
        int c = getNumeroServidores();
        double denominador;
        double numerador = Math.pow(r, n);
        
        if (n == 0) {
            return probSistemaVazio();
        }else if (n >= 1 && n < c) {
            denominador = (double) MathUtil.fatorial(n);
        } else if (n >= c) {
            denominador = Math.pow(c, n - c) * (double) MathUtil.fatorial(c);
        } else {
            throw new IllegalArgumentException("N de ser maior ou igual a ZERO");
        }
        return probSistemaVazio() * (numerador / denominador);
    }

    @Override
    public double probMaisNClientesSistema(int n) {
        //P(N >=n) = 1 - P(0) + P(1) + .... + P(n-1)
        double probAteNMenos1 = 0;
        for (int i = 0; i < n; i++) {
            //System.out.println("prob[" + i +"] = " + probNClientesSistema(i) + "   " + (1.0 - probAteNMenos1));
            probAteNMenos1 += probNClientesSistema(i);
        }
       // System.out.println(probAteNMenos1);
        return 1.0 - probAteNMenos1;
    }

    @Override
    public double numeroMedioClientesFila() {
       int c = getNumeroServidores();
       double numerador = probSistemaVazio() * c * Math.pow(r, c + 1);
       double denominador = (double) MathUtil.fatorial(c) * Math.pow(c - r, 2);
       return numerador / denominador;
    }
    
    @Override
    public double numeroMedioClientesSistema() {
        double numerador;
        double denominador;
        int c = getNumeroServidores();
        numerador = Math.pow(r, c + 1) * c;
        denominador = MathUtil.fatorial(c) * Math.pow(c - r, 2);
        return r + (numerador / denominador) * probSistemaVazio();
    }

    @Override
    public double tempoMedioFila() {
        double numerador;
        double denominador;
        int c = getNumeroServidores();
        numerador = Math.pow(r, c) * getTaxaSaida();
        denominador = MathUtil.fatorial(c - 1) * Math.pow(c * getTaxaSaida() - getTaxaChegada(), 2);
        return (numerador / denominador) * probSistemaVazio();
    }

    @Override
    public double tempoMedioSistema() {
        return 1D / getTaxaSaida() + tempoMedioFila();
    }

    @Override
    public double probEsperaFilaMaiorT(double t) {
        double numerador;
        double d1, d2;
        int c = getNumeroServidores();
        numerador = Math.pow(r, c);
        d1 = (double) MathUtil.fatorial(c) * (1 - taxaOcupacao());
        d2 = Math.exp(Math.pow((c * getTaxaSaida() - getTaxaChegada()), t));
        return 1D - probSistemaVazio() * (numerador / (d1 * d2));
    }
}
