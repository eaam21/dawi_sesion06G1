package org.ciberfarma.repository;

import java.util.List;

import org.ciberfarma.model.Categoria;
import org.ciberfarma.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto, String> {
	Producto findByCodigo(String codigo);
	List<Producto> findByCategoria(Categoria categoria);
}
