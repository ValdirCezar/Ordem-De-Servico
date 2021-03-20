package com.valdir.os.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valdir.os.domain.Cliente;
import com.valdir.os.domain.OrdemDeServico;
import com.valdir.os.domain.Tecnico;
import com.valdir.os.domain.enuns.Perfil;
import com.valdir.os.repositories.ClienteRepository;
import com.valdir.os.repositories.OrdemDeServicoRepository;
import com.valdir.os.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OrdemDeServicoRepository osRepository;

	public void instanciaDB() throws ParseException {
		Tecnico t1 = new Tecnico(null, "Valdir Cezar", "561.096.320-66");
		t1.addPerfil(Perfil.ADMIN);
		Tecnico t2 = new Tecnico(null, "Matheus Henrique", "07854125698");

		Cliente c1 = new Cliente(null, "Albert Eistein", "09874125895", "43985459585");

		OrdemDeServico os1 = new OrdemDeServico(null, "Troca de placa mãe", t1, c1);

		t1.getOsList().add(os1);
		c1.getOsList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}
}
