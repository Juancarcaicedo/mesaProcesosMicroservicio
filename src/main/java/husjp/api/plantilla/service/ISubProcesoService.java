package husjp.api.plantilla.service;

import java.util.List;

import husjp.api.plantilla.service.dto.SubProcesoDTO;

public interface ISubProcesoService {
	    List<SubProcesoDTO>obtenerSubprocesos();
	    List<SubProcesoDTO>obtenerSubprocesosporArea(Integer idproceso);
	    SubProcesoDTO crearSubProceso(SubProcesoDTO subProcesoDTO);
	    SubProcesoDTO actualizarSubproceso( Integer id, SubProcesoDTO subProcesoDTO );
}
