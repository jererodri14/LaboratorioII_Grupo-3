package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Inventario;

@Repository
public interface IInventarioRepository extends JpaRepository<Inventario, Long> {

    /*@Query(value="{call valuate_actives_for_inventory(:id_in)}")
    Boolean calculateValuesOfActivesForInventory(Long inventoryId);*/
}
