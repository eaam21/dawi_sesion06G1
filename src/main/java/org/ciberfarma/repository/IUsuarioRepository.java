package org.ciberfarma.repository;

import org.ciberfarma.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	public Usuario findByCorreoAndClave(String correo, String clave);
}
