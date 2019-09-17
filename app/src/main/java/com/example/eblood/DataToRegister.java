package com.example.eblood;

public class DataToRegister {
    private String searchKey;
    private String name;
    private String phoneNumber;

    public DataToRegister() {
    }

    public DataToRegister(String searchKey, String name, String phoneNumber) {
        this.searchKey = searchKey;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
