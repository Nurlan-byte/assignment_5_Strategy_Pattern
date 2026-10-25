import context.Navigator;
import strategy.CarStrategy;
import strategy.CycleStrategy;
import strategy.RouteStrategy;
import strategy.WalkStrategy;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Client: chooses which strategy the Navigator should use.
 * Strategies are looked up by name in a map, so there is no if/switch on the transport type.
 */
public class Main {

    private static final String FROM = "AITU";
    private static final String TO = "Khan Shatyr";
    private static final double DEMO_DISTANCE_KM = 8;

    public static void main(String[] args) {
        Map<String, RouteStrategy> strategies = createStrategies();
        runDemo(strategies);
    }

    /** To add a new transport mode: write a new class and add one line here. */
    private static Map<String, RouteStrategy> createStrategies() {
        Map<String, RouteStrategy> strategies = new LinkedHashMap<>();
        strategies.put("car", new CarStrategy());
        strategies.put("walk", new WalkStrategy());
        strategies.put("cycle", new CycleStrategy());
        return strategies;
    }

    /** One Navigator object, its strategy is replaced at runtime. */
    private static void runDemo(Map<String, RouteStrategy> strategies) {
        System.out.println("=== Demo: same Navigator, strategy switched at runtime ===");
        Navigator navigator = new Navigator(new CarStrategy());
        for (RouteStrategy strategy : strategies.values()) {
            navigator.setStrategy(strategy);
            System.out.println(navigator.planRoute(FROM, TO, DEMO_DISTANCE_KM));
        }
    }
}
