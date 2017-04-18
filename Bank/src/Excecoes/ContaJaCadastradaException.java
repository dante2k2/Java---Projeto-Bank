package Excecoes;

public class ContaJaCadastradaException extends Exception {
	
	public ContaJaCadastradaException() {
		super("Cliente já possui uma conta!");
	}
	
}
