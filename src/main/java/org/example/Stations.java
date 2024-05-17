package org.example;

public class Stations {
    public String station_name;
    public String depth;

    public Stations() {
        this.station_name = "station_name";
        this.depth = "depth";
    }

    public Stations(String station_name, String depth) {
        this.station_name = station_name;
        this.depth = depth;
    }

    public String getStation_name() {
        return station_name;
    }

    public String getDepth() {
        return depth;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "Stations{" +
                "station_name='" + station_name + '\'' +
                ", depth='" + depth + '\'' +
                '}';
    }
}
