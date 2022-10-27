package com.lidp.fare.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class Fare
{
   private Instant departureTime;
   private double distanceMi;
   private int seatRow;
   private double cost;
   private @Id int id;

   public Fare() {this.id = new FareId().hashCode();}

   public Fare(Instant departureTime, double distanceMi, int seatRow, double cost){
      this.setCost(cost);
      this.setSeatRow(seatRow);
      this.setDistanceMi(distanceMi);
      this.setDepartureTime(departureTime);
      this.generateId();
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

   public double getCost()
   {
      return cost;
   }

   public void setCost(double cost)
   {
      this.cost = cost;
   }

   public void generateId(){
      this.id = new FareId(this.departureTime,this.distanceMi, this.seatRow).hashCode();
   }

   public int getId(){
      return this.id;
   }

   @Override
   public String toString()
   {
      return "Fare{" + "departureTime=" + departureTime + ", distanceMi=" + distanceMi + ", seatRow=" + seatRow + ", cost=" + cost + '}';
   }
}
