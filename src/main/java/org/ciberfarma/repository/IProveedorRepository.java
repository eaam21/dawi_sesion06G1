package org.ciberfarma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.ciberfarma.model.Proveedor;
public interface IProveedorRepository extends JpaRepository<Proveedor, Integer> {

}
