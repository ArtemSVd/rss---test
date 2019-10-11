package com.model;

import java.util.Set;

public class CheckboxModel {
    Set<Integer> selectedCategories;

    public CheckboxModel(Set<Integer> selectedCategories) {
        this.selectedCategories = selectedCategories;
    }

    public CheckboxModel() {
    }

    public Set<Integer> getSelectedCategories() {
        return selectedCategories;
    }

    public void setSelectedCategories(Set<Integer> selectedCategories) {
        this.selectedCategories = selectedCategories;
    }
}
