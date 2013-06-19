package br.edu.fatec.util;

import javax.faces.bean.ManagedBean;

/**
 * @author Carlos-Notebook
 */

@ManagedBean

public class MathUtil {
	
	public static long fatorial(int n) {
		if (n == 0 || n == 1) {
			return 1L;
		}
		long fatorial = 1;
		for (int i = n; i > 1; i--) {
			fatorial *= i;
		}
		return fatorial;
	}
	
}
