package sn.ads.couturepro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ads.couturepro.entities.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findById(Long id);
    boolean existsByTelephoneAndUtilisateurId(String telephone, Long id);
    List<Client> findByUtilisateurId(Long id);
    boolean existsByTelephoneAndIdNot(String telephone, Long id);
}
