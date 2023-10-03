package org.ciberfarma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ciberfarma.model.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {

}
