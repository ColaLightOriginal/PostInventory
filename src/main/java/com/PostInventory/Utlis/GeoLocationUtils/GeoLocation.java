package com.PostInventory.Utlis.GeoLocationUtils;

import java.util.LinkedList;
import java.util.List;

public class GeoLocation {


    private double radLatitude;
    private double radLongitude;

    private double latitude;
    private double longitude;

    private double distance;
    private static final double MIN_LAT = Math.toRadians(-90d);
    private static final double MAX_LAT = Math.toRadians(90d);
    private static final double MIN_LON = Math.toRadians(-180d);
    private static final double MAX_LON = Math.toRadians(180d);

    private static final double RADIUS = 6371;


    public GeoLocation(double latitude, double longitude, double distance){
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.radLatitude = Math.toRadians(latitude);
        this.radLongitude = Math.toRadians(longitude);

        checkBounds(this.radLatitude, MIN_LAT, MAX_LAT, this.radLongitude, MIN_LON, MAX_LON);
    }

    private void checkBounds(double radLatitude, double minLatitude, double maxLatitude,
                             double radLongitude, double minLongitude, double maxLongitude){
        if(radLatitude < minLatitude || radLatitude > maxLatitude ||
                radLongitude < minLongitude || radLongitude > maxLongitude)
            throw new IllegalArgumentException();
    }

    public List<GeoLocation> boundingCoordinates(double distance){

        List<GeoLocation> result = new LinkedList<>();

        if(RADIUS < 0 || distance <0) throw new IllegalArgumentException();

        double angularDistance = distance / RADIUS;

        double minLatitude = this.radLatitude - angularDistance;
        double maxLatitude = this.radLatitude + angularDistance;
        double minLongitude;
        double maxLongitude;

        if (minLatitude > MIN_LAT && maxLatitude < MAX_LAT) {
            double deltaLon = Math.asin(Math.sin(angularDistance) /
                    Math.cos(this.radLatitude));
            minLongitude = radLongitude - deltaLon;
            if (minLongitude < MIN_LON) minLongitude += 2d * Math.PI;
            maxLongitude = radLongitude + deltaLon;
            if (maxLongitude > MAX_LON) maxLongitude -= 2d * Math.PI;
        } else{
            minLatitude = Math.max(minLatitude, MIN_LAT);
            maxLatitude = Math.min(maxLatitude, MAX_LAT);
            minLongitude = MIN_LON;
            maxLongitude = MAX_LON;
        }

        result.add(new GeoLocation(minLatitude, minLongitude, distance ));
        result.add(new GeoLocation(maxLatitude, maxLongitude, distance));

        return result;
    }

    public boolean meridian180WithDistance(){
        List<GeoLocation> boundingCoordinatesList = this.boundingCoordinates(this.distance);
        return boundingCoordinatesList.get(0).getRadLongitude()
                > boundingCoordinatesList.get(1).getRadLatitude();
    }

    public double getRadLatitude() {
        return radLatitude;
    }

    public void setRadLatitude(double radLatitude) {
        this.radLatitude = radLatitude;
    }

    public double getRadLongitude() {
        return radLongitude;
    }

    public void setRadLongitude(double radLongitude) {
        this.radLongitude = radLongitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public static double getRADIUS() {
        return RADIUS;
    }

}
