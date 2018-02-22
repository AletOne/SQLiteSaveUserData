package com.example.wang.sqlitesaveuserdata.persistence;

import com.example.wang.sqlitesaveuserdata.model.FavoriteTv;

import java.util.List;

/**
 * Created by Wang on 2/20/18.
 */

public interface FavoriteDAO {

    public boolean insertFavorite(FavoriteTv favoriteTv);

    public List<FavoriteTv> selectAll();

    public boolean updateFavorite(FavoriteTv favoriteTv);

    public boolean deleteFavorite(int id);

    public FavoriteTv selectById(int id);
}
