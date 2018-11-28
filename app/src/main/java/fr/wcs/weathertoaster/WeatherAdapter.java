package fr.wcs.weathertoaster;

import android.content.Context;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WeatherAdapter extends ArrayAdapter<Weather> {


    public WeatherAdapter( Context context,  @NonNull ArrayList<Weather> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Weather weather=getItem(position);
        if ( null == convertView){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }
        TextView date= convertView.findViewById(R.id.itemWeatherDateView);
        TextView description= convertView.findViewById(R.id.itemWeatherDescriptionView);
        date.setTextColor(Color.BLACK); //for my phone
        description.setTextColor(Color.BLACK); //for my phone
        date.setText(weather.getDate());
        description.setText(weather.getDescription());
        return convertView;
    }
}
