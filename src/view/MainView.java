package view;

import java.util.Scanner;

public class MainView {
	final int size = 70; // Digite um numero par
	final int mid = size/2;
	Scanner scanner;
	
	public void header() {
		String logo = "SiGest - Sistema de Gerenciamento Estudantil";
		border();
		for(int i = 0; i <= size; i++) {
			if(i == 0) {
				System.out.print("|");
			} else if (i == mid-(logo.length()/2) ){
				System.out.print(logo);
				i += logo.length()-1;
			} else if(i == size){
				System.out.println("|");
			}	else {
				System.out.print(" ");
			}
		}
	}
	
	public String inputString(String output) {
		String input;
		scanner = new Scanner(System.in);
		System.out.print(output);
		input = scanner.nextLine();
		return input;
	}
	
	public int inputOpcao() {
		int input;
		scanner = new Scanner(System.in);
		String opcao = "Sua opção: ";
		System.out.print(opcao);
		input = scanner.nextInt();
		return input;
	}
	
	public void text(String text) {
		int textSize = text.length();
		for(int i = 0; i <= size; i++) {
			if(i == 0) {
				System.out.print("|");
			} else if(i == size) {
				System.out.println("|");
			} else if(i == 2){
				System.out.print(text);
				i += textSize - 1;
			} else {
				System.out.print(" ");
			}
		}
	}
	
	public void textCenter(String text) {
		int textSize = text.length();
		int textMid = textSize/2;
		border();
		for(int i = 0; i <= size; i++) {
			if(i == 0) {
				System.out.print("|");
			} else if(i == size) {
				System.out.println("|");
			} else if(i == mid-(textMid)){
				System.out.print(text);
				i += textSize - 1;
			} else {
				System.out.print(" ");
			}
		}
	}
	
	public void textBox(String text) {
		border();
		text(text);
		border();
	}
	
	public void border() {
		for(int i = 0; i <= size; i++) {
			if(i == 0) {
				System.out.print("+");
			} else if(i == size){
				System.out.println("+");
			}
			else {
				System.out.print("-");
			}	
		}
	}
	
	public void borderln() {
		for(int i = 0; i <= size; i++) {
			if(i == 0) {
				System.out.print("+");
			} else if(i == size){
				System.out.println("+");
			}
			else {
				System.out.print("-");
			}	
		}
		System.out.println();
	}
}
