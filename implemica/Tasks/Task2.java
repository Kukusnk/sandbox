import java.util.*;

/**
 * The program reads from stdin information about the number of cities,
 * names of each city, number of direct routes to neighboring cities and cost of these routes.
 * ...
 * NAME [city name]
 * p [the number of neighbors of city NAME]
 * nr cost [nr - index of a city connected to NAME][cost - the transportation cost].
 * ...
 * And computes the minimum cost route between pairs of specified cities using Dijkstra's algorithm.
 */
public class Task2 {
    /**
     * The maximum allowable cost, constant by condition.
     */
    final static int MAX_COST = 200000;

    public static void main(String[] args) {
        // Create a Scanner object to read data from the stdin
        Scanner scanner = new Scanner(System.in);

        // Initializing the number of test
        int numberOfTests;
        numberOfTests = scanner.nextInt();

        // Start testing
        while ((numberOfTests--) > 0) {
            // Initializing the first index of city
            int index = 1;

            // Initializing the number of cities
            int numberOfCity;
            numberOfCity = scanner.nextInt();
            scanner.skip("\n");

            // Initialization of a city map where the key is the city index
            // and its value is a map of routes and their costs.
            Map<Integer, Map<Integer, Integer>> cityMap = new HashMap<>();

            // Initialization of the index map,
            // where the key is the city name and the value is its index.
            Map<String, Integer> cityIndexes = new HashMap<>();

            // Entering data for each city
            while ((numberOfCity--) > 0) {
                // Initializing the city name and number of the neighbors for it city
                String name; //start index = 1
                int numberOfNeighbors;
                name = scanner.nextLine();
                numberOfNeighbors = scanner.nextInt();
                scanner.skip("\n");

                // Inserting a new pair into the city indexes
                cityIndexes.put(name, index);
                // Initialization of route map to neighboring cities
                Map<Integer, Integer> routeMapToNeighboringCities = new HashMap<>();

                // Filling in the map of routes to neighboring cities, their indices and costs
                while ((numberOfNeighbors--) > 0) {
                    String[] attitude = scanner.nextLine().split(" ");
                    int i = Integer.parseInt(attitude[0]);
                    int cost = Integer.parseInt(attitude[1]);
                    routeMapToNeighboringCities.put(i, cost);
                }
                // Adding to the map of cities,
                // the city and a map with direct routes to the city from other cities and their costs
                cityMap.put(index, routeMapToNeighboringCities);
                index++;
            }

            // Initialization of the number of routs to be found
            int numberOfPathToFind = scanner.nextInt();
            scanner.skip("\n");

            /*Initialization of the array where the results of the search
             for the minimum cost of all pairs will be stored */
            int[] result = new int[numberOfPathToFind];

            //Enter pairs of cities between which you want to find the cost of the shortest route
            for (int i = 0; i < numberOfPathToFind; ++i) {
                String searchPath = scanner.nextLine();
                String[] strings = searchPath.split(" ");

                //To identify the indices of each member of the pair
                int indexFrom = cityIndexes.get(strings[0]);
                int indexTo = cityIndexes.get(strings[1]);

                // Calling the method of shortest path search where indexFrom and indexTo
                // are the pair between which the route is searched
                // and saving the result to the results array
                result[i] = getMinTransportCost(cityMap, indexFrom, indexTo);
            }
            // Closes the scanner object
            scanner.close();

            // Output search results to stdout
            for (int res : result) {
                System.out.println(res);
            }
            System.out.println();

        }
    }

    /**
     * The method calculates, using Dijkstri's algorithm, the minimum transportation cost
     *
     * @param cityMap  - map of cities and the costs of their direct routes to other cities.
     * @param fromCity - Starting point (city) for calculating values.
     * @param toCity   - Ending point (city) for calculating values.
     * @return int returns the minimum cost between fromCity and toCity.
     */
    public static int getMinTransportCost(Map<Integer, Map<Integer, Integer>> cityMap,
                                          Integer fromCity, Integer toCity) {
        // Initialization of costs
        Map<Integer, Integer> costs = new HashMap<>();
        for (Integer index : cityMap.keySet()) {
            costs.put(index, MAX_COST);
        }
        costs.put(fromCity, 0);

        // Queue with priority for index processing using cost sorting
        PriorityQueue<Route> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        queue.add(new Route(fromCity, 0));

        while (!queue.isEmpty()) {
            Route current = queue.poll();
            Integer currentIndex = current.index;
            int currentCost = current.cost;

            // If the current value is greater than the recorded value, skip it.
            if (currentCost > costs.get(currentIndex)) {
                continue;
            }

            // Updating the cost to routes
            Map<Integer, Integer> neighbors = cityMap.get(currentIndex);
            for (Map.Entry<Integer, Integer> neighbor : neighbors.entrySet()) {
                Integer neighborIndex = neighbor.getKey();
                int costValue = neighbor.getValue();
                int totalCost = currentCost + costValue;

                if (totalCost < costs.get(neighborIndex)) {
                    costs.put(neighborIndex, totalCost);
                    queue.add(new Route(neighborIndex, totalCost));
                }
            }
        }

        // Finding the cost of the relevant route
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : costs.entrySet()) {
            if (entry.getKey() == toCity) {
                result = entry.getValue();
                break;
            }
        }
        return result;
    }

    /**
     * Internal static class for storing the cost of routes by index
     */
    static class Route {
        /**
         * City index with direct route
         */
        int index;
        /**
         * The cost of a city route
         */
        int cost;

        /**
         * Creates a route with a specified cost
         *
         * @param index - The index of the city to which the route leads.
         * @param cost  - Cost of transportation route to the city.
         */
        Route(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }
}
