package main.calculadora;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

public class Calculadora {
	private double resultado = 0;
	private String operacaoAtual = "";
	private String numeroDigitado = "0";
	private boolean podeDigitar = true;
	private ArrayList<String> numeros = new ArrayList<>();
	
	public <T extends Number> double operacoesBasicas(T a, T b, String operador) {
		double resultado;
		switch (operador) {
			case "+" -> resultado = a.doubleValue() + b.doubleValue();
			case "-" -> resultado = a.doubleValue() - b.doubleValue();
			case "*" -> resultado = a.doubleValue() * b.doubleValue();
			case "/" -> resultado = b.doubleValue() != 0 ? a.doubleValue() / b.doubleValue() : 0;
			case "^" -> resultado = Math.pow(a.doubleValue(), 2);
			case "v" -> resultado = Math.sqrt(a.doubleValue());
			case "n" -> resultado = 1 / a.doubleValue();
			default -> resultado = 0;
		};
		return resultado;
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
		numeros.add(numeroDigitado);
	}
	
	public void guardarOperacao(String operacao) {
		this.podeDigitar = !this.podeDigitar;
		if (this.operacaoAtual.isEmpty()) {
			resultado = Double.parseDouble(this.numeroDigitado);
		} else {
			this.processarOperacao(false);
		}
		operacaoAtual = operacao;
		System.out.println(operacaoAtual);
	}
	
	public void processarOperacao(boolean limpar) {
		double n1 = this.resultado;
		double n2 = Double.parseDouble(this.numeroDigitado);
		this.resultado = operacoesBasicas(n1, n2, this.operacaoAtual);
		if (limpar) {
			this.numeroDigitado = "0";
		}
		System.out.printf("O resultado é %f", this.resultado);
	}
	
	public void converterEmPorcento() {
		double stringEmDouble = Double.parseDouble(this.numeroDigitado);
		double numeroAnterior = this.resultado;
		if (numeroAnterior == 0) {
			this.numeroDigitado = "0";
			return;
		}
		double porcentagem = stringEmDouble / 100;
		this.numeroDigitado = this.formatarNumero(numeroAnterior * porcentagem);
	}
	
	public void inverterSinal() {
		double stringEmDouble = Double.parseDouble(this.numeroDigitado);
		double numeroInvertido = stringEmDouble * -1;
		this.numeroDigitado = this.formatarNumero(numeroInvertido);
	}
	
	public void digitarNaTela(String valor) {
		if(!numeroDigitado.contains(".") || this.eNumero(valor)) {
			if(numeroDigitado.equals("0") || !this.podeDigitar) {
				numeroDigitado = valor;
				if (!this.podeDigitar) this.podeDigitar = !this.podeDigitar;
				return;
			}
			numeroDigitado += valor;
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
			resultado = 0;
			operacaoAtual = "";
			numeros.clear();
		}
		numeroDigitado = "0";
	}
	
	public void deletarNumero() {
		if (numeroDigitado.length() > 0) {
			numeroDigitado = numeroDigitado.substring(0, numeroDigitado.length() - 1);
		}
		if (numeroDigitado.isEmpty()) {
			numeroDigitado = "0";
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
		return numeroDigitado;
	}
}
