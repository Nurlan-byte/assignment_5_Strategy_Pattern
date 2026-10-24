package context;

import strategy.RouteStrategy;

/**
 * Context: holds a RouteStrategy and delegates route building to it.
 * It knows only the interface, never the concrete strategies.
 */
public class Navigator {

    private RouteStrategy strategy;

    public Navigator(RouteStrategy strategy) {
        setStrategy(strategy);
    }

    /** Switches the algorithm at runtime. */
    public void setStrategy(RouteStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Strategy must not be null");
        }
        this.strategy = strategy;
    }

    public String planRoute(String from, String to, double distanceKm) {
        if (!Double.isFinite(distanceKm) || distanceKm <= 0) {
            throw new IllegalArgumentException("Distance must be a positive number, got: " + distanceKm);
        }
        return strategy.buildRoute(from, to, distanceKm);
    }
}
