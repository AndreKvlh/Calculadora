package main.gui;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.calculadora.Calculadora;

public class Main extends Application {
	Calculadora calculadora = new Calculadora();
	static Scanner leitor = new Scanner(System.in);
	String n1, n2;
	
	@Override
	public void start(Stage palco) {
		VBox base = new VBox(10);
		base.setPadding(new Insets(10));
		TextField visor = new TextField(calculadora.getNumeroDigitado());
		visor.setMaxWidth(Double.MAX_VALUE);
		Scene cena = new Scene(base, 250, 400);
		visor.setEditable(false);
		visor.setAlignment(Pos.CENTER_RIGHT);
		visor.setPrefHeight(70);
		visor.setStyle("-fx-font-size: 28px;");
		
		GridPane teclado = new GridPane();
		teclado.add(visor, 0, 0, 4, 1);
		configurarTeclado(teclado, visor);
		
		base.getChildren().addAll(visor, teclado);
		
		palco.setTitle("Calculadora");
		palco.setScene(cena);
		palco.show();
	}

	public static void main(String[] args) {
		/*System.out.print("Digite um número: ");
		Calculadora.guardarNumero(checarEntrada(leitor));
		System.out.print("Selecione a operação: +, -, *, / ");
		String operacao = leitor.next();
		Calculadora.guardarOperacao(operacao);
		System.out.print("Digite um número: ");
		Calculadora.guardarNumero(checarEntrada(leitor));
		Calculadora.processarOperacao();*/
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
	
	public void configurarTeclado(GridPane teclado, TextField visor) {
		String[][] teclas = {
				{"%","CE","C","Backspace"},
				{"1/x", "x²", "²Vx", "/"},
				{"7","8","9","*"},
				{"4","5","6","-"},
				{"1","2","3","+"},
				{"+/-","0",".","="},
		};
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				Button botao = new Button(teclas[i][j]);
				botao.setPrefSize(70,50);
				
				botao.setOnAction(e -> {
					String tecla = botao.getText();
					switch (tecla) {
						case "1","2","3","4","5","6","7","8","9","0","." : 
							calculadora.digitarNaTela(tecla);
							visor.setText(calculadora.getNumeroDigitado());
							break;
						case "+","-","*","/":
							calculadora.guardarOperacao(tecla);
							visor.setText(calculadora.getResultado());
							break;
						case "+/-":
							calculadora.inverterSinal();
							visor.setText(calculadora.getNumeroDigitado());
							break;
						case "%": 
							calculadora.converterEmPorcento();
							visor.setText(calculadora.getNumeroDigitado());
							break;
						case "CE": 
							calculadora.limpar(false);
							visor.setText(calculadora.getNumeroDigitado());
							break;
						case "C": 
							calculadora.limpar(true);
							visor.setText(calculadora.getNumeroDigitado());
							break;
						case "Backspace": 
							calculadora.deletarNumero();
							visor.setText(calculadora.getNumeroDigitado());
							break;
						case "1/x":
							calculadora.inverterFracao();
							visor.setText(calculadora.getNumeroDigitado());
							break;
						case "x²": 
							calculadora.elevarAoQuadrado();
							visor.setText(calculadora.getNumeroDigitado());
							break;
						case "²Vx": 
							calculadora.raizQuadrada();
							visor.setText(calculadora.getNumeroDigitado());
							break;
						default: 
							calculadora.processarOperacao(true);
							visor.setText(calculadora.getResultado());
					}
				});
				
				teclado.add(botao, j, i + 1);
			}
		}
	}
}
