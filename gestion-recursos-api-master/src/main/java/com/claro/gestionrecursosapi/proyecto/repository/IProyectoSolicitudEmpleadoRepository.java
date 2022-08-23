package com.claro.gestionrecursosapi.proyecto.repository;


import org.springframework.data.repository.CrudRepository;

import com.claro.gestionrecursosapi.proyecto.entity.ProyectosolicitudempleadoEntity;

public interface IProyectoSolicitudEmpleadoRepository extends CrudRepository<ProyectosolicitudempleadoEntity, Integer> {
	
	Iterable<ProyectosolicitudempleadoEntity> findByCodlineaproducto(Integer codLineaProducto);
	
	Iterable<ProyectosolicitudempleadoEntity> findByCodperfil(Integer codPerfil);
	
	Iterable<ProyectosolicitudempleadoEntity> findByCodperfilnivel(Integer codPerfilNivel);
	
	Iterable<ProyectosolicitudempleadoEntity> findByCodperfiltipo(Integer codPerfilTipo);
	
	Iterable<ProyectosolicitudempleadoEntity> findByCodproyecto(Integer codProyecto);
	
}
