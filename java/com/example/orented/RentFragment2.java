package com.example.orented;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class RentFragment2 extends Fragment {
    DBAdapter dba;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rent2, container, false);

        dba = new DBAdapter(getActivity().getApplicationContext());

        TextView carName = view.findViewById(R.id.car_name);
        TextView carRentPrice = view.findViewById(R.id.car_rent_price);

        Bundle bundle = getArguments();
        String name = bundle.getString("cname");
        String price = bundle.getString("crentprice");

        carName.setText(name);
        carRentPrice.setText(price);

        ImageView carImage = view.findViewById(R.id.car_image);
        if(name.equals("Datsun Go")){
            int id = getResources().getIdentifier("datsungo", "drawable", getContext().getPackageName());
            carImage.setImageResource(id);
        }
        else if(name.equals("Daihatsu Ayla")){
            int id = getResources().getIdentifier("daihatsuayla", "drawable", getContext().getPackageName());
            carImage.setImageResource(id);
        }
        else if(name.equals("Honda Brio")){
            int id = getResources().getIdentifier("hondabrio", "drawable", getContext().getPackageName());
            carImage.setImageResource(id);
        }
        else if(name.equals("Daihatsu Sigra")){
            int id = getResources().getIdentifier("daihatsusigra", "drawable", getContext().getPackageName());
            carImage.setImageResource(id);
        }
        else if(name.equals("Suzuki Ertiga")){
            int id = getResources().getIdentifier("suzukiertiga", "drawable", getContext().getPackageName());
            carImage.setImageResource(id);
        }
        else if(name.equals("Toyota Avanza")){
            int id = getResources().getIdentifier("toyotaavanza", "drawable", getContext().getPackageName());
            carImage.setImageResource(id);
        }
        else if(name.equals("Toyota Innova")){
            int id = getResources().getIdentifier("toyotainnova", "drawable", getContext().getPackageName());
            carImage.setImageResource(id);
        }
        else if(name.equals("Toyota Hiace")){
            int id = getResources().getIdentifier("toyotahiace", "drawable", getContext().getPackageName());
            carImage.setImageResource(id);
        }

        Button makeScheduleButton = view.findViewById(R.id.make_schedule_button);
        makeScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sid = generateId();
                String pickUpDate = ((EditText) view.findViewById(R.id.inp_pick_up_date)).getText().toString();
                String pickUpTime = ((EditText) view.findViewById(R.id.inp_pick_up_time)).getText().toString();
                String pickUpLocation = ((EditText) view.findViewById(R.id.inp_pick_up_location)).getText().toString();
                String payment = ((EditText) view.findViewById(R.id.inp_payment)).getText().toString();
                int duration = Integer.parseInt(((EditText) view.findViewById(R.id.inp_duration)).getText().toString());

                String renterId = bundle.getString("cuserid");
                User user = null;
                for(User u : dba.getAllUser()){
                    if(u.getId().equals(renterId)){
                        user = u;
                    }
                }

                Vehicle vehicle = null;
                for(Vehicle ve : dba.getAllVehicle()){
                    if(ve.getFullName().equals(name)){
                        vehicle = ve;
                    }
                }

                String driverName = ((EditText) view.findViewById(R.id.inp_driver)).getText().toString();
                Driver driver = new Driver("DDDDD", driverName, 5.0f);

                Schedule newSchedule = new Schedule(sid, pickUpDate, pickUpTime, pickUpLocation, payment, duration, user, vehicle, driver);
                dba.insertSchedule(newSchedule);

                ArrayList<Schedule> allSchedules = dba.getAllSchedule();
                if(allSchedules != null){
                    for(Schedule s : allSchedules){
                        Log.d("DB Check", s.getId() + " - " + s.getPickUpDate() + " - " + s.getPickUpTime() + " - " + s.getPickUpLocation() + " - " + s.getDuration() + " - " + s.getPayment() + " - " + s.getDriver().getName() + " - " + s.getBorrower().getName() + " - " + s.getOwner().getName());
                    }
                }

                Toast.makeText(getContext(), "Schedule successfully created!", Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putString("carmanufacturer", "all");
                bundle.putInt("numberofseats", -1);
                bundle.putString("carcontroltype", "all");
                bundle.putInt("lowestprice", -1);
                bundle.putInt("highestprice", -1);

                Fragment homeFragment = new HomeFragment();
                homeFragment.setArguments(bundle);

                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();

            }
        });

        return view;
    }

    public String generateId(){
        String scheduleIdCandidate = "";
        do{
            scheduleIdCandidate = "";
            scheduleIdCandidate += "S";
            for(int i = 0; i < 3; i++){
                int n = (int) (0 + Math.random() * 10);
                scheduleIdCandidate += n;
            }
        }
        while(!idUnique(scheduleIdCandidate));

        return scheduleIdCandidate;
    }

    public boolean idUnique(String idCandidate){
        dba = new DBAdapter(getContext());

        ArrayList<Schedule> allSchedules = dba.getAllSchedule();

        for(Schedule s : allSchedules){
            if(s.equals(idCandidate)){
                return false;
            }
        }

        return true;
    }
}
