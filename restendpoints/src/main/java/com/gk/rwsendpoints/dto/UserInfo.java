package com.gk.rwsendpoints.dto;

/**
 * Created by greg korenevsky on 7/28/14.
 */
public class UserInfo {

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateOrProvince() {
        return stateOrProvince;
    }

    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public UserInfo withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserInfo withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserInfo withGender(String gender) {
        this.gender = gender;
        return this;
    }

    public UserInfo withAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public UserInfo withAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public UserInfo withCity(String city) {
        this.city = city;
        return this;
    }

    public UserInfo withStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
        return this;
    }

    public UserInfo withCountry(String country) {
        this.country = country;
        return this;
    }

    public UserInfo withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public UserInfo withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public UserInfo withEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
        return this;
    }
}
