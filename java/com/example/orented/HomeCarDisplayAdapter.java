package com.example.orented;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeCarDisplayAdapter extends RecyclerView.Adapter<HomeCarDisplayAdapter.ViewHolder>{

    String[] carNames;
    int[] carImages;

    String selectedCar = "Car Name";
    FragmentManager fm;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView imageView;
        private final Button button;

        public ViewHolder(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.car_name);
            imageView = (ImageView) view.findViewById(R.id.car_image);
            button = (Button) view.findViewById(R.id.rent_car_button);
        }

        public TextView getTextView() {
            return textView;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public Button getButton(){
            return button;
        }
    }

    public HomeCarDisplayAdapter(String[] carNames, int[] carImages, FragmentManager fm) {
        this.carNames = carNames;
        this.carImages = carImages;
        this.fm = fm;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_recyclerview_homecardisplay, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(carNames[position]);
        viewHolder.getImageView().setImageResource(carImages[position]);
        viewHolder.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCar = carNames[viewHolder.getAdapterPosition()];
                Log.d("Testus: ", selectedCar);
                Bundle bundle = new Bundle();
                bundle.putString("selected", selectedCar);
                fm.setFragmentResult("reqKey", bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carNames.length;
    }

}