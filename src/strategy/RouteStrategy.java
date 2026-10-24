package strategy;

/**
 * Strategy interface: one way of building a route.
 * Every transport mode implements this single method.
 */
public interface RouteStrategy {

    String buildRoute(String from, String to, double distanceKm);
}
