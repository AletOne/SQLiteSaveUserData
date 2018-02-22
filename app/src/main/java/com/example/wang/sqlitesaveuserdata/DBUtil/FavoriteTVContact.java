package com.example.wang.sqlitesaveuserdata.DBUtil;

import android.provider.BaseColumns;

/**
 * Created by Wang on 2/20/18.
 */

public final class FavoriteTVContact {
    private FavoriteTVContact(){}

    public static class UserEntry implements BaseColumns{
        public static final String TABLE_NAME = "favorite_tv_table";
        public static final String COLUMN_NAME_UNMAE = "name";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_TVSHOW = "tv_show";
    }

}
