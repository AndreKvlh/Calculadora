package gui;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import calculadora.Calculadora;

public class Main extends Application {
	Calculadora calculadora = new Calculadora();
	static Scanner leitor = new Scanner(System.in);
	String n1, n2;
	List<String> botoesAtivosNoErro = Arrays.asList("C","CE","Backspace","0","1","2","3","4","5","6","7","8","9");
	
	@Override
	public void start(Stage palco) {
		VBox base = new VBox(0);
		base.setAlignment(Pos.CENTER_RIGHT);
		base.setPadding(new Insets(10));
		
		Label historico = new Label("");
		historico.setStyle("-fx-font-size: 14px; -fx-text-fill: gray;");
		
		TextField visor = new TextField(calculadora.getNumeroDigitado());
		visor.setMaxWidth(Double.MAX_VALUE);
		Scene cena = new Scene(base, 250, 400);
		visor.setEditable(false);
		visor.setAlignment(Pos.CENTER_RIGHT);
		visor.setPrefHeight(70);
		visor.setStyle("-fx-font-size: 32px; -fx-background-color: transparent;");
		
		GridPane teclado = new GridPane();
		teclado.add(visor, 0, 0, 4, 1);
		configurarTeclado(teclado, visor, historico);
		
		base.getChildren().addAll(historico, visor, teclado);
		
		palco.setTitle("Calculadora");
		palco.setScene(cena);
		palco.show();
	}

	public static void main(String[] args) {
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
	
	public void desativarBotoes(boolean desativar, GridPane teclado) {
		if (desativar) {
			for (Node no : teclado.getChildren()) {
				if (no instanceof Button btn) {
					String categoria = (String) no.getUserData();
					if ("INATIVO".equals(categoria)) {
						btn.setDisable(true);
					}
				}
			}
			calculadora.setDivPorZero(false);
		} else {
			for (Node n : teclado.getChildren()) {
				if (n instanceof Button btn) {
					btn.setDisable(false);
				}
			}
				
		}
	}
	
	public void configurarTeclado(GridPane teclado, TextField visor, Label historico) {
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
				String tecla = botao.getText();
				if (!botoesAtivosNoErro.contains(tecla)) {
					botao.setUserData("INATIVO");
				}
				
				botao.setOnAction(e -> {
					switch (tecla) {
						case "1","2","3","4","5","6","7","8","9","0","." : 
							calculadora.digitarNaTela(tecla);
							visor.setText(calculadora.getNumeroDigitado());
							break;
						case "+","-","*","/":
							calculadora.guardarOperacao(tecla);
							if (calculadora.getDivPorZero()) {
								visor.setText(calculadora.getNumeroDigitado());
								break;
							}
							visor.setText(calculadora.getPrimeiroNum());
							historico.setText(calculadora.getHistorico());
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
							historico.setText(calculadora.getHistorico());
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
							if (calculadora.getDivPorZero()) {
								visor.setText(calculadora.getNumeroDigitado());
								break;
							}
							visor.setText(calculadora.getResultado());
							historico.setText(calculadora.getHistorico());
					}
					desativarBotoes(calculadora.getDivPorZero(), teclado);
				});
				
				teclado.add(botao, j, i + 1);
			}
		}
	}
}
