package husjp.api.plantilla.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import husjp.api.plantilla.service.IAreaServicioService;
import husjp.api.plantilla.service.dto.AreaServicioDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/AreaServicio")
@CrossOrigin(origins = {"http://localhost:5173","http://optimus:5173"})
public class AreaServicioController {
    private IAreaServicioService areaServicioService;
    @SecurityRequirement(name = "Bearer Authentication")//PERMITE QUE SE PUEDA EJECUTAR DESDE SWAGGER
    @GetMapping
    public List<AreaServicioDTO> ListarAreasServicio(){
        return areaServicioService.obtenerAreasServicio();
    }

    
}
