package com.example.orented;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class HistoryDisplayAdapter extends RecyclerView.Adapter<HistoryDisplayAdapter.ViewHolder>{

    String[] pickUpDates, pickUpTimes, pickUpLocations, carNames, driverNames;
    int[] rentPrices;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvPickUpDate, tvPickUpTime, tvPickUpLocation, tvCarName, tvDriverName, tvRentPrice;

        public ViewHolder(View view) {
            super(view);

            tvPickUpDate = (TextView) view.findViewById(R.id.pick_up_date);
            tvPickUpTime = (TextView) view.findViewById(R.id.pick_up_time);
            tvPickUpLocation = (TextView) view.findViewById(R.id.pick_up_location);
            tvCarName = (TextView) view.findViewById(R.id.car_name);
            tvDriverName = (TextView) view.findViewById(R.id.driver_name);
            tvRentPrice = (TextView) view.findViewById(R.id.rent_price);

        }

        public TextView getTvPickUpDate() {
            return tvPickUpDate;
        }

        public TextView getTvPickUpTime() {
            return tvPickUpTime;
        }

        public TextView getTvPickUpLocation() {
            return tvPickUpLocation;
        }

        public TextView getTvCarName() {
            return tvCarName;
        }

        public TextView getTvDriverName() {
            return tvDriverName;
        }

        public TextView getTvRentPrice() {
            return tvRentPrice;
        }
    }

    public HistoryDisplayAdapter(String[] pickUpDates, String[] pickUpTimes, String[] pickUpLocations, String[] carNames, String[] driverNames, int[] rentPrices) {
        this.pickUpDates = pickUpDates;
        this.pickUpTimes = pickUpTimes;
        this.pickUpLocations = pickUpLocations;
        this.carNames = carNames;
        this.driverNames = driverNames;
        this.rentPrices = rentPrices;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_recyclerview_history, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTvPickUpDate().setText(pickUpDates[position] + ", ");
        viewHolder.getTvPickUpTime().setText(pickUpTimes[position]);
        viewHolder.getTvPickUpLocation().setText(pickUpLocations[position]);
        viewHolder.getTvCarName().setText(carNames[position]);
        viewHolder.getTvDriverName().setText("With " + driverNames[position]);
        viewHolder.getTvRentPrice().setText(String.valueOf((int) rentPrices[position] / 1000) + "K");
    }

    @Override
    public int getItemCount() {
        return carNames.length;
    }
}
