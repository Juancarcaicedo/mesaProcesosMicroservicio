package husjp.api.plantilla.controllers;

import husjp.api.plantilla.service.EjemploService;
import husjp.api.plantilla.service.dto.EjemploDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/ejemplo")
@CrossOrigin(origins = {"http://localhost:5173","http://optimus:5173"})
public class EjemploController {

    private EjemploService ejemploService;

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Obtiene el ejemplo")
    @GetMapping()
    public ResponseEntity<List<EjemploDTO>> getAllEjemplo(){
        return ResponseEntity.ok(ejemploService.getAllEjemplo());
    }
}
