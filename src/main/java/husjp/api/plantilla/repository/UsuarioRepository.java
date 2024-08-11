package husjp.api.plantilla.repository;

import husjp.api.plantilla.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
