package husjp.api.plantilla.controllers;

import java.util.List;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import husjp.api.plantilla.service.IProcesoService;
import husjp.api.plantilla.service.dto.ProcesoDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/procesos")
@CrossOrigin(origins = {"http://localhost:5173","http://optimus:5173"})
public class ProcesoController {
	
	private IProcesoService procesoService;
	
	@SecurityRequirement(name = "Bearer Authentication")//PERMITE QUE SE PUEDA EJECUTAR DESDE SWAGGER
    @GetMapping
        public List<ProcesoDTO> listarProcesos(){
            return procesoService.obtenerProcesos();
        }

 @SecurityRequirement(name = "Bearer Authentication")//PERMITE QUE SE PUEDA EJECUTAR DESDE SWAGGER
    @GetMapping("/{idarea}")
        public List<ProcesoDTO> listarProcesosporArea(@PathVariable(name = "idarea") Integer idarea){
            return procesoService.obtenerProcesosArea(idarea);
        }

   @SecurityRequirement(name = "Bearer Authentication")//PERMITE QUE SE PUEDA EJECUTAR DESDE SWAGGER  
    @PostMapping
    public ProcesoDTO crearProceso(@RequestBody ProcesoDTO procesoDTO) {
        return procesoService.crearProceso(procesoDTO);
    }

     @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{idproceso}")
    public ProcesoDTO actualizarProceso(@PathVariable(name = "idproceso") Integer idproceso, @RequestBody ProcesoDTO procesoDTO) {
        return procesoService.actualizarProceso(idproceso, procesoDTO);
    }

	
}
