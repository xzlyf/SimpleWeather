package com.xz.simpleweather.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.xz.simpleweather.R;
import com.xz.simpleweather.entity.WeatherForecastData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ForecastWeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private WeatherForecastData weatherForecastData;
    private int TYPE_HEADER = 2333;

    public ForecastWeatherAdapter(WeatherForecastData weatherForecastData, Context context) {
        this.weatherForecastData = weatherForecastData;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==TYPE_HEADER){
            View view = LayoutInflater.from(context).inflate(R.layout.header_forecast_item,null);
            return new HeaderHolder(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.forecast_item, null);
            return new ViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return TYPE_HEADER;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  ViewHolder){
            ((ViewHolder)holder).forecast_date.setText(formatTime(weatherForecastData.getData().getForecasts().get(position-1).getDate()));
            ((ViewHolder)holder).forecast_dayOfWeek.setText(formatDate(weatherForecastData.getData().getForecasts().get(position-1).getDayOfWeek()));
            ((ViewHolder)holder).forecast_dayWeather.setText(weatherForecastData.getData().getForecasts().get(position-1).getDayWeather());
            ((ViewHolder)holder).forecast_dayTemp.setText(weatherForecastData.getData().getForecasts().get(position-1).getDayTemp());
            ((ViewHolder)holder).forecast_dayWindDirection.setText(weatherForecastData.getData().getForecasts().get(position-1).getDayWindDirection());
            ((ViewHolder)holder).forecast_nightWeather.setText(weatherForecastData.getData().getForecasts().get(position-1).getNightWeather());
            ((ViewHolder)holder).forecast_nightTemp.setText(weatherForecastData.getData().getForecasts().get(position-1).getNightTemp());
            ((ViewHolder)holder).forecast_nightWindPower.setText(weatherForecastData.getData().getForecasts().get(position-1).getNightWindDirection());

        }
         }
    /**
     * 格式化日期
     *
     * @param reportTime
     * @return
     */
    private String formatTime(String reportTime) {
        //2019-06-26 13:15:54
        try {
            //因为接收回来的日期是字符串格式要转
            SimpleDateFormat dateP = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateP.parse(reportTime);
            SimpleDateFormat dateF = new SimpleDateFormat("MM/dd");
            return dateF.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "null";
        }
    }

    /**
     * 格式化日期——星期
     * @param numTime
     * @return
     */
    private String formatDate(String numTime){
        if (numTime.equals("1")){
            return "星期一";
        }else if (numTime.equals("2")){
            return "星期二";
        }else if (numTime.equals("3")){
            return "星期三";
        }else if (numTime.equals("4")){
            return "星期四";
        }else if (numTime.equals("5")){
            return "星期五";
        }else if (numTime.equals("6")){
            return "星期六";
        }else if (numTime.equals("7")){
            return "星期七";
        }
        return "null";
    }
    @Override
    public int getItemCount() {
        return weatherForecastData.getData().getForecasts().size()+1    ;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView forecast_date;
        TextView forecast_dayOfWeek;
        TextView forecast_dayWeather;
        TextView forecast_dayTemp;
        TextView forecast_dayWindDirection;
        TextView forecast_nightWeather;
        TextView forecast_nightTemp;
        TextView forecast_nightWindPower;

        public ViewHolder(View itemView) {
            super(itemView);

            forecast_date = itemView.findViewById(R.id.forecast_date);
            forecast_dayOfWeek = itemView.findViewById(R.id.forecast_dayOfWeek);
            forecast_dayWeather = itemView.findViewById(R.id.forecast_dayWeather);
            forecast_dayTemp = itemView.findViewById(R.id.forecast_dayTemp);
            forecast_dayWindDirection = itemView.findViewById(R.id.forecast_dayWindDirection);
            forecast_nightWeather = itemView.findViewById(R.id.forecast_nightWeather);
            forecast_nightTemp = itemView.findViewById(R.id.forecast_nightTemp);
            forecast_nightWindPower = itemView.findViewById(R.id.forecast_nightWindPower);
        }
    }
    class HeaderHolder extends RecyclerView.ViewHolder{

        public HeaderHolder(View itemView) {
            super(itemView);
        }
    }
}
