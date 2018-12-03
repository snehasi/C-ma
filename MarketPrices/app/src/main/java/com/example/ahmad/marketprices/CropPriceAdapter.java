package com.example.ahmad.marketprices;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CropPriceAdapter extends RecyclerView.Adapter<CropPriceAdapter.MyViewHolder> {

    private List<CropPrices> cropPriceList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, date, min,max,mode,location;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.CropPriceName);
            date = (TextView) view.findViewById(R.id.CropPriceDate);
            min = (TextView) view.findViewById(R.id.CropPriceMin);
            //max = (TextView) view.findViewById(R.id.CropPriceMax);
            mode = (TextView) view.findViewById(R.id.CropPriceMode);
            location=(TextView) view.findViewById(R.id.CropPriceLocation);
        }
    }
    public CropPriceAdapter(List<CropPrices> CropPriceList) {
        this.cropPriceList = CropPriceList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crop_price_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CropPriceAdapter.MyViewHolder myViewHolder, int i) {
        CropPrices c = cropPriceList.get(i);
        myViewHolder.name.setText(c.commodity);
        myViewHolder.date.setText("On "+c.arrival_date);
        myViewHolder.min.setText("Min Price : Rs"+c.min_price+"/100Kg");
        //myViewHolder.max.setText("Max Price : Rs"+c.max_price);
        myViewHolder.mode.setText("Common Price : Rs"+c.modal_price);
        myViewHolder.location.setText("In "+c.thestate+","+c.district+","+c.market);
    }

    @Override
    public int getItemCount() {
        return cropPriceList.size();
    }


}
