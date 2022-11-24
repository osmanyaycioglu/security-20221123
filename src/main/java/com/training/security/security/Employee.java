package com.training.security.security;

import lombok.ToString;

@ToString
public class Employee {

    private int counter;

    private   String name;
    private   String surname;
    private   String username;
    protected String password;
    String data;

    public Employee() {
    }

    Employee(String name,
             String surname,
             String username,
             String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }


    public int getCounter() {
        return this.counter;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("password 8 den küçük olamaz");
        }
        this.password = password;
    }

    public static class EmployeeBuilder {
        private String name;
        private String surname;
        private String username;
        private String password;

        EmployeeBuilder() {
        }

        public EmployeeBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public EmployeeBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public EmployeeBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public EmployeeBuilder withPassword(String password) {
            this.password = password;
            if (password.length() < 8) {
                throw new IllegalArgumentException("password 8 den küçük olamaz");
            }
            return this;
        }

        public Employee build() {
            return new Employee(name,
                                surname,
                                username,
                                password);
        }

        public String toString() {
            return "Employee.EmployeeBuilder(name="
                   + this.name
                   + ", surname="
                   + this.surname
                   + ", username="
                   + this.username
                   + ", password="
                   + this.password
                   + ")";
        }
    }
}
