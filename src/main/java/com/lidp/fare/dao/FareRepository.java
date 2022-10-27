package com.lidp.fare.dao;

import java.util.Optional;

import com.lidp.fare.domain.Fare;
import com.lidp.fare.domain.FareId;

public interface FareRepository
{
   Iterable<Fare> findAll();
   Optional<Fare> findById(FareId fareId);
   Fare save(Fare fare);
}
