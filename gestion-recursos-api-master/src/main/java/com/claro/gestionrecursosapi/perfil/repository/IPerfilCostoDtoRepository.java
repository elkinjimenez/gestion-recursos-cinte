package com.claro.gestionrecursosapi.perfil.repository;

import com.claro.gestionrecursosapi.perfil.dto.PerfilCostoDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPerfilCostoDtoRepository extends CrudRepository<PerfilCostoDto, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT pc.id, pr.id proveedorid, pr.nombre proveedornombre, p.id perfilid, p.nombre perfilnombre, \n" +
                    "pn.id perfilnivelid, pn.nombre perfilnivelnombre, pt.id perfiltipoid, pt.nombre perfiltiponombre, \n" +
                    "pc.fechacreacion, pc.fechamodificacion, pc.estado, pc.valor \n" +
                    "FROM df_perfilcosto pc \n" +
                    "inner join df_proveedor pr on pc.codproveedor = pr.id \n" +
                    "inner join df_perfilnivel pn on pc.codperfilnivel = pn.id \n" +
                    "inner join df_perfiltipo pt on pc.codperfiltipo = pt.id \n" +
                    "inner join df_perfil p on pc.codperfil = p.id ")
    public List<PerfilCostoDto> findAllPerfilCosto();

    @Query(nativeQuery = true,
            value = "SELECT pc.id, pr.id proveedorid, pr.nombre proveedornombre, p.id perfilid, p.nombre perfilnombre, \n" +
                    "pn.id perfilnivelid, pn.nombre perfilnivelnombre, pt.id perfiltipoid, pt.nombre perfiltiponombre, \n" +
                    "pc.fechacreacion, pc.fechamodificacion, pc.estado, pc.valor \n" +
                    "FROM df_perfilcosto pc \n" +
                    "inner join df_proveedor pr on pc.codproveedor = pr.id \n" +
                    "inner join df_perfilnivel pn on pc.codperfilnivel = pn.id \n" +
                    "inner join df_perfiltipo pt on pc.codperfiltipo = pt.id \n" +
                    "inner join df_perfil p on pc.codperfil = p.id \n" +
                    "WHERE pc.id = ?")
    public PerfilCostoDto findByIdPerfilCosto(Integer id);

}
