package cl.hope.repositories;


import cl.hope.repositories.entities.HopeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HopeRepository extends JpaRepository<HopeEntity, Long> {

}