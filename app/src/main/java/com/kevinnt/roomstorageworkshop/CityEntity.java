package com.kevinnt.roomstorageworkshop;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "cities")
public class CityEntity implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "city_name")
    public String name;

    @ColumnInfo(name = "city_image")
    public String image;

    public CityEntity(){
    }

    protected CityEntity(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CityEntity> CREATOR = new Creator<CityEntity>() {
        @Override
        public CityEntity createFromParcel(Parcel in) {
            return new CityEntity(in);
        }

        @Override
        public CityEntity[] newArray(int size) {
            return new CityEntity[size];
        }
    };
}
