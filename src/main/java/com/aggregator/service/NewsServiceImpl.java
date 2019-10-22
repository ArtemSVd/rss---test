package com.aggregator.service;

import com.aggregator.dao.NewsDAO;
import com.aggregator.model.Category;
import com.aggregator.model.News;
import com.aggregator.parsers.XmlParser;
import com.aggregator.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Сервисный класс, предназначенный для работы с новостями.
 */
@Service
public class NewsServiceImpl implements NewsService {

    /**
     * DAO - объект, предназначенный для работы с новостями.
     */
    private final NewsDAO newsDAO;
    /**
     * Сервис для работы с категориями.
     */
    private final CategoryServiceImpl categoryService;
    /**
     * Парсер xml - документов (RSS - лент).
     */
    private final XmlParser xmlParser;

    @Autowired
    public NewsServiceImpl(final NewsDAO newsDAO,
                           final CategoryServiceImpl categoryService,
                           final XmlParser xmlParser) {
        this.newsDAO = newsDAO;
        this.categoryService = categoryService;
        this.xmlParser = xmlParser;
    }

    @Override
    public void add(final Set<News> newsSet) {
        for (News news : newsSet) {
            newsDAO.addNews(news);
        }
    }

    @Override
    public void update(final News news) {
        newsDAO.updateNews(news);
    }

    @Override
    public void delete(final News news) {
        newsDAO.deleteNews(news);
    }

    @Override
    public List<News> getSortedNewsBySelectedCategories(
            final Set<Long> selectedCategories,
            final int page) {

        Date currentDate = new Date();

        for (long id : selectedCategories) {
            loadAndSaveNews(id, currentDate);
        }
        return newsDAO.getSortedNewsBySelectedCategories(
                selectedCategories,
                page);
    }

    @Override
    public List<News> getSortedNewsByAllCategories(final int page) {
        Date currentDate = new Date();

        for (long id : categoryService.getIdForAllCategories()) {
            loadAndSaveNews(id, currentDate);
        }
        return newsDAO.getSortedNewsByAllCategories(page);
    }

    /**
     * Метод для загрузки и сохранения новостей для конкретной категории.
     * При этом новости загружаются только в том случае,
     * если после последнего обращения прошло более 10 минут.
     * Также здесь происходит установка даты последнего обновления для
     * конкретной категории
     * @param id идентификатор категории
     * @param currentDate текущая дата/время
     */
    private void loadAndSaveNews(final long id,
                                 final Date currentDate) {
        Category category = categoryService.getById(id);

        Date lastUpdate = category.getLastUpdate();

        long minutesDiff = TimeUtil.getDateDiff(
                lastUpdate,
                currentDate,
                TimeUnit.MINUTES);

        final int TEN_MINUTES = 10;
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

    /**
     * Метод небоходим для того, чтобы узнать количество страниц
     * для новостей по выбранным категориям, исходя из того,
     * что максимальный размер страницы - 20 новостей.
     * @param selectedCategories выбранные категории
     * @return количество страниц (номер последней)
     */
    public int lastPaginatedNews(final Set<Long> selectedCategories) {

        if (selectedCategories != null) {
            return newsDAO.lastPaginatedNews(selectedCategories);
        } else {
            return newsDAO.lastPaginatedNews(
                    categoryService.getIdForAllCategories());
        }

    }
}
