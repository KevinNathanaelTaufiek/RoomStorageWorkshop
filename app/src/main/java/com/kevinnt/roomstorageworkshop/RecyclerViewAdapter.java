package com.kevinnt.roomstorageworkshop;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<CityEntity> cities = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setCities(ArrayList<CityEntity> cities) {
        this.cities = cities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_city_name.setText(cities.get(position).name);
        Glide.with(context).asBitmap().load(cities.get(position).image).into(holder.iv_city_image);

        holder.btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddActivity.class);
                intent.putExtra("city", (Parcelable) cities.get(position));
                intent.putExtra("flag", true);
//                Log.d("Update", intent.getParcelableExtra("city").toString());
                context.startActivity(intent);
            }
        });

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = AppDatabase.getInstance(context);
                db.cityDao().deleteCity(cities.get(position));

                cities.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,cities.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_city_name;
        private ImageView iv_city_image;
        private Button btn_update, btn_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_city_name = itemView.findViewById(R.id.tv_city_name);
            iv_city_image = itemView.findViewById(R.id.iv_city_image);
            btn_update = itemView.findViewById(R.id.btn_update);
            btn_delete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
