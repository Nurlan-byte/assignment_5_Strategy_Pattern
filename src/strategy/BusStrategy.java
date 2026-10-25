package strategy;

/**
 * Route by city bus: includes waiting time at the stop.
 * Added later to show that a new strategy needs no changes in existing classes.
 */
public class BusStrategy implements RouteStrategy {

    private static final double SPEED_KMH = 25;
    private static final int WAITING_MINUTES = 10;

    @Override
    public String buildRoute(String from, String to, double distanceKm) {
        int minutes = (int) Math.round(distanceKm / SPEED_KMH * 60) + WAITING_MINUTES;
        return "Bus:   " + from + " -> " + to + " | city bus routes | "
                + minutes + " min (incl. " + WAITING_MINUTES + " min waiting)";
    }
}
