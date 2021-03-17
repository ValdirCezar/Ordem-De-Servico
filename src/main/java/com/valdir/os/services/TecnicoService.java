package com.valdir.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdir.os.domain.Tecnico;
import com.valdir.os.domain.dtos.TecnicoDTO;
import com.valdir.os.repositories.TecnicoRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElse(null);
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public TecnicoDTO create(TecnicoDTO obj) {
		Tecnico tecnico = new Tecnico(null, obj.getNome(), obj.getCpf());
		obj.getPerfis().forEach(x -> tecnico.addPerfil(x));
		return new TecnicoDTO(repository.save(tecnico));
	}
}
