package com.gabrielspassos;

import java.util.HashMap;
import java.util.Optional;

/*
Considering the following hash map:
power = {
  "John": 100,
  "Paul": 90,
  "George": 80,
  "Ringo": 70
}
Create three functions.
    Function one should return the power of a given person.
    Function two should receive 2 names and the with one is the most powerful(should use function one).
    Function three should receive 2 names and update the leaderboard.
Now the third function that will update the leaderboard after all the matches. i.e Leaderboard should be:
leaderboard = {
  "John": 0,
  "Paul": 0,
  "George": 0,
  "Ringo": 0
}
Every time a play wins, he scores +10 points, if there is a draw, both players score +5 points. IF one player loses, he scores -5 points.
play("John", "Paul") -> "John"
leaderboard -> {
  "John": 10,
  "Paul": -5,
  "George": 0,
  "Ringo": 0
}
lets do another round:
play("John", "Ringo") -> "John"
leaderboard -> {
  "John": 20,
  "Paul": -5,
  "George": 0,
  "Ringo": -5
}
Now can you refactor your code and do less ifs? Maybe introduce pointers?
*/
public class Challenge05 {

    private static final HashMap<String, Integer> POWER_MAP = new HashMap<>();
    private static HashMap<String, Integer> LEADERBOARD = new HashMap<>();

    static {
        POWER_MAP.put("John", 100);
        POWER_MAP.put("Paul", 90);
        POWER_MAP.put("George", 80);
        POWER_MAP.put("Ringo", 70);

        POWER_MAP.keySet().forEach(key -> LEADERBOARD.put(key, 0));
    }

    public static Optional<Integer> getPowerByName(String name) {
        return Optional.ofNullable(POWER_MAP.get(name));
    }

    public static Optional<String> getMostPowerful(String name1, String name2) {
        var optionalPower1 = getPowerByName(name1);
        var optionalPower2 = getPowerByName(name2);

        return optionalPower1.flatMap(power1 ->
                optionalPower2.map(power2 -> power1 > power2 ? name1 : name2)
        );
    }

}
