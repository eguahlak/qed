package dk.kalhauge.qed;

import java.util.Date;

public class Person {
  private String firstName;
  private String lastName;
  private Date dateOfBirth;
  private Address address;

  public Person(String firstName, String lastName, Date dateOfBirth, Address address) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.address = address;
    }
  
  public String getName() {
    return firstName+" "+lastName;
    }

  public String getFirstName() {
    return firstName;
    }

  public Address getAddress() {
    return address;
    }
  
  public Date getDateOfBirth() { return dateOfBirth; }
  
  }
