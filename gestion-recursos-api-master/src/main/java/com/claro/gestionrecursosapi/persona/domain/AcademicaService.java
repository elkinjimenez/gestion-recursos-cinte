package com.claro.gestionrecursosapi.persona.domain;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosapi.excepcion.DataIncorrectaExcepcion;
import com.claro.gestionrecursosapi.excepcion.NoExisteExcepcion;
import com.claro.gestionrecursosapi.excepcion.YaExisteExcepcion;
import com.claro.gestionrecursosapi.persona.entity.AcademicaEntity;
import com.claro.gestionrecursosapi.persona.repository.IAcademicaRepository;

@Service
@Transactional
public class AcademicaService implements IAcademicaService {

	@Autowired
	private IAcademicaRepository academicaRepository;

	@Override
	public AcademicaEntity create(AcademicaEntity entity) throws YaExisteExcepcion, DataIncorrectaExcepcion {
		if (academicaRepository.findById(entity.getId()).isPresent()) {
			throw new YaExisteExcepcion("Ya existe Academica");
		} else {
			return academicaRepository.save(entity);
		}
	}

	@Override
	public AcademicaEntity update(int id, AcademicaEntity entity) throws NoExisteExcepcion, DataIncorrectaExcepcion {
		AcademicaEntity findAcademicaEntity = findById(id);

		findAcademicaEntity.setTitulo(entity.getTitulo());
		findAcademicaEntity.setInstitucion(entity.getInstitucion());
		findAcademicaEntity.setFechainicio(entity.getFechainicio());
		findAcademicaEntity.setFechafin(entity.getFechafin());
		findAcademicaEntity.setFechacreacion(entity.getFechacreacion());
		findAcademicaEntity.setFechamodificacion(entity.getFechamodificacion());
		findAcademicaEntity.setCodAcademicanivel(entity.getCodAcademicanivel());
		findAcademicaEntity.setCodPersona(entity.getCodPersona());

		return academicaRepository.save(findAcademicaEntity);
	}

	@Override
	public boolean delete(int id) throws NoExisteExcepcion {
		AcademicaEntity academicaEntity = academicaRepository.findById(id).orElse(null);

		if (academicaEntity == null) {
			throw new NoExisteExcepcion("No existe Academica");
		} else {
			academicaRepository.delete(academicaEntity);
			return true;
		}
	}

	@Override
	public Iterable<AcademicaEntity> findByCodpersona(Integer codPersona) throws NoExisteExcepcion {
		Iterable<AcademicaEntity> lista = academicaRepository.findByCodpersona(codPersona);

		if (lista == null) {
			throw new NoExisteExcepcion("No hay registros de academica");
		} else {
			return lista;
		}
	}

	@Override
	public Iterable<AcademicaEntity> findByTitulo(String titulo) throws NoExisteExcepcion {
		Iterable<AcademicaEntity> lista = academicaRepository.findByTitulo(titulo);

		if (lista == null) {
			throw new NoExisteExcepcion("No hay registros para el titulo");
		} else {
			return lista;
		}
	}

	@Override
	public Iterable<AcademicaEntity> findByInstitucion(String institucion) throws NoExisteExcepcion {
		Iterable<AcademicaEntity> lista = academicaRepository.findByInstitucion(institucion);

		if (lista == null) {
			throw new NoExisteExcepcion("No hay registros para la institución");
		} else {
			return lista;
		}
	}

	@Override
	public Iterable<AcademicaEntity> findAll() throws NoExisteExcepcion {
		Iterable<AcademicaEntity> lista = academicaRepository.findAll();

		if (lista == null) {
			throw new NoExisteExcepcion("No hay registros para academica");
		} else {
			return lista;
		}
	}

	@Override
	public AcademicaEntity findById(int id) throws NoExisteExcepcion {
		AcademicaEntity findAcademicaEntity = academicaRepository.findById(id).orElse(null);
		if (findAcademicaEntity != null) {
			return findAcademicaEntity;
		} else {
			throw new NoExisteExcepcion("No existe Academica");
		}
	}

}
