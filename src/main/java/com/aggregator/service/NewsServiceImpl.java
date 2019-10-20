package com.aggregator.service;

import com.aggregator.dao.NewsDAO;
import com.aggregator.model.Category;
import com.aggregator.model.News;
import com.aggregator.parsers.XmlParser;
import com.aggregator.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsDAO newsDAO;
    private final CategoryServiceImpl categoryService;
    private final XmlParser xmlParser;

    @Autowired
    public NewsServiceImpl(NewsDAO newsDAO, CategoryServiceImpl categoryService, XmlParser xmlParser) {
        this.newsDAO = newsDAO;
        this.categoryService = categoryService;
        this.xmlParser = xmlParser;
    }

    @Override
    public void add(Set<News> newsSet) {
        for(News news : newsSet) {
            newsDAO.addNews(news);
        }
    }

    @Override
    public void update(News news) {
        newsDAO.updateNews(news);
    }

    @Override
    public News getById(int newsId) {
        return newsDAO.getNewsyById(newsId);
    }

    @Override
    public List<News> all() {
        return newsDAO.getAllNews();
    }

    @Override
    public void delete(News news) {
        newsDAO.deleteNews(news);
    }

    @Override
    public List<News> getSortedNewsBySelectedCategories(Set<Category> selectedCategories, int page) {
        Date currentDate = new Date();

        Set<Long> idSelectedCategories = new HashSet<>();
        for (Category category : selectedCategories) {
            loadAndSaveNews(category,currentDate);

            idSelectedCategories.add(category.getId());
        }

        return newsDAO.getSortedNewsBySelectedCategories(idSelectedCategories, page);
    }

    @Override
    public List<News> getSortedNewsByAllCategories(int page) {
        Date currentDate = new Date();
        for (Category category : categoryService.all()) {
            loadAndSaveNews(category,currentDate);
        }
        return newsDAO.getSortedNewsByAllCategories(page);
    }

    private void loadAndSaveNews(Category category, Date currentDate){
        Date lastUpdate = category.getLastUpdate();
        long minutesDiff = TimeUtil.getDateDiff(lastUpdate, currentDate, TimeUnit.MINUTES);
        int TEN_MINUTES = 10;
        if (minutesDiff >= TEN_MINUTES) {
            Set<News> newsByCategory;
            try {
                newsByCategory = xmlParser.getNewsByCategory(category);
                add(newsByCategory);
                category.setLastUpdate(currentDate);
                categoryService.update(category);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int lastPaginatedNews(Set<Category> selectedCategories){
        Set<Long> idSelectedCategories = new HashSet<>();

        for(Category category : selectedCategories){
            idSelectedCategories.add(category.getId());
        }

        if(selectedCategories == null){
            idSelectedCategories = categoryService.getIdForAllCategories();
        }

        return newsDAO.lastPaginatedNews(idSelectedCategories);
    }
}
