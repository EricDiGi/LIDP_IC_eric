package com.lidp.fare.dao;

import com.lidp.fare.domain.Fare;
import com.lidp.fare.domain.FareId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel="fares", path="fares")
public interface FareRepository extends JpaRepository<Fare, Long>
{
    List<Fare> findAll();
    Optional<Fare> findById(@Param("fareid") FareId id);
    Fare save(Fare fare);
}
