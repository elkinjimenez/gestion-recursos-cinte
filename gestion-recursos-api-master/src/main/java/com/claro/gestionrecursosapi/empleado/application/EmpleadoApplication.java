package com.claro.gestionrecursosapi.empleado.application;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import com.claro.gestionrecursosapi.empleado.enums.PresupuestoEnum;
import com.claro.gestionrecursosapi.empleado.repository.IEmpleadoRepository;
import com.claro.gestionrecursosapi.excepcion.BusinessException;
import com.claro.gestionrecursosapi.excepcion.DataNotFoundException;
import com.claro.gestionrecursosapi.perfil.entity.PerfilEntity;
import com.claro.gestionrecursosapi.perfil.repository.IPerfilRepository;
import com.claro.gestionrecursosapi.presupuesto.entity.PresupuestoEntity;
import com.claro.gestionrecursosapi.presupuesto.repository.IPresupuestoRepository;
import com.claro.gestionrecursosapi.proyecto.entity.ProyectoEntity;
import com.claro.gestionrecursosapi.proyecto.repository.IProyectoRepository;
import com.claro.gestionrecursosapi.seguridad.entity.UsuarioRolEntity;
import com.claro.gestionrecursosapi.seguridad.entity.UsuarioRolesEntity;
import com.claro.gestionrecursosapi.seguridad.repository.IUsuarioRolesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.gestionrecursosapi.empleado.domain.EmpleadoControlService;
import com.claro.gestionrecursosapi.empleado.domain.EmpleadoService;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadoEntity;
import com.claro.gestionrecursosapi.empleado.entity.EmpleadocontrolEntity;
import com.claro.gestionrecursosapi.persona.domain.PersonaService;
import com.claro.gestionrecursosapi.persona.entity.PersonaEntity;
import com.claro.gestionrecursosapi.seguridad.domain.UsuarioRolEnum;
import com.claro.gestionrecursosapi.seguridad.domain.UsuarioService;
import com.claro.gestionrecursosapi.seguridad.entity.UsuarioEntity;
import com.claro.gestionrecursosapi.tarea.domain.TareaService;
import com.claro.gestionrecursosapi.tarea.entity.TareaEntity;
import com.google.common.hash.Hashing;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(TareaService.class);

	@Autowired
	private EmpleadoControlService serviceEmpleadoControl;
	@Autowired
	private TareaService serviceTarea;
	@Autowired
	private UsuarioService serviceUsuario;
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private PersonaService personaService;
	@Autowired
	private IUsuarioRolesRepository iUsuarioRolesRepository;

	@Transactional
	public EmpleadocontrolEntity saveControl(EmpleadocontrolEntity entity) throws BusinessException, DataNotFoundException {
		/*// Se registra el tiempo del empleado
		LOGGER.info("Entrando al metodo save de TareaService");
		BigDecimal horasPorValor;
		BigDecimal montoAcumuladoProyecto;
		BigDecimal presupuestoEnPesos;
		//Inicio busqueda perfil usuario
		Optional<EmpleadoEntity> oempl = iEmpleadoRepository.findById(entity.getCodempleado());
		if (!(oempl.isPresent())) {
			throw new DataNotFoundException("Código de empleado no existe!!!");
		}
		Optional<PerfilEntity> operfil = iPerfilRepository.findById(oempl.get().getCodperfil());

		if (!(operfil.isPresent())) {
			throw new DataNotFoundException("Inconsistencia de datos!!!");
		}
		//Fin busqueda perfil usuario
		horasPorValor = calcularHorasPorValor(entity, operfil.get());
		LOGGER.info("horas por Valor: ".concat(horasPorValor.toString()));
		//Inicio busqueda de presupuesto
		Optional<TareaEntity> otarea = serviceTarea.findById(entity.getCodtarea());
		if (!(otarea.isPresent())) {
			throw new DataNotFoundException("La Tarea no existe");
		}

		Optional<ProyectoEntity> opro = iProyectoRepository.findById(otarea.get().getCodproyecto());
		if (!(opro.isPresent())) {
			throw new DataNotFoundException("Código de proyecto no existe!!!");
		}

		if (Objects.isNull(opro.get().getCodpresupuesto())) {
			throw new DataNotFoundException("Código de presupuesto está nulo");
		}

		Optional<PresupuestoEntity> opre = iPresupuestoRepository.findById(opro.get().getCodpresupuesto());
		if (!(opre.isPresent())) {
			throw new DataNotFoundException("Inconsistencia de datos!!!");
		}
		//Fin busqueda de presupuesto

		//Inicio validación de presupuesto

		if (Objects.isNull(opre.get().getPresupuesto_cop())) {
			throw new DataNotFoundException("El monto del presupuesto no está cargado");
		}

		montoAcumuladoProyecto = iProyectoRepository.calculoDeHorasQueLlevaUnProyecto(opro.get().getId());
		//LOGGER.info("montoAcumuladoProyecto.compareTo(horasPorValor): " + montoAcumuladoProyecto.compareTo(horasPorValor));
		//LOGGER.info("montoAcumuladoProyecto: " + montoAcumuladoProyecto.toString());
		//LOGGER.info("presupuesto: " + opre.get().getPresupuesto_ussd().multiply(BigDecimal.valueOf(4000)));
		//LOGGER.info("horasPorValor" + horasPorValor.toString());
		//presupuestoEnPesos = opre.get().getPresupuesto_ussd().multiply(BigDecimal.valueOf(4000));
		if (opre.get().getPresupuesto_cop().compareTo(montoAcumuladoProyecto.add(horasPorValor)) < BigDecimal.ZERO.intValue()) {
			//(1) excedió el presupuesto
			entity.setExcedido(PresupuestoEnum.EXCEDIDO.getId());
		} else {
			//(0) Aún son cubiertas por el presupuesto
			entity.setExcedido(PresupuestoEnum.NO_EXCEDIDO.getId());
		}*/
		/*Fin validación de presupuesto*/
		///////////////////////////////////////////////////////////////////////////
		entity = serviceEmpleadoControl.save(entity);
		// Se actualizan los datos de la tarea
		Optional<TareaEntity> tarea = serviceTarea.findById(entity.getCodtarea());
		if (tarea.isPresent()) {
			BigDecimal horasTotal = serviceEmpleadoControl.sumHorasByCodtarea(entity.getCodtarea());
			Date fechaIniMin = serviceEmpleadoControl.minFechaIniByCodtarea(entity.getCodtarea());
			Date fechaFinMax = serviceEmpleadoControl.maxFechaFinByCodtarea(entity.getCodtarea());
			
			tarea.get().setTiemporeal(horasTotal);
			tarea.get().setFechainireal(fechaIniMin);
			tarea.get().setFechafinreal(fechaFinMax);
			
			serviceTarea.save(tarea.get());
		}
		
		return entity;
	}

	@Transactional
	public EmpleadoEntity save(EmpleadoEntity entity) {
		Boolean bEsInsert = entity.getId() == null || entity.getId() <= 0;
		EmpleadoEntity empleadoActivo = empleadoService.findActivoByCodPersona(entity.getCodpersona());
		
		if(empleadoActivo != null) {
			throw new BusinessException("La persona indicada ya tiene una vinculación activa");
		}
		
		entity = empleadoService.save(entity);		
		
		if (!bEsInsert)
			return entity;
		
		Optional<PersonaEntity> persona = personaService.findById(entity.getCodpersona());
		// se reutiliza el usuario de la persona, en caso de que tenga
		UsuarioEntity usuarioEntity = serviceUsuario.findByCodPersona(entity.getCodpersona());
		UsuarioRolesEntity usuarioRol = null;
		
		if (usuarioEntity == null) {
			// Crear usuario
			// Se crea el usuario por defecto
			usuarioEntity = new UsuarioEntity();
		} else {
			usuarioRol = iUsuarioRolesRepository.findByIdUsuario(usuarioEntity.getId());
		}

		usuarioEntity.setUsuario(entity.getUsuarioclaro());
		
		if (persona.isPresent()) {
			usuarioEntity.setCodpersona(persona.get().getId());
			usuarioEntity.setNombre(String.valueOf(persona.get().getNombre1() + " " + persona.get().getApellido1()));
			usuarioEntity.setClave(encriptarTexto(persona.get().getNumerodocumento().toString()));
		}
		
		//usuarioEntity.setCodusuariorol(UsuarioRolEnum.USUARIO.getValue());
		usuarioEntity.setEstado("A");
		usuarioEntity.setCodusuariorol(entity.getRol());
		serviceUsuario.save(usuarioEntity);

		// actualiza o crea el usuario rol, según el caso
		if (usuarioRol != null) {
			usuarioRol.setUsuarioRolEntity(new UsuarioRolEntity(usuarioEntity.getCodusuariorol()));
			iUsuarioRolesRepository.save(usuarioRol);
		} else {
			iUsuarioRolesRepository.save(new UsuarioRolesEntity(new UsuarioEntity(usuarioEntity.getId()), new UsuarioRolEntity(usuarioEntity.getCodusuariorol())));
		}
		return entity;
	}
	
	private String encriptarTexto(String texto) {
		return Hashing.sha256().hashString(texto, StandardCharsets.UTF_8).toString();
	}


	private BigDecimal calcularHorasPorValor(EmpleadocontrolEntity entity, PerfilEntity perfil) {
		//Considerar configuración de perfil imcompleto si la multiplicación da cero
		BigDecimal montoPorHoraPerfil = Objects.isNull(perfil.getConstoPromedioHora()) ? BigDecimal.ZERO : new BigDecimal(perfil.getConstoPromedioHora());
		BigDecimal montoPorHorasReportadas = montoPorHoraPerfil.multiply(entity.getHoras());
		return montoPorHorasReportadas;
	}
	
}
