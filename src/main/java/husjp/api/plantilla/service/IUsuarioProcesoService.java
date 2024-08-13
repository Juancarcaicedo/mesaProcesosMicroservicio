package husjp.api.plantilla.service;
import java.time.LocalDateTime;
import java.util.List;

import husjp.api.plantilla.service.dto.UsuarioProcesoDTO;

public interface IUsuarioProcesoService {
	
	 List<UsuarioProcesoDTO> obtenerUsuariosprocesos();
	   List<UsuarioProcesoDTO> obtenerprocesosPorUsuario(String documento);
	   UsuarioProcesoDTO crearUsuarioProceso(UsuarioProcesoDTO usuarioProcesoDTO);
	   UsuarioProcesoDTO actualizarUsuarioProcesoFecha(Integer id, LocalDateTime nuevaFechaFin);
	   UsuarioProcesoDTO actualizarUsuarioprocesoEstado(Integer idsubproceso, String enlace);
	   void eliminarUsuarioProceso(Integer id); 
	   public List<UsuarioProcesoDTO> obtenerUsuarioProcesoArea(Integer idArea);

}
