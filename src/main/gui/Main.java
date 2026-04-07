package main.gui;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import main.calculadora.Calculadora;

public class Main extends Application {
	Calculadora calculadora = new Calculadora();
	static Scanner leitor = new Scanner(System.in);
	String n1, n2;
	
	@Override
	public void start(Stage palco) {
		palco.setTitle("Olá, mundo!");
		palco.show();
	}

	public static void main(String[] args) {
		System.out.print("Digite um número: ");
		Calculadora.guardarNumero(checarEntrada(leitor));
		System.out.print("Selecione a operação: +, -, *, / ");
		String operacao = leitor.next();
		Calculadora.guardarOperacao(operacao);
		System.out.print("Digite um número: ");
		Calculadora.guardarNumero(checarEntrada(leitor));
		Calculadora.processarOperacao();
		launch(args);
	}

	public static String checarEntrada(Scanner leitor) {
		while (true) {
			String entrada = leitor.next();
			try {
				Double.parseDouble(entrada);
				return entrada;
			} catch (NumberFormatException e) {
				System.out.println("ERRO! Digite um número válido");
			}
		}
	}
}
