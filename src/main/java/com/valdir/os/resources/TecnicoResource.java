package com.valdir.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.valdir.os.domain.dtos.TecnicoDTO;
import com.valdir.os.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

	@Autowired
	private TecnicoService service;
 
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(new TecnicoDTO(service.findById(id)));
	}

	@GetMapping()
	public ResponseEntity<List<TecnicoDTO>> findAll() {
		List<TecnicoDTO> list = service.findAll().stream().map(x -> new TecnicoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping()
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO obj) {
		obj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@Valid @RequestBody TecnicoDTO obj, @PathVariable Integer id) {
		obj = new TecnicoDTO(service.update(obj, id));
		return ResponseEntity.ok().build();
	}

}

















