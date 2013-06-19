package br.edu.fatec;

import br.edu.fatec.dist.Exponencial;
import br.edu.fatec.dist.Poisson;
import br.edu.fatec.mod.MM1;
import br.edu.fatec.mod.MMc;
import br.edu.fatec.mod.ModeloFila;
import javax.faces.bean.ManagedBean;

/**
 * @author Carlos-Notebook
 */

@ManagedBean
public class Main {

    public static void main(String[] args) {
        /*int presicao = 4;

        ModeloFila modelo = new MM1(3, 4);
        modelo.setN(5);
        modelo.setTipoCliente("clientes");
        modelo.setUnidadeTempo("horas");
        modelo.setPrecisao(presicao);
        modelo.setTempoFila(10);    
        System.out.println(modelo);

        modelo = new MMc(60, 30, 3);
        modelo.setPrecisao(presicao);
        modelo.setN(5);
        modelo.setUnidadeTempo("horas");
        modelo.setTipoCliente("clientes");
        modelo.setTempoFila(5/60.0);
        System.out.println(modelo);*/
        
        System.out.println("EXER 03 ============================");
        System.out.printf("%.4f\n", Poisson.prob(3, 4));
        System.out.printf("%.4f\n", Poisson.probMenorIgualX(2, 4));
        System.out.printf("%.4f\n",Poisson.probMaiorIgualX(13, 4));
        
        System.out.println("EXER 04 ============================");
        System.out.printf("%.4f\n",Exponencial.probMaiorIgualX(10, 0.1));
        System.out.printf("%.4f\n",Exponencial.probMenorIgualX(10, 0.1));
        System.out.printf("%.4f\n",Exponencial.probMenorIgualX(3, 0.1));
        System.out.printf("%.4f\n",Exponencial.probXEntreAeB(3, 10, 0.1));
        
        ModeloFila modelo = new MM1(3.20, 4);
        modelo.setN(5);
        modelo.setTipoCliente("clientes");
        modelo.setUnidadeTempo("hora");
        modelo.setPrecisao(4);
        modelo.setTempoFila(10);    
        System.out.println(modelo);
        
        modelo = new MMc(60, 30, 3);
        modelo.setPrecisao(4);
        modelo.setN(5);
        modelo.setUnidadeTempo("horas");
        modelo.setTipoCliente("clientes");
        modelo.setTempoFila(5/60.0);
        System.out.println(modelo);
        
        
    }
}
