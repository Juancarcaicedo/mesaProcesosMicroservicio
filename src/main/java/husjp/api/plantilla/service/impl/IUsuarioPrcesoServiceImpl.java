package husjp.api.plantilla.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import husjp.api.plantilla.entity.SubProceso;
import husjp.api.plantilla.entity.Usuario;
import husjp.api.plantilla.entity.UsuarioProceso;
import husjp.api.plantilla.repository.SubProcesoRepository;
import husjp.api.plantilla.repository.UsuarioProcesoRepository;
import husjp.api.plantilla.repository.UsuarioRepository;
import husjp.api.plantilla.service.IUsuarioProcesoService;
import husjp.api.plantilla.service.dto.UsuarioProcesoDTO;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class IUsuarioPrcesoServiceImpl  implements IUsuarioProcesoService   {

	private UsuarioProcesoRepository usuarioProcesoRepository;
    private SubProcesoRepository subProcesoRepository;
    private UsuarioRepository usuarioRespository;
    private ModelMapper modelMapper;
    @Override
    public List<UsuarioProcesoDTO> obtenerUsuariosprocesos() {
        return usuarioProcesoRepository.findAll().stream()
        .map(usuarioProceso -> {
            UsuarioProcesoDTO dto = modelMapper.map(usuarioProceso, UsuarioProcesoDTO.class);
            dto.setIdusuario(usuarioProceso.getUsuario().getDocumento());
            dto.setIdsubProceso(usuarioProceso.getSubProceso().getIdSubProceso());
            //dto.setNombreUsuario(usuarioProceso.getUsuario().getNombreCompleto());
            dto.setDescripcionSubproceso(usuarioProceso.getSubProceso().getDescripcion());
            return dto;
        })
        .collect(Collectors.toList()); 
    }

    @Override
    public List<UsuarioProcesoDTO> obtenerprocesosPorUsuario(String idUsuario) {
        List<UsuarioProceso> usuarioProcesos = usuarioProcesoRepository.findAllByUsuarioId(idUsuario);
        
        return usuarioProcesos.stream().map(up -> {
            UsuarioProcesoDTO dto = new UsuarioProcesoDTO();
            dto.setId(up.getId());
            dto.setEstado(up.getEstado());
            dto.setFechaInicio(up.getFechaInicio());
            dto.setFechaFin(up.getFechaFin());
            dto.setIdusuario(up.getUsuario().getDocumento());
            dto.setIdsubProceso(up.getSubProceso().getIdSubProceso());
            dto.setDescripcionSubproceso(up.getSubProceso().getDescripcion());
            //dto.setNombreUsuario(up.getUsuario().getNombreCompleto());
            return dto;
        }).collect(Collectors.toList());
    }
      
    @Override
    public UsuarioProcesoDTO crearUsuarioProceso(UsuarioProcesoDTO usuarioProcesoDTO) {

         
        Optional<SubProceso> subProcesoOpt = subProcesoRepository.findById(usuarioProcesoDTO.getIdsubProceso());
        if (!subProcesoOpt.isPresent()) {
            throw new IllegalArgumentException("El SubProceso con ID " + usuarioProcesoDTO.getIdsubProceso() + " no existe.");
        }
        Optional<Usuario> usuarioOpt = usuarioRespository.findById(usuarioProcesoDTO.getIdusuario());
        if (!usuarioOpt.isPresent()) {
            throw new IllegalArgumentException("El Usuario con ID " + usuarioProcesoDTO.getIdusuario() + " no existe.");
        }
        UsuarioProceso usuarioSubProceso = modelMapper.map(usuarioProcesoDTO, UsuarioProceso.class);
        // Asignar entidades existentes
        usuarioSubProceso.setSubProceso(subProcesoOpt.get());
        usuarioSubProceso.setUsuario(usuarioOpt.get());
        // Establecer estado inicial
        usuarioSubProceso.setEstado(1);  // Estado por defecto es 1 (en curso)
        UsuarioProceso savedUsuarioSubProceso = usuarioProcesoRepository.save(usuarioSubProceso);
        // Devolver DTO guardado
        return modelMapper.map(savedUsuarioSubProceso, UsuarioProcesoDTO.class);
    }

    @Override
    public UsuarioProcesoDTO actualizarUsuarioProcesoFecha(Integer id, LocalDateTime nuevaFechaFin) {
        Optional<UsuarioProceso> usuOptional = usuarioProcesoRepository.findById(id);
        if (usuOptional.isPresent()) {
            UsuarioProceso usuarioProceso = usuOptional.get();
            LocalDateTime hoy = LocalDateTime.now();
            // Actualizar la fecha fin
            usuarioProceso.setFechaFin(nuevaFechaFin);
            // Si la nueva fecha fin es superior a la fecha actual, cambiar el estado a 1 (en curso)
            if (nuevaFechaFin.isAfter(hoy)) {
                usuarioProceso.setEstado(1);
            } else {
                // Si la nueva fecha fin no es superior a la fecha actual, mantén el estado como atrasado (3)
                usuarioProceso.setEstado(3);
            }
            UsuarioProceso savedUsuarioProceso = usuarioProcesoRepository.save(usuarioProceso);
            // Convertir la entidad guardada en DTO y devolverla
            return modelMapper.map(savedUsuarioProceso, UsuarioProcesoDTO.class);
        } else {
            throw new IllegalArgumentException("UsuarioProceso no encontrado.");
        }
    }
   

    @Override
    public UsuarioProcesoDTO actualizarUsuarioprocesoEstado(Integer idsubproceso, String enlace) {
        Optional<UsuarioProceso> usuOptional = usuarioProcesoRepository.findById(idsubproceso);
        if(usuOptional.isPresent()){
            UsuarioProceso usuarioProceso = usuOptional.get();
            usuarioProceso.setEstado(2);
            usuarioProceso.setEnlace(enlace);
            UsuarioProceso savedUsuarioProceso = usuarioProcesoRepository.save(usuarioProceso);
            return modelMapper.map(savedUsuarioProceso, UsuarioProcesoDTO.class);
        }else {
            throw new IllegalArgumentException("UsuarioProceso no encontrado.");
        }
    }


    @Scheduled(fixedRate = 86400000)
   public void actualizarEstadosAutomáticamente() {
        List<UsuarioProceso> procesos = usuarioProcesoRepository.findAll();
        LocalDateTime hoy = LocalDateTime.now();
        System.out.println("la fecha es "+ hoy);
        procesos.forEach(proceso -> {
            if (proceso.getEstado() == 1 && proceso.getFechaFin().isBefore(hoy)) {
                proceso.setEstado(3); // Estado "atrasado"
                usuarioProcesoRepository.save(proceso);
            }
        });
    }
   
    @Override
    public void eliminarUsuarioProceso(Integer id) {
       Optional <UsuarioProceso> usuOptional = usuarioProcesoRepository.findById(id);
       if(usuOptional.isPresent()){
         usuarioProcesoRepository.deleteById(id);
       }else{
        throw new IllegalArgumentException("UsuarioProceso no encontrado.");
       } 

    }

    @Override
    public List<UsuarioProcesoDTO> obtenerUsuarioProcesoArea(Integer idArea) {
        List<UsuarioProceso> usuarioProcesos = usuarioProcesoRepository.findAllByAreaId(idArea);
        return usuarioProcesos.stream()
                .map(usuarioProceso -> {
                    UsuarioProcesoDTO dto = modelMapper.map(usuarioProceso, UsuarioProcesoDTO.class);
                    dto.setIdusuario(usuarioProceso.getUsuario().getDocumento());
                    dto.setIdsubProceso(usuarioProceso.getSubProceso().getIdSubProceso());
                    dto.setNombreUsuario(usuarioProceso.getUsuario().getNombrecompleto());
                    dto.setDescripcionSubproceso(usuarioProceso.getSubProceso().getDescripcion());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioProcesoDTO transferirSubprocesoAUsuario(Integer idUsuarioProceso, String nuevoUsuarioId) {
        Optional<UsuarioProceso> usuarioProcesoOpt = usuarioProcesoRepository.findById(idUsuarioProceso);
        if (!usuarioProcesoOpt.isPresent()) {
            throw new IllegalArgumentException("UsuarioProceso no encontrado.");
        }
        Optional<Usuario> nuevoUsuarioOpt = usuarioRespository.findById(nuevoUsuarioId);
        if (!nuevoUsuarioOpt.isPresent()) {
            throw new IllegalArgumentException("Usuario con ID " + nuevoUsuarioId + " no encontrado.");
        }

        // Actualizar el UsuarioProceso con el nuevo usuario
        UsuarioProceso usuarioProceso = usuarioProcesoOpt.get();
        usuarioProceso.setUsuario(nuevoUsuarioOpt.get());
        
        // Guardar el cambio en la base de datos
        UsuarioProceso usuarioProcesoActualizado = usuarioProcesoRepository.save(usuarioProceso);

        // Convertir la entidad actualizada a DTO y devolverla
        return modelMapper.map(usuarioProcesoActualizado, UsuarioProcesoDTO.class);
    }

}
