package com.example.orented;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    DBAdapter dba;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        dba = new DBAdapter(getActivity().getApplicationContext());

        RecyclerView rv = view.findViewById(R.id.history_car_and_driver);
        RecyclerView rv2 = view.findViewById(R.id.history_car_only);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        LinearLayoutManager llm2 = new LinearLayoutManager(this.getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        llm2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(llm);
        rv2.setLayoutManager(llm2);

        ArrayList<Schedule> allSchedules = dba.getAllSchedule();
        ArrayList<Schedule> allSchedulesCarAndDriver = new ArrayList<>();
        ArrayList<Schedule> allSchedulesCarOnly = new ArrayList<>();

        for(Schedule s : allSchedules){
            if(s.getDriver().getName().equals("No Driver")){
                allSchedulesCarOnly.add(s);
            }
            else {
                allSchedulesCarAndDriver.add(s);
            }
        }

        int n1 = allSchedulesCarAndDriver.size();
        int n2 = allSchedulesCarOnly.size();

        String[] pickUpDatesCD = new String[n1];
        String[] pickUpTimesCD = new String[n1];
        String[] pickUpLocationsCD = new String[n1];
        String[] carNamesCD = new String[n1];
        String[] driverNamesCD = new String[n1];
        int[] rentPricesCD = new int[n1];

        String[] pickUpDatesCO = new String[n2];
        String[] pickUpTimesCO = new String[n2];
        String[] pickUpLocationsCO = new String[n2];
        String[] carNamesCO = new String[n2];
        String[] driverNamesCO = new String[n2];
        int[] rentPricesCO = new int[n2];

        for(int i = 0; i < n1; i++){
            Schedule s = allSchedulesCarAndDriver.get(i);

            pickUpDatesCD[i] = s.getPickUpDate();
            pickUpTimesCD[i] = s.getPickUpTime();
            pickUpLocationsCD[i] = s.getPickUpLocation();
            carNamesCD[i] = s.getVehicle().getFullName();
            driverNamesCD[i] = s.getDriver().getName();
            rentPricesCD[i] = s.getDuration() * s.getVehicle().getRentPrice();
        }

        for(int i = 0; i < n2; i++){
            Schedule s = allSchedulesCarOnly.get(i);

            pickUpDatesCO[i] = s.getPickUpDate();
            pickUpTimesCO[i] = s.getPickUpTime();
            pickUpLocationsCO[i] = s.getPickUpLocation();
            carNamesCO[i] = s.getVehicle().getFullName();
            driverNamesCO[i] = s.getDriver().getName();
            rentPricesCO[i] = s.getDuration() * s.getVehicle().getRentPrice();
        }

        HistoryDisplayAdapter adaptus = new HistoryDisplayAdapter(pickUpDatesCD, pickUpTimesCD, pickUpLocationsCD, carNamesCD, driverNamesCD, rentPricesCD);
        rv.setAdapter(adaptus);

        HistoryDisplayAdapter adaptus2 = new HistoryDisplayAdapter(pickUpDatesCO, pickUpTimesCO, pickUpLocationsCO, carNamesCO, driverNamesCO, rentPricesCO);
        rv2.setAdapter(adaptus2);

        return view;
    }
}
