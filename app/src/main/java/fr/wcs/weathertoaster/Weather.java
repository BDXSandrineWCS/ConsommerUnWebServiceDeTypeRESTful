package fr.wcs.weathertoaster;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Weather {

    private String date;
    private String description;

    public Weather() {
        super();
    }

    public Weather(String date, String description) {
        super();
        this.date = date;
        this.description = description;
    }

    public Weather(long date, String description) {
        super();
        this.date = unixToStringDate(date);
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String unixToStringDate(long date){
        return new SimpleDateFormat("dd/MM/yyyy-HH:mm").format(new Date(date*1000));
    }
}
