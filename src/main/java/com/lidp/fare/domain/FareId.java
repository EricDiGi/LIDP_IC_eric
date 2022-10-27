package com.lidp.fare.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class FareId implements Serializable
{
   private Instant departureTime;
   private double distanceMi;
   private int seatRow;

   public FareId()
   {
      // Default constructor
   }

   public FareId(Instant departureTime, double distanceMi, int seatRow)
   {
      this.departureTime = departureTime;
      this.distanceMi = distanceMi;
      this.seatRow = seatRow;
   }

   public Instant getDepartureTime()
   {
      return departureTime;
   }

   public void setDepartureTime(Instant departureTime)
   {
      this.departureTime = departureTime;
   }

   public double getDistanceMi()
   {
      return distanceMi;
   }

   public void setDistanceMi(double distanceMi)
   {
      this.distanceMi = distanceMi;
   }

   public int getSeatRow()
   {
      return seatRow;
   }

   public void setSeatRow(int seatRow)
   {
      this.seatRow = seatRow;
   }

   @Override
   public boolean equals(Object o)
   {
      if (this == o)
      {
         return true;
      }

      if (o == null || getClass() != o.getClass())
      {
         return false;
      }

      FareId fareId = (FareId) o;
      return Double.compare(fareId.distanceMi, distanceMi) == 0 && seatRow == fareId.seatRow && departureTime.equals(fareId.departureTime);
   }

   @Override
   public int hashCode()
   {
      return Objects.hash(departureTime, distanceMi, seatRow);
   }
}
