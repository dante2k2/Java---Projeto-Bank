package Excecoes;

public class ContaInvalidaException extends Exception {

	public ContaInvalidaException() {
		super("Conta Inv�lida!");
	}
	
	public ContaInvalidaException(int numConta) {
		super("A conta "+numConta+" � inv�lida!");
	}
	
}
