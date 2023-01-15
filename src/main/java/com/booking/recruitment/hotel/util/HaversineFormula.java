package com.booking.recruitment.hotel.util;

public class HaversineFormula {
	public static final int radius_of_earth = 6371;
	public static double calculateDistance(double startLatitude, double startLongitude,double endLatitude,double endLongitude) {
		double distanceLatitude=Math.toRadians(endLatitude-startLatitude);
		double distanceLongitude=Math.toRadians(endLongitude-startLongitude);
		startLatitude=Math.toRadians(startLatitude);
		endLatitude=Math.toRadians(endLatitude);
		double x=haversine(distanceLatitude)+Math.cos(startLatitude)*Math.cos(endLatitude)*haversine(distanceLongitude);
		double y=2*Math.atan2(Math.sqrt(x), Math.sqrt(1-x));
		return radius_of_earth*y;
	}
	public static double haversine(double x) {
		return Math.pow(Math.sin(x/2), 2);
	}
}
