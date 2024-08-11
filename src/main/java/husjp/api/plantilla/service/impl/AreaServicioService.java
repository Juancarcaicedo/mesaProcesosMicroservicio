package husjp.api.plantilla.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import husjp.api.plantilla.repository.AreaServicioRepository;
import husjp.api.plantilla.service.IAreaServicioService;
import husjp.api.plantilla.service.dto.AreaServicioDTO;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class AreaServicioService implements IAreaServicioService {
    private AreaServicioRepository areaServicioRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AreaServicioDTO> obtenerAreasServicio() {
      List<AreaServicioDTO> areaDtos = areaServicioRepository.findAll().stream()
        .map(area -> modelMapper.map(area, AreaServicioDTO.class))
        .collect(Collectors.toList());

        return areaDtos;
    }
    
}
