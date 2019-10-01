package gui.dao;

import gui.model.RssLink;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class RssLinkDaoImpl extends HibernateDaoSupport implements RssLinkDao {

    @Override
    public void save(RssLink link) {
        getHibernateTemplate().save(link);
    }

    @Override
    public void update(RssLink link) {
        getHibernateTemplate().update(link);
    }

    @Override
    public void delete(RssLink link) {
        getHibernateTemplate().delete(link);
    }

    @Override
    public RssLink findByName(String name) {
        List list = getHibernateTemplate().find("from rss_Link where name = ?",name);
        return null;
    }
}
