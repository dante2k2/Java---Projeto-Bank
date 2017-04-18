package Repositorio;

import java.util.ArrayList;

import Cliente.Cliente;
import Conta.Conta;
import Conta.ContaBonificada;
import Conta.ContaPoupanca;

public class RepositorioConta {
	ArrayList<Conta> contas = new ArrayList<Conta>();
	
	public void inserirConta(Conta conta) {
		contas.add(conta);
	}
	
	public void removerConta (Conta conta) {
		contas.remove(conta);
	}
	
	public String listarContas() {
		String imprime = "";
		for (Conta c: contas) {
			imprime += c.toString();
		}
		return imprime;
	}
		
	public Conta consultaConta(String cpf) {
		for (Conta c : contas) {
			if (cpf.equals(c.getTitular().getCpf())) {
				return c;
			}
		}
		return null;
	}
	
	public Conta consultaConta(int numConta) {
		for (Conta c : contas) {
			if (numConta == c.getNumConta()) {
				return c;
			}
		}
		return null;
	}
	
	public boolean realizarSaque (int numConta, double valor) {
		for (Conta c : contas) {
			if (c.getNumConta() == numConta) {
				if (valor <= c.getSaldo()) {
					double saque = c.getSaldo() - valor;
					if (c instanceof ContaBonificada) {
						saque -= valor*0.002;
					}
					c.setSaldo(saque);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean realizarDeposito (int numConta, double valor) {
		for (Conta c : contas) {
			if (c.getNumConta() == numConta) {
				double saldo = c.getSaldo() + valor;
				if (c instanceof ContaPoupanca) {
					saldo += valor*0.005;
				}
				c.setSaldo(saldo);
				return true;
			}
		}
		return false;
	}

}
