package com.cmcc.edu.dao;

import java.util.List;

import com.cmcc.edu.entity.UrlFilter;


public interface UrlFilterDao {

    public UrlFilter createUrlFilter(UrlFilter urlFilter);
    public UrlFilter updateUrlFilter(UrlFilter urlFilter);
    public void deleteUrlFilter(Long urlFilterId);

    public UrlFilter findOne(Long urlFilterId);
    public List<UrlFilter> findAll();
}
