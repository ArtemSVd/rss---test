package com.aggregator.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Модель для выбранных пользователем категорий.
 */
public class Selector {
    private Set<Long> selectedCategories = new HashSet<>();

    public Selector() {
    }

    public Selector(Set<Long> selectedCategories) {
        this.selectedCategories = selectedCategories;
    }

    public Set<Long> getSelectedCategories() {
        return selectedCategories;
    }

    public void setSelectedCategories(Set<Long> selectedCategories) {
        this.selectedCategories = selectedCategories;
    }
}
