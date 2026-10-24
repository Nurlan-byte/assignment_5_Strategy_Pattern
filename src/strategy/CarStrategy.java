package strategy;

/** Route by car: fast on main roads, plus time to park. */
public class CarStrategy implements RouteStrategy {

    private static final double SPEED_KMH = 60;
    private static final int PARKING_MINUTES = 5;

    @Override
    public String buildRoute(String from, String to, double distanceKm) {
        int minutes = (int) Math.round(distanceKm / SPEED_KMH * 60) + PARKING_MINUTES;
        return "Car:   " + from + " -> " + to + " | main roads | "
                + minutes + " min (incl. " + PARKING_MINUTES + " min parking)";
    }
}
