package Apresentacao;

import javax.swing.JOptionPane;

import Cliente.Cliente;
import Conta.Conta;
import Conta.ContaBonificada;
import Conta.ContaPoupanca;
import Excecoes.ClienteInvalidoException;
import Excecoes.ClienteJaCadastradoException;
import Excecoes.ContaInvalidaException;
import Excecoes.ContaJaCadastradaException;
import Excecoes.SaldoInsuficienteException;
import Excecoes.ValorInvalidoException;
import Repositorio.RepositorioCliente;
import Repositorio.RepositorioConta;

public class FachadaBanco {
	RepositorioCliente repCliente;
	RepositorioConta repConta;
	Cliente cliente;
	Conta conta, contaP, contaB;
	
	public FachadaBanco() {
		repCliente = new RepositorioCliente();
		repConta = new RepositorioConta();
	}
	
	public void cadastrarCliente(String cpf, String nome, String dataNasc) 
	throws ClienteJaCadastradoException {
		if (repCliente.consultaCliente(cpf) == null) {
			cliente = new Cliente(cpf, nome, dataNasc);
			repCliente.cadastrarCliente(cliente);
		} else {
			throw new ClienteJaCadastradoException();
		}
	}
	
	public void atualizaCliente(String cpf, String nome, String dataNasc)
	throws ClienteInvalidoException {
		if (repCliente.consultaCliente(cpf) != null) {
			repCliente.atualizaCliente(new Cliente(cpf, nome, dataNasc));
		} else {
			throw new ClienteInvalidoException();
		}
	}
	
	public String listarClientes() {
		return repCliente.listarClientes();
	}
	
	public Cliente consultaCliente(String cpf) {
		return repCliente.consultaCliente(cpf);
	}
	
	public String listarContas() {
		return repConta.listarContas();
	}
	
	public Conta consultaConta(String cpf) {
		return repConta.consultaConta(cpf);
	}
	
	public void inserirConta(Cliente titular, double valor)
	throws ClienteInvalidoException, ContaJaCadastradaException, ValorInvalidoException {
		
		if (titular.getCpf() != null) {
			if (repConta.consultaConta(titular.getCpf()) == null) {
				if (valor > 0) {
					conta = new Conta(titular, valor);
					repConta.inserirConta(conta);
					JOptionPane.showConfirmDialog(null, "Conta criada com sucesso!", "Conta Corrente", JOptionPane.DEFAULT_OPTION);
				} else {
					JOptionPane.showConfirmDialog(null, new ValorInvalidoException().getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
					throw new ValorInvalidoException();
				}
			} else {
				JOptionPane.showConfirmDialog(null, new ContaJaCadastradaException().getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
				throw new ContaJaCadastradaException();
			}
		} else {
			JOptionPane.showConfirmDialog(null, new ClienteInvalidoException().getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
			throw new ClienteInvalidoException();
		}
		
	}
	
	public void inserirContaP(Cliente titular, double valor)
	throws ClienteInvalidoException, ContaJaCadastradaException, ValorInvalidoException {
			
		if (titular.getCpf() != null) {
			if (repConta.consultaConta(titular.getCpf()) == null) {
				if (valor >= 100) {
					contaP = new ContaPoupanca(titular, valor);
					repConta.inserirConta(contaP);
					JOptionPane.showConfirmDialog(null, "Conta criada com sucesso!", "Conta Poupança", JOptionPane.DEFAULT_OPTION);
				} else {
					JOptionPane.showConfirmDialog(null, new ValorInvalidoException().getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
					throw new ValorInvalidoException();
				}
			} else {
				JOptionPane.showConfirmDialog(null, new ContaJaCadastradaException().getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
				throw new ContaJaCadastradaException();
			}
		} else {
			JOptionPane.showConfirmDialog(null, new ClienteInvalidoException().getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
			throw new ClienteInvalidoException();
		}
			
	}
	
	public void inserirContaB(Cliente titular, double valor)
	throws ClienteInvalidoException, ContaJaCadastradaException, ValorInvalidoException {

		if (titular.getCpf() != null) {
			if (repConta.consultaConta(titular.getCpf()) == null) {
				if (valor >= 5) {
					contaB = new ContaBonificada(titular, valor);
					repConta.inserirConta(contaB);
					JOptionPane.showConfirmDialog(null, "Conta criada com sucesso!", "Conta Bonificada", JOptionPane.DEFAULT_OPTION);
				} else {
					JOptionPane.showConfirmDialog(null, new ValorInvalidoException().getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
					throw new ValorInvalidoException();
				}
			} else {
				JOptionPane.showConfirmDialog(null, new ContaJaCadastradaException().getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
				throw new ContaJaCadastradaException(); 
			}
		} else {
			JOptionPane.showConfirmDialog(null, new ClienteInvalidoException().getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
			throw new ClienteInvalidoException();
		}
			
	}
	
	public void consultarSaldo(int numconta)
	throws ContaInvalidaException {
		if (repConta.consultaConta(numconta) != null){
			double saldo = repConta.consultaConta(numconta).getSaldo();
			JOptionPane.showConfirmDialog(null, "Saldo: "+saldo,"Saldo", JOptionPane.DEFAULT_OPTION);
		}
		else{
			JOptionPane.showConfirmDialog(null, new ContaInvalidaException().getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
			throw new ContaInvalidaException();
		}
	}
	
	public void realizarSaque(int numConta, double valor)
	throws ContaInvalidaException, ValorInvalidoException, SaldoInsuficienteException {
		if (repConta.consultaConta(numConta) != null) {
			if (valor > 0) {
				if (repConta.realizarSaque(numConta, valor)) {
					JOptionPane.showConfirmDialog(null, "Saque realizado com sucesso!","Sucesso!", JOptionPane.DEFAULT_OPTION);
				} else {
					JOptionPane.showConfirmDialog(null, new SaldoInsuficienteException().getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
					throw new SaldoInsuficienteException();
				}
			} else {
				JOptionPane.showConfirmDialog(null, new ValorInvalidoException().getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
				throw new ValorInvalidoException();
			}
		} else {
			JOptionPane.showConfirmDialog(null, new ContaInvalidaException().getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
			throw new ContaInvalidaException();
		}
	}
	
	public void realizarDeposito (int numConta, double valor)
	throws ContaInvalidaException, ValorInvalidoException {
		if (repConta.consultaConta(numConta) != null) {
			if (valor > 0) {
				if (repConta.realizarDeposito(numConta, valor)) {
					JOptionPane.showConfirmDialog(null, "Depósito realizado com sucesso!","Sucesso!", JOptionPane.DEFAULT_OPTION);
				} else
					JOptionPane.showConfirmDialog(null, "Falha no depósito!","Falha!", JOptionPane.DEFAULT_OPTION);
			} else {
				JOptionPane.showConfirmDialog(null, new ValorInvalidoException().getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
				throw new ValorInvalidoException();
			}
		} else {
			JOptionPane.showConfirmDialog(null, new ContaInvalidaException().getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
			throw new ContaInvalidaException();
		}
	}
	
	public void realizarTransferencia(int numConta1, int numConta2, double valor)
	throws ContaInvalidaException, SaldoInsuficienteException, ValorInvalidoException {
		if (repConta.consultaConta(numConta1) != null)  {
			if (repConta.consultaConta(numConta2) != null) {
				if (valor > 0) {
					if (repConta.realizarSaque(numConta1, valor)) {
						repConta.realizarDeposito(numConta2, valor);
						JOptionPane.showConfirmDialog(null, "Tranferência realizada com sucesso!","Sucesso!", JOptionPane.DEFAULT_OPTION);
					} else {
						JOptionPane.showConfirmDialog(null, new SaldoInsuficienteException().getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
						throw new SaldoInsuficienteException();
					}
				} else {
					JOptionPane.showConfirmDialog(null, new ValorInvalidoException().getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
					throw new ValorInvalidoException();
				}
			} else {
				JOptionPane.showConfirmDialog(null, new ContaInvalidaException(numConta2).getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
				throw new ContaInvalidaException(numConta2);
			}
		} else {
			JOptionPane.showConfirmDialog(null, new ContaInvalidaException(numConta1).getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
			throw new ContaInvalidaException(numConta1);
		}
	}
	
	public void finalizarConta (int numConta)
	throws ContaInvalidaException {
		if (repConta.consultaConta(numConta) != null) {
			int confirma = JOptionPane.showConfirmDialog(null, "Você realmente deseja encerrar a conta?", "Atenção!", JOptionPane.YES_NO_OPTION);
			if (confirma == JOptionPane.YES_OPTION) {
				repConta.removerConta(repConta.consultaConta(numConta));
				JOptionPane.showConfirmDialog(null, "Conta Encerrada!","Sucesso!", JOptionPane.DEFAULT_OPTION);
			} else {
				JOptionPane.showConfirmDialog(null, "Operação Cancelada!","Falha!", JOptionPane.DEFAULT_OPTION);
			}
		} else {
			JOptionPane.showConfirmDialog(null, new ContaInvalidaException().getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
			throw new ContaInvalidaException();
		}
	}
	
}
