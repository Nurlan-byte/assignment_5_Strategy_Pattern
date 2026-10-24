package strategy;

/** Route by bicycle: uses bike lanes. */
public class CycleStrategy implements RouteStrategy {

    private static final double SPEED_KMH = 15;

    @Override
    public String buildRoute(String from, String to, double distanceKm) {
        int minutes = (int) Math.round(distanceKm / SPEED_KMH * 60);
        return "Cycle: " + from + " -> " + to + " | bike lanes | " + minutes + " min";
    }
}
