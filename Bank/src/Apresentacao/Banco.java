package Apresentacao;

import java.util.Scanner;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.JOptionPane;

import Cliente.Cliente;
import Excecoes.ClienteInvalidoException;
import Excecoes.ClienteJaCadastradoException;

public class Banco {
	
	public static void main(String[] args) {
		FachadaBanco fachada = new FachadaBanco();
		String nome, cpf, dataNasc, opcao = "";
		Scanner scanf = new Scanner(System.in);
		Cliente titular;
		Double valor;
		Integer numConta;
		
		while (!opcao.equalsIgnoreCase("13")) {
			
			try {
				
				opcao = JOptionPane.showInputDialog(null, " "
								 + "1\t- Cadastrar Cliente\n "
								 + "2\t- Listar Clientes\n "
							 	 + "3\t- Atualizar Dados de Clientes\n "
							 	 + "4\t- Listar Contas\n "
							 	 + "5\t- Abrir Conta Conrrente\n "
							 	 + "6\t- Abrir Conta Poupança\n "
							 	 + "7\t- Abrir Conta Bonificada\n "
							 	 + "8\t- Consultar Saldo\n "
							 	 + "9\t- Realizar Saque\n "
							 	 + "10\t- Realizar Deposito\n "
							 	 + "11\t- Realizar Transferência\n "
							 	 + "12\t- Encerrar Conta\n "
							 	 + "13\t- Finalizar Sistema"
							 	 , "Menu", 3);
				
				if (opcao == null)
					opcao = "FinalizarPrograma";
				switch (opcao) {
				case "1":
					JOptionPane.showConfirmDialog(null, "Para cadastrar um cliente informe:\n"
												+ "-CPF\n-NOME\n-DATA DE NASCIMENTO", "Cadastro", JOptionPane.DEFAULT_OPTION);
					cpf = JOptionPane.showInputDialog(null, "Digite o CPF do Cliente", "Cadastro", JOptionPane.OK_CANCEL_OPTION);
					if (cpf == null || cpf.length() != 11) break;
					nome = JOptionPane.showInputDialog(null, "Digite o NOME do Cliente", "Cadastro", JOptionPane.OK_CANCEL_OPTION);
					if (nome == null || nome.length() < 4) break;
					dataNasc = JOptionPane.showInputDialog(null, "Digite a DATA DE NASCIMENTO do Cliente","Cadastro", JOptionPane.OK_CANCEL_OPTION);
					if (dataNasc == null || dataNasc.length() < 8) break;
					try {
						fachada.cadastrarCliente(cpf, nome, dataNasc);
						JOptionPane.showConfirmDialog(null, "Cliente Cadastrado!", "Cadastro", JOptionPane.DEFAULT_OPTION);
					} catch (ClienteJaCadastradoException e) {
						JOptionPane.showConfirmDialog(null, e.getMessage(),"Falha!", JOptionPane.DEFAULT_OPTION);
					}
					cpf = null; nome = null; dataNasc = null;
					break;
				case "2":
					JOptionPane.showConfirmDialog(null, fachada.listarClientes(),"Clientes", JOptionPane.DEFAULT_OPTION);
					break;
				case "3":
					JOptionPane.showConfirmDialog(null, "Para atualizar os dados de um cliente informe:\n"
							+ "-CPF\n-NOME\n-DATA DE NASCIMENTO", "Atualização", JOptionPane.DEFAULT_OPTION);
					cpf = JOptionPane.showInputDialog(null, "Digite o CPF do Cliente", "Atualização", JOptionPane.OK_CANCEL_OPTION);
					if (cpf == null || cpf.length() != 11) break;
					nome = JOptionPane.showInputDialog(null, "Digite o NOME do Cliente", "Atualização", JOptionPane.OK_CANCEL_OPTION);
					if (nome == null || nome.length() < 4) break;
					dataNasc = JOptionPane.showInputDialog(null, "Digite a DATA DE NASCIMENTO do Cliente", "Atualização", JOptionPane.OK_CANCEL_OPTION);
					if (dataNasc == null || dataNasc.length() < 8) break;
					try {
						fachada.atualizaCliente(cpf, nome, dataNasc);
						JOptionPane.showConfirmDialog(null, "Cliente Atualizado!", "Sucesso!", JOptionPane.DEFAULT_OPTION);
					} catch (ClienteInvalidoException e) {
						JOptionPane.showConfirmDialog(null, e.getMessage(), "Falha!", JOptionPane.DEFAULT_OPTION);
					}
					cpf = null; nome = null; dataNasc = null;
					break;
				case "4":
					JOptionPane.showConfirmDialog(null, fachada.listarContas(),"Contas", JOptionPane.DEFAULT_OPTION);
					break;
				case "5":
					JOptionPane.showConfirmDialog(null, "Para abrir uma Conta Corrente informe:\n"
							+ "-CPF DO TITULAR\n-VALOR DE DEPÓSITO", "Conta Corrente", JOptionPane.DEFAULT_OPTION);
					cpf = JOptionPane.showInputDialog(null, "Digite o CPF do cliente", "Conta Corrente", JOptionPane.OK_CANCEL_OPTION);
					if (cpf == null || cpf.length() != 11) break;
					valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor da conta", "Conta Corrente", JOptionPane.DEFAULT_OPTION));
					titular = fachada.consultaCliente(cpf);
					fachada.inserirConta(titular, valor);
					cpf = null; valor = null; titular = null;
					break;
				case "6":
					JOptionPane.showConfirmDialog(null, "Para abrir uma Conta Poupança informe:\n"
							+ "-CPF DO TITULAR\n-VALOR DE DEPÓSITO (Min R$100)", "Conta Poupança", JOptionPane.DEFAULT_OPTION);
					cpf = JOptionPane.showInputDialog(null, "Digite o CPF do cliente", "Conta Poupança", JOptionPane.OK_CANCEL_OPTION);
					if (cpf == null || cpf.length() != 11) break;
					valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor da conta", "Conta Poupança", JOptionPane.DEFAULT_OPTION));
					titular = fachada.consultaCliente(cpf);
					fachada.inserirContaP(titular, valor);
					cpf = null; valor = null; titular = null;
					break;
				case "7":
					JOptionPane.showConfirmDialog(null, "Para abrir uma Conta Bonificada informe:\n"
							+ "-CPF DO TITULAR\n-VALOR DE DEPÓSITO (Min R$5)", "Conta Bonificada", JOptionPane.DEFAULT_OPTION);
					cpf = JOptionPane.showInputDialog(null, "Digite o CPF do cliente", "Conta Bonificada", JOptionPane.OK_CANCEL_OPTION);
					if (cpf == null || cpf.length() != 11) break;
					valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor da conta", "Conta Bonificada", JOptionPane.DEFAULT_OPTION));
					titular = fachada.consultaCliente(cpf);
					fachada.inserirContaB(titular, valor);
					cpf = null; valor = null; titular = null;
					break;
				case "8":
					numConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da conta", "Consultar Saldo", JOptionPane.DEFAULT_OPTION));
					fachada.consultarSaldo(numConta);
					numConta = null;
					break;
				case "9":
					numConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da conta", "Realizar Saque", JOptionPane.DEFAULT_OPTION));
					valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor do saque", "Realizar Saque", JOptionPane.DEFAULT_OPTION));
					fachada.realizarSaque(numConta, valor);
					numConta = null; valor = null;
					break;
				case "10":
					numConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da conta", "Realizar Depósito", JOptionPane.DEFAULT_OPTION));
					valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor do deposito", "Realizar Depósito", JOptionPane.DEFAULT_OPTION));
					fachada.realizarDeposito(numConta, valor);
					numConta = null; valor = null;
					break;
				case "11":
					numConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da sua conta", "Tranferência", JOptionPane.DEFAULT_OPTION));
					valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor da transferência", "Transferência", JOptionPane.DEFAULT_OPTION));
					int numConta2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o número da conta de destino", "Transferência", JOptionPane.DEFAULT_OPTION));
					fachada.realizarTransferencia(numConta, numConta2, valor);
					numConta = null; valor = null;
					break;
				case "12":
					String cancelamento = JOptionPane.showInputDialog(null, "Digite o número da sua conta", "Cancelamento", JOptionPane.OK_CANCEL_OPTION);
					if (cancelamento == null) break;
					fachada.finalizarConta(Integer.parseInt(cancelamento));
					cancelamento = null;
					break;
				case "13":
					break;
				case "FinalizarPrograma":
					int fim = JOptionPane.showConfirmDialog(null, "Deseja finalizar o programa?", "Encerrar Aplicação", JOptionPane.YES_NO_OPTION);
					if (fim == JOptionPane.YES_OPTION)
						System.exit(0);
					break;
				default:
					break;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
