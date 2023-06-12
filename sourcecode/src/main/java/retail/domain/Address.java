package retail.domain;



import javax.persistence.*;

import lombok.Data;


@Entity
@Data

public class Address {
	
    @Id
    @GeneratedValue
    private long Id;
    private String addressLine;
    private String aptNo;
    private String city;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private StateProvince StateProvince;
    private String postalCode;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Phone phone;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private AddressType addressType;


    public Address(String addressLine, String aptNo, String city,
                    String postalCode) {
        this.addressLine = addressLine;
        this.aptNo = aptNo;
       this.city = city;
        this.postalCode = postalCode;

    }


    public Address() {

    }

    public String getAddressLine() {
        return addressLine;
    }

    public String getAptNo() {
        return aptNo;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public void setAptNo(String aptNo) {
        this.aptNo = aptNo;
    }

    @Override
    public String toString() {
        return "Address{" +
                "Id=" + Id +
                ", addressLine='" + addressLine + '\'' +
                ", aptNo='" + aptNo + '\'' +
                ", City='" + city + '\'' +
                ", StateProvince=" + StateProvince +
                ", PostalCode='" + postalCode + '\'' +
                ", Phone=" + phone +
                ", AddressType=" + addressType +
                '}';
    }
}
