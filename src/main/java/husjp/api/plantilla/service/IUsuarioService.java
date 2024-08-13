package husjp.api.plantilla.service;

import husjp.api.plantilla.service.dto.UsuarioDTO;

public interface IUsuarioService {
	 	
	UsuarioDTO buscarPorDocumento(String documento);

}
