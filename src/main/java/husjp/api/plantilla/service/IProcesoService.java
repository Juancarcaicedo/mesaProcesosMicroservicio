package husjp.api.plantilla.service;

import java.util.List;

import husjp.api.plantilla.service.dto.ProcesoDTO;

public interface IProcesoService {
	
	List<ProcesoDTO> obtenerProcesos();
	List<ProcesoDTO>obtenerProcesosArea(Integer id);
    ProcesoDTO crearProceso(ProcesoDTO procesoDTO);
    ProcesoDTO actualizarProceso(Integer id, ProcesoDTO procesoDTO);
}
