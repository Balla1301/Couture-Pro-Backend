package sn.ads.couturepro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.ads.couturepro.entities.Utilisateur;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findById(Long id);
    Optional<Utilisateur> findByTelephone(String telephone);
    Boolean existsByTelephone(String telephone);
    boolean existsByTelephoneAndIdNot(String telephone, Long id);
    List<Utilisateur> findAll();
}
