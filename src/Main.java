import context.Navigator;
import strategy.BusStrategy;
import strategy.CarStrategy;
import strategy.CycleStrategy;
import strategy.RouteStrategy;
import strategy.WalkStrategy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

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
        runInteractive(strategies);
    }

    /** To add a new transport mode: write a new class and add one line here. */
    private static Map<String, RouteStrategy> createStrategies() {
        Map<String, RouteStrategy> strategies = new LinkedHashMap<>();
        strategies.put("car", new CarStrategy());
        strategies.put("walk", new WalkStrategy());
        strategies.put("cycle", new CycleStrategy());
        strategies.put("bus", new BusStrategy());
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

    private static void runInteractive(Map<String, RouteStrategy> strategies) {
        System.out.println();
        System.out.println("=== Interactive mode ===");
        Scanner scanner = new Scanner(System.in);
        Navigator navigator = new Navigator(new CarStrategy());

        while (true) {
            System.out.print("Transport " + strategies.keySet() + " or 'exit': ");
            if (!scanner.hasNextLine()) {
                return;
            }
            String mode = scanner.nextLine().trim().toLowerCase();
            if (mode.equals("exit")) {
                System.out.println("Bye!");
                return;
            }

            RouteStrategy strategy = strategies.get(mode);
            if (strategy == null) {
                System.out.println("Unknown transport: '" + mode + "'. Try again.");
                continue;
            }

            Double distanceKm = readDistance(scanner);
            if (distanceKm == null) {
                return;
            }
            navigator.setStrategy(strategy);
            System.out.println(navigator.planRoute(FROM, TO, distanceKm));
        }
    }

    /** Asks again until the user types a positive number. Returns null if input ended. */
    private static Double readDistance(Scanner scanner) {
        while (true) {
            System.out.print("Distance in km: ");
            if (!scanner.hasNextLine()) {
                return null;
            }
            String input = scanner.nextLine().trim();
            try {
                double distanceKm = Double.parseDouble(input);
                if (Double.isFinite(distanceKm) && distanceKm > 0) {
                    return distanceKm;
                }
                System.out.println("Distance must be greater than 0, got: " + input);
            } catch (NumberFormatException e) {
                System.out.println("Not a number: '" + input + "'. Try again.");
            }
        }
    }
}
