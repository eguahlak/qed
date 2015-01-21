package dk.kalhauge.qed;

public class Address {
  private String street;
  private Postal postal;

  public Address(String street, Postal postal) {
    this.street = street;
    this.postal = postal;
    }

  public String getStreet() {
    return street;
    }

  public Postal getPostal() {
    return postal;
    }

  @Override
  public String toString() {
    return street+", "+postal;
    }
  
  }
