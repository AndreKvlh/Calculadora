package calculadora;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

public class Calculadora {
	private double resultado = 0;
	private String operacaoAtual = "";
	private String numeroDigitado = "0";
	private String historico = "";
	private boolean podeDigitar = true;
	private boolean divisaoPorZero = false;
	private double[] numeros = new double[2];
	private int numAtual = 0;
	
	public <T extends Number> double operacoesBasicas(T a, T b, String operador) {
		double resultado;
		switch (operador) {
			case "+" -> resultado = a.doubleValue() + b.doubleValue();
			case "-" -> resultado = a.doubleValue() - b.doubleValue();
			case "*" -> resultado = a.doubleValue() * b.doubleValue();
			case "/" -> resultado = this.verificarDivPorZero(a.doubleValue(), b.doubleValue());
			default -> resultado = 0;
		};
		return resultado;
	}
	
	public double verificarDivPorZero (double a, double b) {
		if (b == 0) {
			this.divisaoPorZero = true;
			return 0;
		}
		return a / b;
	}
	
	public void elevarAoQuadrado() {
		double stringEmNumero = Double.parseDouble(numeroDigitado);
		this.numeroDigitado = this.formatarNumero(Math.pow(stringEmNumero, 2));
	}
	
	public void raizQuadrada() {
		double stringEmNumero = Double.parseDouble(numeroDigitado);
		this.numeroDigitado = this.formatarNumero(Math.sqrt(stringEmNumero));
	}
	
	public void inverterFracao() {
		double stringEmNumero = Double.parseDouble(numeroDigitado);
		this.numeroDigitado = this.formatarNumero(1 / stringEmNumero);
	}
	
	public void guardarNumero(String numero) {
		numeroDigitado = numero;
	}
	
	public void guardarOperacao(String operacao) {
		this.podeDigitar = false;
		this.numeros[this.numAtual] = Double.parseDouble(this.numeroDigitado);
		if (this.numAtual == 0) {
			this.numAtual = 1;
		} else {
			this.processarOperacao(false);
			if (divisaoPorZero) return;
		}
		this.operacaoAtual = operacao;
		this.numeroDigitado = this.formatarNumero(this.numeros[0]);
		this.historico = String.format("%s %s ", formatarNumero(this.numeros[0]), this.operacaoAtual);
		System.out.println(operacaoAtual);
	}
	
	public void processarOperacao(boolean operacaoFinal) {
		this.podeDigitar = false;
		this.numeros[this.numAtual] = Double.parseDouble(this.numeroDigitado);
		this.resultado = operacoesBasicas(this.numeros[0], this.numeros[1], this.operacaoAtual);
		System.out.println(this.divisaoPorZero);
		if (divisaoPorZero) {
			this.limpar(true);
			this.numeroDigitado = "Não é possível dividir por zero.";
			return;
		}
		if (!operacaoFinal) {
			this.numeros[0] = this.resultado;
		} else {
			this.historico = String.format("%s %s %s =", formatarNumero(this.numeros[0]), this.operacaoAtual, formatarNumero(this.numeros[1]));
			this.numAtual = 0;
		}
		this.numeroDigitado = this.formatarNumero(this.resultado);
		System.out.printf("O resultado é %f", this.resultado);
	}
	
	public void converterEmPorcento() {
		double stringEmDouble = Double.parseDouble(this.numeroDigitado);
		if (this.numAtual == 0) {
			this.numeroDigitado = "0";
			return;
		}
		double numeroAnterior = this.numeros[0];
		double porcentagem = stringEmDouble / 100;
		this.numeroDigitado = this.formatarNumero(numeroAnterior * porcentagem);
	}
	
	public void inverterSinal() {
		double stringEmDouble = Double.parseDouble(this.numeroDigitado);
		double numeroInvertido = stringEmDouble * -1;
		this.numeroDigitado = this.formatarNumero(numeroInvertido);
	}
	
	public void digitarNaTela(String valor) {
		if(!this.numeroDigitado.contains(".") || this.eNumero(valor)) {
			if(this.numeroDigitado.equals("0") && !valor.equals(".") || !this.podeDigitar) {
				this.numeroDigitado = valor;
				this.podeDigitar = true;
				return;
			}
			this.numeroDigitado += valor;
		}
		System.out.println(numeroDigitado);
	}
	
	public boolean eNumero(String valor) {
		try {
			Double.parseDouble(valor);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public void limpar(boolean tudo) {
		if(tudo) {
			this.resultado = 0;
			this.operacaoAtual = "";
			for (double n : this.numeros) {
				n = 0;
			}
			this.historico = "";
			this.numAtual = 0;
		}
		this.numeroDigitado = "0";
	}
	
	public void deletarNumero() {
		if (this.numeroDigitado.length() > 0) {
			this.numeroDigitado = this.numeroDigitado.substring(0, this.numeroDigitado.length() - 1);
		}
		if (this.numeroDigitado.isEmpty() || !this.eNumero(this.numeroDigitado)) {
			this.numeroDigitado = "0";
		}
	}
	
	public String formatarNumero(double valor) {
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.US);
		DecimalFormat decimal = new DecimalFormat("0.#########",simbolos);
		return decimal.format(valor);
	}
	
	public String getResultado() {
		return formatarNumero(resultado);
	}
	
	public String getNumeroDigitado() {
		return this.numeroDigitado;
	}
	
	public String getPrimeiroNum() {
		return formatarNumero(this.numeros[0]);
	}
	
	public String getHistorico() {
		return this.historico;
	}
	
	public boolean getDivPorZero() {
		return divisaoPorZero;
	}
	
	public void setDivPorZero(boolean valor) {
		this.divisaoPorZero = valor;
	}
}
