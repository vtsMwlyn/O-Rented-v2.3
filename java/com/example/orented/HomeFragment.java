package com.example.orented;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    String selectedCar = "Car Name";
    DBAdapter dba;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        dba = new DBAdapter(getActivity().getApplicationContext());

        RecyclerView rv = view.findViewById(R.id.home_car_display);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(llm);

        Bundle bundle = getArguments();

        String carManufacturer = bundle.getString("carmanufacturer");
        int numberOfSeats = bundle.getInt("numberofseats");
        String carControlType = bundle.getString("carcontroltype");
        int lowestPrice = bundle.getInt("lowestprice");
        int highestPrice = bundle.getInt("highestprice");

        ArrayList<Vehicle> allVehicles = dba.getAllVehicle();
        ArrayList<Vehicle> filteredVehicles = new ArrayList<>();

        for(Vehicle v : allVehicles){
            boolean manufacturerFilter, seatFilter, controlFilter, priceFilter;

            if(carManufacturer.equals("all")){
                manufacturerFilter = true;
            }
            else {
                manufacturerFilter = v.getManufacturer().equals(carManufacturer);
            }

            if(numberOfSeats == -1){
                seatFilter = true;
            }
            else {
                seatFilter = v.getSeat() == numberOfSeats;
            }

            if(carControlType.equals("all")){
                controlFilter = true;
            }
            else {
                controlFilter = v.getControl().equals(carControlType);
            }

            if(lowestPrice == -1 && highestPrice == -1){
                priceFilter = true;
            }
            else if(lowestPrice == -1 && highestPrice != -1){
                priceFilter = v.getRentPrice() >= 0 && v.getRentPrice() <= highestPrice;
            }
            else if(lowestPrice != -1 && highestPrice == -1){
                priceFilter = v.getRentPrice() >= lowestPrice;
            }
            else {
                priceFilter = v.getRentPrice() >= lowestPrice && v.getRentPrice() <= highestPrice;
            }

            if(manufacturerFilter && seatFilter && controlFilter && priceFilter){
                filteredVehicles.add(v);
            }

        }

        int n = filteredVehicles.size();
        String[] carNames = new String[n];
        int[] carImages = new int[n];

        for(int i = 0; i < n; i++){
            carNames[i] = filteredVehicles.get(i).getFullName();

            if(carNames[i].equals("Datsun Go")){
                carImages[i] = R.drawable.datsungo;
            }
            else if(carNames[i].equals("Daihatsu Ayla")){
                carImages[i] = R.drawable.daihatsuayla;
            }
            else if(carNames[i].equals("Honda Brio")){
                carImages[i] = R.drawable.hondabrio;
            }
            else if(carNames[i].equals("Daihatsu Sigra")){
                carImages[i] = R.drawable.daihatsusigra;
            }
            else if(carNames[i].equals("Toyota Avanza")){
                carImages[i] = R.drawable.toyotaavanza;
            }
            else if(carNames[i].equals("Suzuki Ertiga")){
                carImages[i] = R.drawable.suzukiertiga;
            }
            else if(carNames[i].equals("Toyota Innova")){
                carImages[i] = R.drawable.toyotainnova;
            }
            else if(carNames[i].equals("Toyota Hiace")){
                carImages[i] = R.drawable.toyotahiace;
            }
        }

        HomeCarDisplayAdapter adaptus = new HomeCarDisplayAdapter(carNames, carImages, getParentFragmentManager());
        rv.setAdapter(adaptus);

        Button filterButton = view.findViewById(R.id.filter_button);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new CarFilterFragment()).commit();
            }
        });

        return view;
    }
}
