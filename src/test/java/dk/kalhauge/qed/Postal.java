package dk.kalhauge.qed;

public class Postal {
  private final int code;
  private final String district;

  public Postal(int code, String district) {
    this.code = code;
    this.district = district;
    }

  public int getCode() {
    return code;
    }

  public String getDistrict() {
    return district;
    }

  @Override
  public String toString() {
    return ""+code+"  "+district;
    }
  
  

  }
