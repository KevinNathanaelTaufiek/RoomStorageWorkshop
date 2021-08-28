package com.kevinnt.roomstorageworkshop;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CityDao {

    @Query("SELECT * FROM cities")
    List<CityEntity> getAllCities();

    @Query("SELECT * FROM cities WHERE city_name LIKE '%'+:name+'%'")
    List<CityEntity> getCitiesByName(String name);

    @Insert
    void insertCity(CityEntity cityEntity);

    @Delete
    void deleteCity(CityEntity cityEntity);

    @Update
    void updateCity(CityEntity cityEntity);
}
