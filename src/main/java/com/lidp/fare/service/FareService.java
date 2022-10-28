package com.lidp.fare.service;

import com.lidp.fare.dao.FareRepository;
import com.lidp.fare.domain.Fare;
import com.lidp.fare.domain.FareId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
public class FareService
{
   private static final Logger logger = Logger.getLogger(FareService.class.getName());

   @Autowired
   private final FareRepository fareRepository;
   private boolean persist = true;

   public FareService(FareRepository fareRepository)
   {
      this.fareRepository = fareRepository;
   }

   @GetMapping("/fares")
   public Iterable<Fare> getFares()
   {
      return fareRepository.findAll();
   }

   @GetMapping("/fare")
   public double getFare(Instant departureTime, double distanceMi, int seatRow)
   {
      // check if fare has already been calculated
      FareId id = new FareId(departureTime, distanceMi, seatRow);

      Optional<Fare> result = fareRepository.findById(id);

      if (result.isPresent())
      {
         // if cost calculation exists, return it
         Fare fare = result.get();
         logger.info("Returning existing fare: " + fare);
         return fare.getCost();
      }
      else
      {
         // else calculate it and persist the result
         //double cost = calculateFare(departureTime, distanceMi, seatRow);

         Fare fare = new Fare();
         fare.setDepartureTime(departureTime);
         fare.setDistanceMi(distanceMi);
         fare.setSeatRow(seatRow);
         double cost = calculateFareNoPersist(fare);
         fare.setCost(cost);

         fareRepository.save(fare);

         logger.info("Saving new fare: " + fare);

         return cost;
      }
   }

   private double calculateFareNoPersist(Fare postFare){
      this.persist = false;
      double out = this.calculateFare(postFare);
      this.persist = true;
      return out;
   }

   @PostMapping(path="/farecalc",consumes = MediaType.APPLICATION_JSON_VALUE)
//   private double calculateFare(@RequestBody Instant departureTime, @RequestBody double distanceMi, @RequestBody int seatRow)
   private double calculateFare(@RequestBody Fare postFare)
   {
      logger.info(postFare.getDepartureTime().getClass().toString());
      // the higher the service level (based on the seat row), the higher the base rate
      double baseRate = getBaseRate(postFare.getSeatRow());

      // the greater the distance, the higher the fare
      double distanceFee = postFare.getDistanceMi() * 0.1;

      // the closer to the departure date, the higher the fare
      long daysUntilDeparture = Instant.now().until(postFare.getDepartureTime(), ChronoUnit.DAYS);
      double departureTimeFee = (0.0088 * daysUntilDeparture * daysUntilDeparture) - (1.3869 * daysUntilDeparture) + 100;

      double calcCost = baseRate + distanceFee + departureTimeFee;

      //Data Access Layer
      FareId id = new FareId(postFare.getDepartureTime(), postFare.getDistanceMi(), postFare.getSeatRow());
      Optional<Fare> result = fareRepository.findById(id);

      logger.info("At Persist Layer");
      if (!result.isPresent() && this.persist) {
         Fare fare = new Fare(postFare.getDepartureTime(), postFare.getDistanceMi(), postFare.getSeatRow(), calcCost);
         fareRepository.save(fare);
         logger.info("Saving new fare: " + fare);
      }

      return calcCost;
   }

   @GetMapping("/baserates/{seatPosition}")
   private double getBaseRate(@PathVariable int seatPosition)
   {
      if (seatPosition < 4)
      {
         // first class base rate
         return 300;
      }
      else if (seatPosition < 8)
      {
         // comfort base rate
         return 150;
      }
      else
      {
         // economy base rate
         return 50;
      }
   }
}
