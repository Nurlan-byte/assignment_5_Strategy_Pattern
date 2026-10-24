package strategy;

/** Route on foot: slow, but uses pedestrian paths and parks. */
public class WalkStrategy implements RouteStrategy {

    private static final double SPEED_KMH = 5;

    @Override
    public String buildRoute(String from, String to, double distanceKm) {
        int minutes = (int) Math.round(distanceKm / SPEED_KMH * 60);
        return "Walk:  " + from + " -> " + to + " | pedestrian paths | " + minutes + " min";
    }
}
