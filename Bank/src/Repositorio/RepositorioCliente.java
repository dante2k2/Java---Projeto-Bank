package Repositorio;

import java.util.ArrayList;

import Cliente.Cliente;

public class RepositorioCliente {
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	
	public void cadastrarCliente(Cliente cliente) {
		clientes.add(cliente);
	}
	
	public void atualizaCliente(Cliente cliente) {
		for (Cliente c : clientes) {
			if (c.getCpf().equals(cliente.getCpf())) {
				c.setNome(cliente.getNome());
				c.setDataNasc(cliente.getDataNasc());
			}
		}
	}
	
	public String listarClientes() {
		String imprime = "";
		for (Cliente c: clientes) {
			imprime += c.toString();
		}
		return imprime;
	}
	
	public Cliente consultaCliente(String cpf) {
		for (Cliente c : clientes) {
			if (cpf.equals(c.getCpf())) {
				return c;
			}
		}
		return null;
	}

}
