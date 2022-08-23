package com.claro.gestionrecursosapi.perfil.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.claro.gestionrecursosapi.perfil.dto.PerfilCostoDto;
import com.claro.gestionrecursosapi.perfil.entity.PerfilcostoPK;
import com.claro.gestionrecursosapi.perfil.repository.IPerfilCostoDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claro.gestionrecursosapi.base.domain.ICrudService;
import com.claro.gestionrecursosapi.perfil.entity.PerfilcostoEntity;
import com.claro.gestionrecursosapi.perfil.repository.IPerfilCostoRepository;

@Service
@Transactional
public class PerfilCostoService implements ICrudService<PerfilcostoEntity, Integer> {

	@Autowired
	private IPerfilCostoRepository repository;

	@Autowired
	private IPerfilCostoDtoRepository iPerfilCostoDtoRepository;

	public PerfilCostoDto savePerfilCosto(PerfilCostoDto perfilCostoDto) {
		PerfilcostoEntity perfilcostoEntity = new PerfilcostoEntity();
		perfilcostoEntity.setCodproveedor(perfilCostoDto.getProveedorId());
		perfilcostoEntity.setCodperfil(perfilCostoDto.getPerfilId());
		perfilcostoEntity.setCodperfilnivel(perfilCostoDto.getPerfilNivelId());
		perfilcostoEntity.setCodperfiltipo(perfilCostoDto.getPerfilTipoId());
		perfilcostoEntity.setValor(perfilCostoDto.getValor());

		repository.save(perfilcostoEntity);
		perfilCostoDto.setId(perfilcostoEntity.getId());
		return perfilCostoDto;
	}

	public PerfilCostoDto updatePerfilCosto(PerfilCostoDto perfilCostoDto, Integer id) {
		Optional<PerfilcostoEntity> oPerfilBd =  repository.findById(id);
		if (oPerfilBd.isPresent()) {
			oPerfilBd.get().setCodproveedor(perfilCostoDto.getProveedorId());
			oPerfilBd.get().setCodperfilnivel(perfilCostoDto.getPerfilNivelId());
			oPerfilBd.get().setCodperfiltipo(perfilCostoDto.getPerfilTipoId());
			oPerfilBd.get().setCodperfil(perfilCostoDto.getPerfilId());
			oPerfilBd.get().setValor(perfilCostoDto.getValor());
			repository.save(oPerfilBd.get());
			return perfilCostoDto;
		} else {
			return null;
		}

	}

	@Override
	public PerfilcostoEntity save(PerfilcostoEntity entity) {
		return repository.save(entity);
	}

	@Override
	public Iterable<PerfilcostoEntity> saveAll(Iterable<PerfilcostoEntity> entities) {
		return repository.saveAll(entities);
	}

	@Override
	public Optional<PerfilcostoEntity> findById(Integer id) {
		return repository.findById(id);
	}

	public PerfilCostoDto findByIdPerfilCosto(Integer id) {
		return iPerfilCostoDtoRepository.findByIdPerfilCosto(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}

	public Iterable<PerfilcostoEntity> findByEstadoContains(String estado) {
		return repository.findByEstado(estado);
	}

	public Iterable<PerfilcostoEntity> findByCodProveedor(Integer codproveedor) {
		return repository.findByCodproveedor(codproveedor);
	}

	public Iterable<PerfilcostoEntity> findByPerfil(Integer perfil) {
		return repository.findByCodperfil(perfil);
	}

	public Iterable<PerfilcostoEntity> findByPerfilTipo(Integer perfilTipo) {
		return repository.findByCodperfiltipo(perfilTipo);
	}
	
	public Iterable<PerfilcostoEntity> findByPerfilNivel(Integer perfilNivel) {
		return repository.findByCodperfilnivel(perfilNivel);
	}

	@Override
	public Iterable<PerfilcostoEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);

	}

	@Override
	public void delete(PerfilcostoEntity entity) {
		repository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<PerfilcostoEntity> entities) {
		repository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	public List<PerfilCostoDto> findAllPerfilCosto() {
		return iPerfilCostoDtoRepository.findAllPerfilCosto();
	}

}
