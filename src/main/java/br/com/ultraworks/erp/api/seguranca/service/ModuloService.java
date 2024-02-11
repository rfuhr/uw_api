package br.com.ultraworks.erp.api.seguranca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ultraworks.erp.api.seguranca.domain.modulo.Modulo;
import br.com.ultraworks.erp.api.seguranca.repository.ModuloRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModuloService {

    private final ModuloRepository moduloRepository;

    public List<Modulo> getAll() {
        return moduloRepository.findAll();
    }

	public Modulo save(Modulo entity) {
		moduloRepository.saveAndFlush(entity);
		return entity;
	}

	public Optional<Modulo> findById(long id) {
		return moduloRepository.findById(id);
	}

	public void deleteById(long id) {
		moduloRepository.deleteById(id);
	}


}
