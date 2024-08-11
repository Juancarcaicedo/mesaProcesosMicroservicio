package husjp.api.plantilla.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EjemploDTO {

    private String nombre;
    private String apellido;
    private int edad;

}
