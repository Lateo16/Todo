package com.lateofrederick.todoish;

public class Todos {
    private int id;
    private String firstname;
    private String lastname;
    private String Telephone;

    public Todos(String firstname, String lastname, String telephone) {
        this.firstname = firstname;
        this.lastname = lastname;
        Telephone = telephone;
    }

    public Todos(){}

    public Todos(int id, String firstname, String lastname, String telephone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        Telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }
}
