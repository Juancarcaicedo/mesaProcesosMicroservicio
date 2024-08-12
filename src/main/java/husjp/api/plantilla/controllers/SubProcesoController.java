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

import husjp.api.plantilla.service.ISubProcesoService;
import husjp.api.plantilla.service.dto.SubProcesoDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;

@AllArgsConstructor
 @RestController
 @RequestMapping("/subprocesos")
 @CrossOrigin(origins = {"http://localhost:5173","http://optimus:5173"})
public class SubProcesoController {
    private ISubProcesoService subProcesoService;
    @SecurityRequirement(name = "Bearer Authentication")//PERMITE QUE SE PUEDA EJECUTAR DESDE SWAGGER
    @GetMapping
    public List<SubProcesoDTO> listarSubprocesos(){
        return subProcesoService.obtenerSubprocesos();
    }
    @SecurityRequirement(name = "Bearer Authentication")//PERMITE QUE SE PUEDA EJECUTAR DESDE SWAGGER
    @GetMapping("/{idProceso}")
    public List<SubProcesoDTO> listarSubprocesosPorArea(@PathVariable(name = "idProceso")   Integer idProceso){
        return subProcesoService.obtenerSubprocesosporArea(idProceso);
    }

    @SecurityRequirement(name = "Bearer Authentication")//PERMITE QUE SE PUEDA EJECUTAR DESDE SWAGGER  
    @PostMapping
    public SubProcesoDTO creaSubProcesoDTO(@RequestBody SubProcesoDTO subProcesoDTO){
        return subProcesoService.crearSubProceso(subProcesoDTO);
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{id}")
    public SubProcesoDTO actualizarSubProceso(@PathVariable Integer id, @RequestBody SubProcesoDTO subProcesoDTO) {
        return subProcesoService.actualizarSubproceso(id, subProcesoDTO);
    }
   

}