package com.valdir.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdir.os.domain.Cliente;
import com.valdir.os.domain.OrdemDeServico;
import com.valdir.os.domain.Tecnico;
import com.valdir.os.domain.dtos.OrdemDeServicoDTO;
import com.valdir.os.domain.enuns.Status;
import com.valdir.os.repositories.ClienteRepository;
import com.valdir.os.repositories.OrdemDeServicoRepository;
import com.valdir.os.repositories.TecnicoRepository;
import com.valdir.os.services.exceptions.ObjectNotFoundException;

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

	/*
	 * FindById
	 */
	public OrdemDeServico findById(Integer id) {
		Optional<OrdemDeServico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + OrdemDeServico.class.getName()));
	}

	/*
	 * FindAll
	 */
	public List<OrdemDeServico> findAll() {
		return repository.findAll();
	}

	/*
	 * Create
	 */
	public @Valid OrdemDeServico create(@Valid OrdemDeServicoDTO obj) {
		return repository.save(fromDTO(obj));
	}

	/*
	 * Update
	 */
	public @Valid OrdemDeServico update(@Valid OrdemDeServicoDTO obj, Integer id) {
		OrdemDeServico oldObj = findById(id);
		oldObj = updateData(obj, oldObj);
		return repository.save(oldObj);
	}

	/*
	 * Atualiza dados da OS
	 */
	private OrdemDeServico updateData(@Valid OrdemDeServicoDTO obj, OrdemDeServico oldObj) {
		oldObj.setPrioridade(obj.getPrioridade());
		oldObj.setObservacoes(obj.getObservacoes());
		oldObj.setTecnico(tecnicoService.findById(obj.getTecnico()));
		oldObj.setStatus(obj.getStatus());
		
		if (obj.getStatus().equals(Status.ENCERRADO)) {
			oldObj.setDataFechamento(LocalDateTime.now());
		}

		return oldObj;
	}

	/*
	 * Transforma um DTO em Model
	 */
	private OrdemDeServico fromDTO(OrdemDeServicoDTO obj) {
		OrdemDeServico newObj = new OrdemDeServico();
		newObj.setId(obj.getId());
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
