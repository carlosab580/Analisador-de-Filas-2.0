package br.edu.fatec.controller;

import br.edu.fatec.mod.MM1;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class MM1Controller {

    private double taxaChegada;
    private double taxaSaida;
    private int c;

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

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public void calcular() {
        System.out.println("Calculando ......");
    }
    
    public String resultado() {
        MM1 m = new MM1(getTaxaChegada(), getTaxaSaida());
        return m.toString();
    }
}
