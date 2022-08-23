package com.claro.gestionrecursosapi.empleado.domain;

import com.claro.gestionrecursosapi.empleado.entity.EmpleadoEntity;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoEstructuraOrganizacionalVU;
import com.claro.gestionrecursosapi.empleado.repository.IEmpleadoEstructuraOrganizacionalRepository;
import com.claro.gestionrecursosapi.empleado.repository.IEmpleadoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoEstructuraOrganizacionalService {

	@Autowired
	private IEmpleadoEstructuraOrganizacionalRepository repository;

	@Autowired
	private IEmpleadoRepository empleadorepository;

	public EmpleadoEstructuraOrganizacionalVU getEmpleadoByActivosByEstructura(Integer codEstructura) {

		EmpleadoEntity empleado = null;
		List<EmpleadoEntity> listaempleados = empleadorepository.findByCodestructuraorganizacional(codEstructura);
		if(listaempleados.isEmpty()) {
			return null;
		}
		empleado = listaempleados.get(0);
		return repository.findByCodEmpleado(empleado.getId());

	}
}
