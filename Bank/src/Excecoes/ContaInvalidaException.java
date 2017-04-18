package Excecoes;

public class ContaInvalidaException extends Exception {

	public ContaInvalidaException() {
		super("Conta Inválida!");
	}
	
	public ContaInvalidaException(int numConta) {
		super("A conta "+numConta+" é inválida!");
	}
	
}
