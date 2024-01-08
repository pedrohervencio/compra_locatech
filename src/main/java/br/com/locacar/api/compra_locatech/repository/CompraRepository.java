package br.com.locacar.api.compra_locatech.repository;

import br.com.locacar.api.compra_locatech.entities.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}
