package com.example.orented;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class CarFilterFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_filter, container, false);

        Button applyFilterBtn = view.findViewById(R.id.apply_filter_button);
        applyFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etCarManufacturer = view.findViewById(R.id.car_manufacturer);
                EditText etNumberOfSeats = view.findViewById(R.id.number_of_seats);
                EditText etCarControlType = view.findViewById(R.id.car_control_type);
                EditText etLowestPrice = view.findViewById(R.id.lowest_price);
                EditText etHighestPrice = view.findViewById(R.id.highest_price);

                String carManufacturer = etCarManufacturer.getText().toString();
                String numberOfSeatsStr = etNumberOfSeats.getText().toString();
                String carControlType = etCarControlType.getText().toString();
                String lowestPriceStr = etLowestPrice.getText().toString();
                String highestPriceStr = etHighestPrice.getText().toString();

                int numberOfSeats, lowestPrice, highestPrice;

                if(carManufacturer.equals("")){
                    carManufacturer = "all";
                }

                if(numberOfSeatsStr.equals("")){
                    numberOfSeats = -1;
                } else {
                    numberOfSeats = Integer.parseInt(numberOfSeatsStr);
                }

                if(carControlType.equals("")){
                    carControlType = "all";
                }

                if(lowestPriceStr.equals("")){
                    lowestPrice = -1;
                } else {
                    lowestPrice = Integer.parseInt(lowestPriceStr);
                }

                if(highestPriceStr.equals("")){
                    highestPrice = -1;
                } else {
                    highestPrice = Integer.parseInt(highestPriceStr);
                }

                Bundle bundle = new Bundle();
                bundle.putString("carmanufacturer", carManufacturer);
                bundle.putInt("numberofseats", numberOfSeats);
                bundle.putString("carcontroltype", carControlType);
                bundle.putInt("lowestprice", lowestPrice);
                bundle.putInt("highestprice", highestPrice);

                Fragment homeFragmentus = new HomeFragment();
                homeFragmentus.setArguments(bundle);

                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragmentus).commit();
            }
        });

        Button defaultFilterBtn = view.findViewById(R.id.default_filter_button);
        defaultFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("carmanufacturer", "all");
                bundle.putInt("numberofseats", -1);
                bundle.putString("carcontroltype", "all");
                bundle.putInt("lowestprice", -1);
                bundle.putInt("highestprice", -1);

                Fragment homeFragmentus = new HomeFragment();
                homeFragmentus.setArguments(bundle);

                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragmentus).commit();
            }
        });

        return view;
    }
}