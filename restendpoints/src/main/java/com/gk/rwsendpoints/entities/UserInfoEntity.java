package com.gk.rwsendpoints.entities;

import javax.persistence.*;

/**
 * Created by greg korenevsky on 7/28/14.
 */
@Entity
@Table(name = "USER_INFO", schema = "test", catalog = "")
public class UserInfoEntity {

    String firstName;
    String lastName;
    String gender;
    String addressLine1;
    String addressLine2;
    String city;
    String stateOrProvince;
    String country;
    String postalCode;
    String userId;
    String encryptedPassword;

    @Basic
    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "GENDER")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "ADDRESS_LINE_1")
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @Basic
    @Column(name = "ADDRESS_LINE_2")
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @Basic
    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "STATE_OR_PROVINCE")
    public String getStateOrProvince() {
        return stateOrProvince;
    }

    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    @Basic
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "POSTAL_CODE")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Id
    @Column(name = "USER_ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "ENCRYPTED_PASSWORD")
    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    /*
        Convenience methods
     */
    public UserInfoEntity withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserInfoEntity withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserInfoEntity withGender(String gender) {
        this.gender = gender;
        return this;
    }

    public UserInfoEntity withAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public UserInfoEntity withAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public UserInfoEntity withCity(String city) {
        this.city = city;
        return this;
    }

    public UserInfoEntity withStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
        return this;
    }

    public UserInfoEntity withCountry(String country) {
        this.country = country;
        return this;
    }

    public UserInfoEntity withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public UserInfoEntity withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public UserInfoEntity withEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
        return this;
    }
}
