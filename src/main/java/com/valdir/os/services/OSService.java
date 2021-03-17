package com.valdir.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdir.os.domain.Cliente;
import com.valdir.os.domain.OrdemDeServico;
import com.valdir.os.domain.Tecnico;
import com.valdir.os.domain.dtos.OrdemDeServicoDTO;
import com.valdir.os.repositories.ClienteRepository;
import com.valdir.os.repositories.OrdemDeServicoRepository;
import com.valdir.os.repositories.TecnicoRepository;

@Service
public class OSService {

	@Autowired
	private OrdemDeServicoRepository repository;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public OrdemDeServico findById(Integer id) {
		Optional<OrdemDeServico> obj = repository.findById(id);
		return obj.orElse(null);
	}

	public List<OrdemDeServico> findAll() {
		return repository.findAll();
	}

	public OrdemDeServico create(OrdemDeServicoDTO obj) {
		return repository.save(fromDTO(obj));
	}

	private OrdemDeServico fromDTO(OrdemDeServicoDTO obj) {
		OrdemDeServico newObj = new OrdemDeServico();

		newObj.setId(obj.getId());
		newObj.setDataAbertura(obj.getDataAbertura());
		newObj.setDataFechamento(obj.getDataFechamento());
		newObj.setPrioridade(obj.getPrioridade());
		newObj.setObservacoes(obj.getObservacoes());

		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		tec.getOsList().add(newObj);
		tecnicoRepository.save(tec);

		Cliente cli = clienteService.findById(obj.getCliente());
		cli.getOsList().add(newObj);
		clienteRepository.save(cli);

		newObj.setTecnico(tec);
		newObj.setCliente(cli);

		return newObj;
	}
}
