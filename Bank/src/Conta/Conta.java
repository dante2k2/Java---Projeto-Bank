package Conta;

import Cliente.Cliente;

public class Conta {
	private static int contador = 1000;
	private double saldo;
	private Cliente titular;
	private int numConta;
	
	public Conta(Cliente titular, double valor) {
		this.titular = titular;
		this.saldo = valor;
		this.numConta = Conta.contador++;
	}
	
	public Conta(int numConta) {
		this.numConta = numConta;
	}
	
	public Conta() {}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Cliente getTitular() {
		return titular;
	}

	public void setTitular(Cliente titular) {
		this.titular = titular;
	}

	public int getNumConta() {
		return numConta;
	}

	public void setNumConta(int numConta) {
		this.numConta = numConta;
	}

	@Override
	public String toString() {
		return "Titular: "+getTitular()+"\nConta: "+getNumConta()+"\nSaldo: "+getSaldo()+"\n-------------------------------\n";
	}
	
}
