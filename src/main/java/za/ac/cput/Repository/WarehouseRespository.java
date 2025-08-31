package za.ac.cput.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.Domain.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, String> {
}
