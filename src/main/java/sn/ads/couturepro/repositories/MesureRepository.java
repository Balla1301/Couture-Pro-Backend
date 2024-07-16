package sn.ads.couturepro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ads.couturepro.entities.Mesure;

import java.util.List;

@Repository
public interface MesureRepository extends JpaRepository<Mesure, Long> {

    List<Mesure> findByClientId(Long id);
}
