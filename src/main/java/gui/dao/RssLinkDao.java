package gui.dao;

import gui.model.RssLink;

public interface RssLinkDao {
    void save(RssLink link);
    void update(RssLink link);
    void delete(RssLink link);
    RssLink findByName(String name);
}
