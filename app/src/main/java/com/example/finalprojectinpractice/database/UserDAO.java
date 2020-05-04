package com.example.finalprojectinpractice.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDAO {

    @Insert
    long insertUser(UserModel userModel);

    @Query("SELECT * FROM data_user WHERE email = :email and password = :password")
    UserModel cekUserLogin (String email, String password);

    @Query("DELETE FROM data_user WHERE email = :email")
    void deleteDataUser (String email);

    @Query("UPDATE data_user SET email = :email, username = :username, password = :password WHERE id= :id")
    void updateDataUser(String email, String username, String password, int id);
}
