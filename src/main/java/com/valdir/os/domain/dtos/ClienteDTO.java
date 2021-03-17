package com.valdir.os.domain.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.valdir.os.domain.Cliente;
import com.valdir.os.domain.OrdemDeServico;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String cpf;
	private String telefone;
	private List<OrdemDeServico> osList = new ArrayList<>();

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Cliente obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.telefone = obj.getTelefone();
		this.osList = obj.getOsList();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<OrdemDeServico> getOsList() {
		return osList;
	}

	public void setOsList(List<OrdemDeServico> osList) {
		this.osList = osList;
	}

}
