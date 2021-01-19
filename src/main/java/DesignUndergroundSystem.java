import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/design-underground-system/ Premium
 * <pre>
 * Implement the UndergroundSystem class:
 *
 * void checkIn(int id, string stationName, int t)
 * A customer with a card id equal to id, gets in the station stationName at time t.
 * A customer can only be checked into one place at a time.
 *
 * void checkOut(int id, string stationName, int t)
 * A customer with a card id equal to id, gets out from the station stationName at time t.
 *
 * double getAverageTime(string startStation, string endStation)
 * Returns the average time to travel between the startStation and the endStation.
 * The average time is computed from all the previous traveling from startStation to endStation that happened directly.
 * Call to getAverageTime is always valid.
 *
 * You can assume all calls to checkIn and checkOut methods are consistent. If a customer gets in at time t1 at some station, they get out at time t2 with t2 > t1. All events happen in chronological order.
 *
 * Input
 * ["UndergroundSystem","checkIn","checkIn","checkIn","checkOut","checkOut","checkOut","getAverageTime","getAverageTime","checkIn","getAverageTime","checkOut","getAverageTime"]
 * [[],[45,"Leyton",3],[32,"Paradise",8],[27,"Leyton",10],[45,"Waterloo",15],[27,"Waterloo",20],[32,"Cambridge",22],["Paradise","Cambridge"],["Leyton","Waterloo"],[10,"Leyton",24],["Leyton","Waterloo"],[10,"Waterloo",38],["Leyton","Waterloo"]]
 *
 * Output
 * [null,null,null,null,null,null,null,14.00000,11.00000,null,11.00000,null,12.00000]
 *
 * Explanation
 * UndergroundSystem undergroundSystem = new UndergroundSystem();
 * undergroundSystem.checkIn(45, "Leyton", 3);
 * undergroundSystem.checkIn(32, "Paradise", 8);
 * undergroundSystem.checkIn(27, "Leyton", 10);
 * undergroundSystem.checkOut(45, "Waterloo", 15);
 * undergroundSystem.checkOut(27, "Waterloo", 20);
 * undergroundSystem.checkOut(32, "Cambridge", 22);
 * undergroundSystem.getAverageTime("Paradise", "Cambridge");       // return 14.00000. There was only one travel from "Paradise" (at time 8) to "Cambridge" (at time 22)
 * undergroundSystem.getAverageTime("Leyton", "Waterloo");          // return 11.00000. There were two travels from "Leyton" to "Waterloo", a customer with id=45 from time=3 to time=15 and a customer with id=27 from time=10 to time=20. So the average time is ( (15-3) + (20-10) ) / 2 = 11.00000
 * undergroundSystem.checkIn(10, "Leyton", 24);
 * undergroundSystem.getAverageTime("Leyton", "Waterloo");          // return 11.00000
 * undergroundSystem.checkOut(10, "Waterloo", 38);
 * undergroundSystem.getAverageTime("Leyton", "Waterloo");          // return 12.00000
 *
 * Input
 * ["UndergroundSystem","checkIn","checkOut","getAverageTime","checkIn","checkOut","getAverageTime","checkIn","checkOut","getAverageTime"]
 * [[],[10,"Leyton",3],[10,"Paradise",8],["Leyton","Paradise"],[5,"Leyton",10],[5,"Paradise",16],["Leyton","Paradise"],[2,"Leyton",21],[2,"Paradise",30],["Leyton","Paradise"]]
 *
 * Output
 * [null,null,null,5.00000,null,null,5.50000,null,null,6.66667]
 *
 * Explanation
 * UndergroundSystem undergroundSystem = new UndergroundSystem();
 * undergroundSystem.checkIn(10, "Leyton", 3);
 * undergroundSystem.checkOut(10, "Paradise", 8);
 * undergroundSystem.getAverageTime("Leyton", "Paradise"); // return 5.00000
 * undergroundSystem.checkIn(5, "Leyton", 10);
 * undergroundSystem.checkOut(5, "Paradise", 16);
 * undergroundSystem.getAverageTime("Leyton", "Paradise"); // return 5.50000
 * undergroundSystem.checkIn(2, "Leyton", 21);
 * undergroundSystem.checkOut(2, "Paradise", 30);
 * undergroundSystem.getAverageTime("Leyton", "Paradise"); // return 6.66667
 *
 * </pre>
 * Constraints:
 * There will be at most 20000 operations.
 * 1 <= id, t <= 106
 * All strings consist of uppercase and lowercase English letters, and digits.
 * 1 <= stationName.length <= 10
 * Answers within 10-5 of the actual value will be accepted as correct.
 */
public class DesignUndergroundSystem {
    Map<Integer, Pair<String, Integer>> checkIns = new HashMap<>();
    Map<Pair<String, String>, Timing> timings = new HashMap<>();

    /**
     * Approach: Use hashmaps to maintain mappings of the check-in time and the source station of a person.
     * Use that to calculate time spent between source station and check-out station.
     * Maintain another map which maintains total time spent and total trips taken between two pairs of stations
     * <p>
     * {@link DesignBrowserHistory} {@link DesignPhoneDirectory}
     */
    public DesignUndergroundSystem() {

    }

    public void checkIn(int id, String stationName, int t) {
        checkIns.put(id, new Pair<>(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Pair<String, Integer> lastCheckIn = checkIns.get(id);
        String sourceStation = lastCheckIn.getKey();
        int timeTaken = t - lastCheckIn.getValue(); //time taken for this person in this journey
        Pair<String, String> key = new Pair<>(sourceStation, stationName);
        Timing currentTimings = timings.getOrDefault(key, new Timing(0, 0));
        currentTimings.totalTime += timeTaken;
        currentTimings.peopleTravelled += 1;
        timings.put(key, currentTimings); //update the timings for this journey

        checkIns.remove(id); //remove journey details of current person as it's journey has finished
    }

    public double getAverageTime(String startStation, String endStation) {
        Timing timing = timings.get(new Pair<>(startStation, endStation));
        return timing.totalTime / (double) timing.peopleTravelled;
    }

    private static class Timing {
        int peopleTravelled;
        int totalTime;

        public Timing(int peopleTravelled, int totalTime) {
            this.peopleTravelled = peopleTravelled;
            this.totalTime = totalTime;
        }
    }
}
