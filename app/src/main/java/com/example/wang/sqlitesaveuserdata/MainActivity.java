package com.example.wang.sqlitesaveuserdata;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wang.sqlitesaveuserdata.model.FavoriteTv;
import com.example.wang.sqlitesaveuserdata.persistence.FavoriteDAO;
import com.example.wang.sqlitesaveuserdata.persistence.persistence.impl.FavoriteDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private EditText nameEt;
    private EditText emailEt;
    private EditText favoriteTvEt;
    private EditText idEt;

    FavoriteDAO favoriteDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEt = findViewById(R.id.name_et);
        emailEt = findViewById(R.id.email_et);
        favoriteTvEt = findViewById(R.id.favorite_et);
        idEt = findViewById(R.id.id_et);
        favoriteDAO = new FavoriteDAOImpl(this);
    }


    public void handleClick(View v){
        switch (v.getId()){
            case R.id.add_btn:
                addData();
                break;
            case R.id.update_btn:
                updateData();
                break;
            case R.id.view_btn:
                viewData();
                break;
            case R.id.delete_btn:
                deleteData();
                break;
        }
    }

    private void addData(){
        String name = nameEt.getText().toString();
        String email = emailEt.getText().toString();
        String favoriteTv = favoriteTvEt.getText().toString();
        String id = idEt.getText().toString();

        FavoriteTv ft = new FavoriteTv();
        ft.setName(name);
        ft.setEmail(email);
        ft.setFavoriteTv(favoriteTv);
        if (!id.equals("")){
            int iid = Integer.valueOf(id);
            if (favoriteDAO.selectById(iid) != null){
                Toast.makeText(this, "Data exists ! Change Id !", Toast.LENGTH_LONG).show();
                return;
            }
            ft.setId(iid);
        }
        boolean result = favoriteDAO.insertFavorite(ft);
        if (result){
            Toast.makeText(this, "Insert successfully", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Insert failed", Toast.LENGTH_LONG).show();
        }
    }

    private void updateData(){
        String name = nameEt.getText().toString();
        String email = emailEt.getText().toString();
        String favoriteTv = favoriteTvEt.getText().toString();
        String id = idEt.getText().toString();

        if (id.equals("")){
            Toast.makeText(this, "Id can not be null !", Toast.LENGTH_LONG).show();
            return;
        }
        int iid = Integer.valueOf(id);

        FavoriteTv ft = new FavoriteTv();
        ft.setName(name);
        ft.setEmail(email);
        ft.setFavoriteTv(favoriteTv);
        ft.setId(iid);

        boolean result = favoriteDAO.updateFavorite(ft);
        if (result){
            Toast.makeText(this, "Update successfully", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Update failed", Toast.LENGTH_LONG).show();
        }
    }

    private void viewData(){
        String id = idEt.getText().toString();
        if (id.equals("")){
            viewAll();
        }else{
            FavoriteTv ft = favoriteDAO.selectById(Integer.valueOf(id));
            if (ft != null){
                List<FavoriteTv> result = new ArrayList<>();
                result.add(ft);
                Dialog mDialog = new ListViewDialog(this, result);
                mDialog.show();
                Window dialogWindow = mDialog.getWindow();
                WindowManager m = this.getWindowManager();
                Display d = m.getDefaultDisplay(); // 获取屏幕宽、高度
                WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
                p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.65，根据实际情况调整
                dialogWindow.setAttributes(p);
            }else{
                Toast.makeText(this, "Item doesn't exists !", Toast.LENGTH_LONG).show();
            }

        }
    }

    private void viewAll(){
        List<FavoriteTv> result = favoriteDAO.selectAll();
        Dialog mDialog = new ListViewDialog(this, result);
        mDialog.show();
        Window dialogWindow = mDialog.getWindow();
        WindowManager m = this.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高度
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.65，根据实际情况调整
        dialogWindow.setAttributes(p);
    }

    private void deleteData(){
        String id = idEt.getText().toString();
        if (id.equals("")){
            Toast.makeText(this, "Id can not be null !", Toast.LENGTH_LONG).show();
            return;
        }
        int iid = Integer.valueOf(id);
        boolean result = favoriteDAO.deleteFavorite(iid);
        if (result){
            Toast.makeText(this, "Delete successfully", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Delete failed", Toast.LENGTH_LONG).show();
        }
    }
}
