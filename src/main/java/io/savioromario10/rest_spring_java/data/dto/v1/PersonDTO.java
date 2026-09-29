package io.savioromario10.rest_spring_java.data.dto.v1;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.savioromario10.rest_spring_java.serializer.GenderSerializer;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id", "first_name", "last_name", "address", "gender"})
//@JsonFilter("PersonFilter")
public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String phoneNumber;

    @JsonIgnore
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date bithDay;

    private String address;

    //@JsonSerialize(using = GenderSerializer.class)
    private String gender;

    //private String sensitiveData;

    public PersonDTO() {}

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getBithDay() {
        return bithDay;
    }
    public void setBithDay(Date bithDay) {
        this.bithDay = bithDay;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /*
    public String getSensitiveData() {
        return sensitiveData;
    }
    public void setSensitiveData(String sensitiveData) {
       this.sensitiveData = sensitiveData;
    }
     */

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PersonDTO personDTO)) return false;
        return Objects.equals(getId(), personDTO.getId()) &&
                Objects.equals(getFirstName(), personDTO.getFirstName()) &&
                Objects.equals(getLastName(), personDTO.getLastName()) &&
                Objects.equals(getPhoneNumber(), personDTO.getPhoneNumber()) &&
                Objects.equals(getBithDay(), personDTO.getBithDay()) &&
                Objects.equals(getAddress(), personDTO.getAddress()) &&
                Objects.equals(getGender(), personDTO.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getPhoneNumber(), getBithDay(), getAddress(),
                getGender());
    }
}