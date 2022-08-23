package com.claro.gestionrecursosapi.persona.domain;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosapi.excepcion.DataIncorrectaExcepcion;
import com.claro.gestionrecursosapi.excepcion.NoExisteExcepcion;
import com.claro.gestionrecursosapi.excepcion.YaExisteExcepcion;
import com.claro.gestionrecursosapi.persona.entity.AcademicanivelEntity;
import com.claro.gestionrecursosapi.persona.repository.IAcademicaNivelRepository;

@Service
@Transactional
public class AcademicaNivelService implements IAcademicaNivelService {

	@Autowired
	private IAcademicaNivelRepository academicaNivelRepository;

	@Override
	public AcademicanivelEntity create(AcademicanivelEntity entity) throws YaExisteExcepcion, DataIncorrectaExcepcion {
		if (academicaNivelRepository.findById(entity.getId()).isPresent()) {
			throw new YaExisteExcepcion("Ya existe nivel Academica");
		} else {
			return academicaNivelRepository.save(entity);
		}
	}

	@Override
	public AcademicanivelEntity update(int id, AcademicanivelEntity entity)
			throws NoExisteExcepcion, DataIncorrectaExcepcion {

		AcademicanivelEntity findAcademicaNivelEntity = findById(id);

		findAcademicaNivelEntity.setNombre(entity.getNombre());
		findAcademicaNivelEntity.setJerarquia(entity.getJerarquia());
		findAcademicaNivelEntity.setFechacreacion(entity.getFechacreacion());
		findAcademicaNivelEntity.setFechamodificacion(entity.getFechamodificacion());

		return academicaNivelRepository.save(findAcademicaNivelEntity);
	}

	@Override
	public boolean delete(int id) throws NoExisteExcepcion {
		AcademicanivelEntity academicaNivelEntity = academicaNivelRepository.findById(id).orElse(null);

		if (academicaNivelEntity == null) {
			throw new NoExisteExcepcion("No existe nivel Academico");
		} else {
			academicaNivelRepository.delete(academicaNivelEntity);
			return true;
		}
	}

	@Override
	public AcademicanivelEntity findById(int id) throws NoExisteExcepcion {
		AcademicanivelEntity findAcademicaNivelEntity = academicaNivelRepository.findById(id).orElse(null);
		if (findAcademicaNivelEntity != null) {
			return findAcademicaNivelEntity;
		} else {
			throw new NoExisteExcepcion("No existe Academica");
		}

	}

	@Override
	public Iterable<AcademicanivelEntity> findByNombre(String nombre) throws NoExisteExcepcion {
		Iterable<AcademicanivelEntity> lista = academicaNivelRepository.findByNombre(nombre);

		if (lista == null) {
			throw new NoExisteExcepcion("No hay registros para el nombre");
		} else {
			return lista;
		}
	}

	@Override
	public Iterable<AcademicanivelEntity> findByJerarquia(int jerarquia) throws NoExisteExcepcion {
		Iterable<AcademicanivelEntity> lista = academicaNivelRepository.findByJerarquia(jerarquia);

		if (lista == null) {
			throw new NoExisteExcepcion("No hay registros para la jerarquia");
		} else {
			return lista;
		}
	}

	@Override
	public Iterable<AcademicanivelEntity> findAll() throws NoExisteExcepcion {
		Iterable<AcademicanivelEntity> lista = academicaNivelRepository.findAll();

		if (lista == null) {
			throw new NoExisteExcepcion("No hay registros de nivel academico");
		} else {
			return lista;
		}
	}

}
