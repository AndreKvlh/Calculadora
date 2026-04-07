package main.calculadora;

import java.util.ArrayList;

public class Calculadora {
	private double resultado = 0;
	private String operacaoAtual = "";
	private String numeroDigitado = "";
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
	
	public void guardarNumero(String numero) {
		numeroDigitado = numero;
		numeros.add(numeroDigitado);
	}
	
	public void guardarOperacao(String operacao) {
		operacaoAtual = operacao;
		System.out.println(operacaoAtual);
	}
	
	public void processarOperacao() {
		double n1 = Double.parseDouble(numeros.get(0));
		double n2 = Double.parseDouble(numeros.get(1));
		resultado = operacoesBasicas(n1, n2, operacaoAtual);
		System.out.printf("O resultado é %f", resultado);
	}
	
	public void converterEmPorcento() {
		double stringEmDouble = Double.parseDouble(numeroDigitado);
		double numeroAnterior = Double.parseDouble(numeros.get(0));
		double porcentagem = stringEmDouble / 100;
		numeroDigitado = Double.toString(numeroAnterior * porcentagem);
		numeros.set(numeros.size() - 1, numeroDigitado);
	}
	
	public void inverterSinal() {
		double stringEmDouble = Double.parseDouble(numeroDigitado);
		double numeroInvertido = stringEmDouble * -1;
		numeroDigitado = Double.toString(numeroInvertido);
		numeros.set(numeros.size() - 1, numeroDigitado);
	}
	
	public void digitarNaTela(String valor) {
		if(!numeroDigitado.contains(".") || this.eNumero(valor))numeroDigitado += valor;
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
		numeroDigitado = "";
	}
	
	public void deletarNumero() {
		if (numeroDigitado.length() > 0) {
			numeroDigitado = numeroDigitado.substring(0, numeroDigitado.length() - 1);
		}
		if (numeroDigitado.isEmpty()) {
			numeroDigitado = "0";
		}
	}
	
	public double getResultado() {
		return resultado;
	}
	
	public String getNumeroDigitado() {
		return numeroDigitado;
	}
}
