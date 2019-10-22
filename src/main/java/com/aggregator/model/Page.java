package com.aggregator.model;

import org.springframework.stereotype.Component;

/**
 * Модель для постраничного отображения новостей
 * содержит текущий номер страницы
 * и номер последней страницы в наборе новостей.
 */
@Component
public class Page {
    private int lastPage;
    private int numPage;


    public Page() {
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getNumPage() {
        return numPage;
    }

    public void setNumPage(int numPage) {
        this.numPage = numPage;
    }
}
