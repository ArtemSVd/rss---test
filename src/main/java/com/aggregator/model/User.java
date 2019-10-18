package com.aggregator.model;

import java.util.HashSet;
import java.util.Set;

public class User implements Cloneable {
    private String login;
    private String password;

    private Set<Long> selectedCategories = new HashSet<>();

    public User() {
    }

    public Set<Long> getSelectedCategories() {
        return selectedCategories;
    }

    public void setSelectedCategories(Set<Long> selectedCategories) {
        this.selectedCategories = selectedCategories;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
