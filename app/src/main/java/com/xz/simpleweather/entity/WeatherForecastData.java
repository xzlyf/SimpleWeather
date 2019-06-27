package com.xz.simpleweather.entity;

import java.util.List;

public class WeatherForecastData {


    /**
     * code : 1
     * msg : 数据返回成功
     * data : {"address":"广东省 深圳市","cityCode":"440300","reportTime":"2019-06-27 16:15:51","forecasts":[{"date":"2019-06-27","dayOfWeek":"4","dayWeather":"多云","nightWeather":"多云","dayTemp":"32℃","nightTemp":"27℃","dayWindDirection":"无风向","nightWindDirection":"无风向","dayWindPower":"≤3级","nightWindPower":"≤3级"},{"date":"2019-06-28","dayOfWeek":"5","dayWeather":"阵雨","nightWeather":"阵雨","dayTemp":"32℃","nightTemp":"27℃","dayWindDirection":"无风向","nightWindDirection":"无风向","dayWindPower":"≤3级","nightWindPower":"≤3级"},{"date":"2019-06-29","dayOfWeek":"6","dayWeather":"阵雨","nightWeather":"阵雨","dayTemp":"32℃","nightTemp":"27℃","dayWindDirection":"无风向","nightWindDirection":"无风向","dayWindPower":"≤3级","nightWindPower":"≤3级"},{"date":"2019-06-30","dayOfWeek":"7","dayWeather":"多云","nightWeather":"雷阵雨","dayTemp":"31℃","nightTemp":"28℃","dayWindDirection":"无风向","nightWindDirection":"无风向","dayWindPower":"≤3级","nightWindPower":"≤3级"}]}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address : 广东省 深圳市
         * cityCode : 440300
         * reportTime : 2019-06-27 16:15:51
         * forecasts : [{"date":"2019-06-27","dayOfWeek":"4","dayWeather":"多云","nightWeather":"多云","dayTemp":"32℃","nightTemp":"27℃","dayWindDirection":"无风向","nightWindDirection":"无风向","dayWindPower":"≤3级","nightWindPower":"≤3级"},{"date":"2019-06-28","dayOfWeek":"5","dayWeather":"阵雨","nightWeather":"阵雨","dayTemp":"32℃","nightTemp":"27℃","dayWindDirection":"无风向","nightWindDirection":"无风向","dayWindPower":"≤3级","nightWindPower":"≤3级"},{"date":"2019-06-29","dayOfWeek":"6","dayWeather":"阵雨","nightWeather":"阵雨","dayTemp":"32℃","nightTemp":"27℃","dayWindDirection":"无风向","nightWindDirection":"无风向","dayWindPower":"≤3级","nightWindPower":"≤3级"},{"date":"2019-06-30","dayOfWeek":"7","dayWeather":"多云","nightWeather":"雷阵雨","dayTemp":"31℃","nightTemp":"28℃","dayWindDirection":"无风向","nightWindDirection":"无风向","dayWindPower":"≤3级","nightWindPower":"≤3级"}]
         */

        private String address;
        private String cityCode;
        private String reportTime;
        private List<ForecastsBean> forecasts;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public String getReportTime() {
            return reportTime;
        }

        public void setReportTime(String reportTime) {
            this.reportTime = reportTime;
        }

        public List<ForecastsBean> getForecasts() {
            return forecasts;
        }

        public void setForecasts(List<ForecastsBean> forecasts) {
            this.forecasts = forecasts;
        }

        public static class ForecastsBean {
            /**
             * date : 2019-06-27
             * dayOfWeek : 4
             * dayWeather : 多云
             * nightWeather : 多云
             * dayTemp : 32℃
             * nightTemp : 27℃
             * dayWindDirection : 无风向
             * nightWindDirection : 无风向
             * dayWindPower : ≤3级
             * nightWindPower : ≤3级
             */

            private String date;
            private String dayOfWeek;
            private String dayWeather;
            private String nightWeather;
            private String dayTemp;
            private String nightTemp;
            private String dayWindDirection;
            private String nightWindDirection;
            private String dayWindPower;
            private String nightWindPower;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getDayOfWeek() {
                return dayOfWeek;
            }

            public void setDayOfWeek(String dayOfWeek) {
                this.dayOfWeek = dayOfWeek;
            }

            public String getDayWeather() {
                return dayWeather;
            }

            public void setDayWeather(String dayWeather) {
                this.dayWeather = dayWeather;
            }

            public String getNightWeather() {
                return nightWeather;
            }

            public void setNightWeather(String nightWeather) {
                this.nightWeather = nightWeather;
            }

            public String getDayTemp() {
                return dayTemp;
            }

            public void setDayTemp(String dayTemp) {
                this.dayTemp = dayTemp;
            }

            public String getNightTemp() {
                return nightTemp;
            }

            public void setNightTemp(String nightTemp) {
                this.nightTemp = nightTemp;
            }

            public String getDayWindDirection() {
                return dayWindDirection;
            }

            public void setDayWindDirection(String dayWindDirection) {
                this.dayWindDirection = dayWindDirection;
            }

            public String getNightWindDirection() {
                return nightWindDirection;
            }

            public void setNightWindDirection(String nightWindDirection) {
                this.nightWindDirection = nightWindDirection;
            }

            public String getDayWindPower() {
                return dayWindPower;
            }

            public void setDayWindPower(String dayWindPower) {
                this.dayWindPower = dayWindPower;
            }

            public String getNightWindPower() {
                return nightWindPower;
            }

            public void setNightWindPower(String nightWindPower) {
                this.nightWindPower = nightWindPower;
            }
        }
    }
}
