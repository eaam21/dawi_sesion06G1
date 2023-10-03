package org.ciberfarma.repository;

import org.ciberfarma.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto, String> {

}
