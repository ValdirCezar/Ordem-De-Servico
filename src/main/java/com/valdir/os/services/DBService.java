package com.valdir.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdir.os.domain.Tecnico;
import com.valdir.os.domain.enuns.Perfil;
import com.valdir.os.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;

	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Valdir Cezar", "09874596325");
		t1.addPerfil(Perfil.ADMIN);

		Tecnico t2 = new Tecnico(null, "Matheus Henrique", "07854125698");
		
		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
	}
}
