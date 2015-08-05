package com.chuanggu.app.dao;

import com.chuanggu.app.entity.Product;

public interface ProductDao {
    int deleteByPrimaryKey(String product_id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String product_id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}