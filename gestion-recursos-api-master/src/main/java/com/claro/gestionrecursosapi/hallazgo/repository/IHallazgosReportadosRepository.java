package com.claro.gestionrecursosapi.hallazgo.repository;

import com.claro.gestionrecursosapi.hallazgo.entity.HallazgosReportadosEntity;
import org.springframework.data.repository.CrudRepository;

public interface IHallazgosReportadosRepository extends CrudRepository<HallazgosReportadosEntity, Integer> {
	public HallazgosReportadosEntity findByIdHallazgo(Integer id);
}