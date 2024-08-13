package husjp.api.plantilla.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import husjp.api.plantilla.service.IUsuarioProcesoService;
import husjp.api.plantilla.service.IUsuarioService;
import husjp.api.plantilla.service.dto.UsuarioProcesoDTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarioprocesos")
@CrossOrigin(origins = { "http://localhost:5173", "http://optimus:5173" })
public class UsuarioProcesosController {
	private IUsuarioProcesoService usuarioProcesoService;
	
	   @SecurityRequirement(name = "Bearer Authentication")
	    @GetMapping
	    public List<UsuarioProcesoDTO> listarSubprocesosUsuarios() {
	        return usuarioProcesoService.obtenerUsuariosprocesos();
	    }

	    @SecurityRequirement(name = "Bearer Authentication")
	    @GetMapping("/{usuarioid}")
	    public List<UsuarioProcesoDTO> listarUsurioProceso(@PathVariable(name = "usuarioid")  String usuarioid) {
	        return usuarioProcesoService.obtenerprocesosPorUsuario(usuarioid);
	    }

	    @SecurityRequirement(name = "Bearer Authentication")
	    @PostMapping
	    public UsuarioProcesoDTO crearUsuarioSubProceso(@RequestBody UsuarioProcesoDTO usuarioProcesoDTO) {
	        return usuarioProcesoService.crearUsuarioProceso(usuarioProcesoDTO);

	    }
	    @SecurityRequirement(name = "Bearer Authentication")
	    @PutMapping("/{id}")
	    public UsuarioProcesoDTO actualizarFechaFin(@PathVariable(name= "id") Integer id, @RequestBody LocalDateTime nuevaFechaFin) {
	        return usuarioProcesoService.actualizarUsuarioProcesoFecha(id, nuevaFechaFin);
	    }

	    @SecurityRequirement(name = "Bearer Authentication")
	    @PutMapping("/estado/{proceso}")
	    public UsuarioProcesoDTO actualizarestado(@PathVariable (name = "proceso") Integer proceso, String enlace ) {
	        return usuarioProcesoService.actualizarUsuarioprocesoEstado(proceso,enlace);
	    }

	    @SecurityRequirement(name = "Bearer Authentication")
	    @DeleteMapping("/{id}")
	    public void eliminarUsuarioProceso(@PathVariable (name= "id") Integer id) {
	        usuarioProcesoService.eliminarUsuarioProceso(id);
	    }
	    @SecurityRequirement(name = "Bearer Authentication")
	    @GetMapping("/area/{idArea}")
	    public List<UsuarioProcesoDTO> getUsuarioProcesosByArea(@PathVariable (name="idAR") Integer idArea) {
	        return usuarioProcesoService.obtenerUsuarioProcesoArea(idArea);
	    }

}
