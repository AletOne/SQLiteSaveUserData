package com.example.wang.sqlitesaveuserdata.persistence.persistence.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.wang.sqlitesaveuserdata.DBUtil.FavoriteTVContact;
import com.example.wang.sqlitesaveuserdata.DBUtil.FavoriteTVDBHelper;
import com.example.wang.sqlitesaveuserdata.model.FavoriteTv;
import com.example.wang.sqlitesaveuserdata.persistence.FavoriteDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wang on 2/20/18.
 */

public class FavoriteDAOImpl implements FavoriteDAO {

    private Context mContext;
    private FavoriteTVDBHelper dbHelper;

    public FavoriteDAOImpl(Context context){
        mContext = context;
        dbHelper = new FavoriteTVDBHelper(context);
    }

    @Override
    public boolean insertFavorite(FavoriteTv favoriteTv) {

        if (favoriteTv != null){
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(FavoriteTVContact.UserEntry.COLUMN_NAME_UNMAE, favoriteTv.getName());
            values.put(FavoriteTVContact.UserEntry.COLUMN_NAME_EMAIL, favoriteTv.getEmail());
            values.put(FavoriteTVContact.UserEntry.COLUMN_NAME_TVSHOW, favoriteTv.getFavoriteTv());
            if (favoriteTv.getId() != -1){
                values.put(FavoriteTVContact.UserEntry._ID, favoriteTv.getId());
            }

            long newRowId = db.insert(FavoriteTVContact.UserEntry.TABLE_NAME, null, values);
            if (newRowId != -1){
                db.close();
                return true;
            }else{
                db.close();
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public List<FavoriteTv> selectAll() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String[] projection = {
                FavoriteTVContact.UserEntry._ID,
                FavoriteTVContact.UserEntry.COLUMN_NAME_UNMAE,
                FavoriteTVContact.UserEntry.COLUMN_NAME_EMAIL,
                FavoriteTVContact.UserEntry.COLUMN_NAME_TVSHOW
        };
        Cursor cursor = database.query(
                FavoriteTVContact.UserEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                null
        );
        List<FavoriteTv> result = new ArrayList<>();

        while (cursor.moveToNext()){
            FavoriteTv favoriteTv = new FavoriteTv();
            favoriteTv.setId(cursor.getInt(cursor.getColumnIndexOrThrow(FavoriteTVContact.UserEntry._ID)));
            favoriteTv.setName(cursor.getString(cursor.getColumnIndexOrThrow(FavoriteTVContact.UserEntry.COLUMN_NAME_UNMAE)));
            favoriteTv.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(FavoriteTVContact.UserEntry.COLUMN_NAME_EMAIL)));
            favoriteTv.setFavoriteTv(cursor.getString(cursor.getColumnIndexOrThrow(FavoriteTVContact.UserEntry.COLUMN_NAME_TVSHOW)));
            result.add(favoriteTv);
        }

        database.close();

        return result;
    }

    public FavoriteTv selectById(int id){
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String[] projection = {
                FavoriteTVContact.UserEntry._ID,
                FavoriteTVContact.UserEntry.COLUMN_NAME_UNMAE,
                FavoriteTVContact.UserEntry.COLUMN_NAME_EMAIL,
                FavoriteTVContact.UserEntry.COLUMN_NAME_TVSHOW
        };
        String selection = FavoriteTVContact.UserEntry._ID + " = ?";
        String[] args = {String.valueOf(id)};

        Cursor cursor = database.query(
                FavoriteTVContact.UserEntry.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null,
                null
        );
        FavoriteTv favoriteTv = null;

        if (cursor.moveToNext()){
            favoriteTv = new FavoriteTv();
            favoriteTv.setId(cursor.getInt(cursor.getColumnIndexOrThrow(FavoriteTVContact.UserEntry._ID)));
            favoriteTv.setName(cursor.getString(cursor.getColumnIndexOrThrow(FavoriteTVContact.UserEntry.COLUMN_NAME_UNMAE)));
            favoriteTv.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(FavoriteTVContact.UserEntry.COLUMN_NAME_EMAIL)));
            favoriteTv.setFavoriteTv(cursor.getString(cursor.getColumnIndexOrThrow(FavoriteTVContact.UserEntry.COLUMN_NAME_TVSHOW)));
        }
        return favoriteTv;

    }
    @Override
    public boolean updateFavorite(FavoriteTv favoriteTv) {
        if (favoriteTv != null){
            SQLiteDatabase database = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            if (favoriteTv.getName() != null && !favoriteTv.getName().equals("")){
                values.put(FavoriteTVContact.UserEntry.COLUMN_NAME_UNMAE, favoriteTv.getName());
            }
            if (favoriteTv.getEmail() != null && !favoriteTv.getEmail().equals("")){
                values.put(FavoriteTVContact.UserEntry.COLUMN_NAME_EMAIL, favoriteTv.getEmail());
            }
            if (favoriteTv.getFavoriteTv() != null && !favoriteTv.getFavoriteTv().equals("")){
                values.put(FavoriteTVContact.UserEntry.COLUMN_NAME_TVSHOW, favoriteTv.getFavoriteTv());
            }

            String selection = FavoriteTVContact.UserEntry._ID + " = ?";
            String[] args = {String.valueOf(favoriteTv.getId())};
            int count = database.update(FavoriteTVContact.UserEntry.TABLE_NAME,
                    values,
                    selection,
                    args);

            if (count <= 0){
                return false;
            }
            database.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteFavorite(int id) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String selection = FavoriteTVContact.UserEntry._ID + " = ?";
        String[] args = {String.valueOf(id)};

        int count = database.delete(FavoriteTVContact.UserEntry.TABLE_NAME,
                selection, args);
        if (count == 0){
            return false;
        }
        database.close();

        return true;
    }
}
