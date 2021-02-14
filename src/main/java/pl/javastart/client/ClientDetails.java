package pl.javastart.client;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "client_details")
public class ClientDetails implements Serializable {
    private static final long serialUID = 1233456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client_details")
    private Long id;
    @Column(length = 9,name = "phone_number",nullable = false)
    private String phoneNumber;
    @Column(length = 256,nullable = false)
    private String address;
    @Column(length = 9,name = "id_card_number",nullable = false,unique = true)
    private String idNumber;

    public ClientDetails() {
    }

    public ClientDetails(String phoneNumber, String address, String idNumber) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.idNumber = idNumber;
    }

    public static long getSerialUID() {
        return serialUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public String toString() {
        return idNumber + ", " + phoneNumber + ", " + address + ";";
    }
}
