package com.totemcrew.directors.domain.persistence;

import com.totemcrew.directors.domain.model.entity.Principal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrincipalRepository extends JpaRepository<Principal, Long> {

    //@Query("select d from Director d where d.email = ?1")
    //Optional<Director> findByEmail(String email);

}
