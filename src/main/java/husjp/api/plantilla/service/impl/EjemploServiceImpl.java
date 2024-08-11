package husjp.api.plantilla.service.impl;

import husjp.api.plantilla.service.EjemploService;
import husjp.api.plantilla.service.dto.EjemploDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjemploServiceImpl implements EjemploService {

    @Override
    public List<EjemploDTO> getAllEjemplo() {
        List<EjemploDTO> lista = List.of(
                new EjemploDTO("Ejemplo 1","Ejemplo 1",1),
                new EjemploDTO("Ejemplo 2","Ejemplo 2",2),
                new EjemploDTO("Ejemplo 3","Ejemplo 3",3)
        );
        return lista;
    }
}
