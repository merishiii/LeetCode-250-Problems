import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        HashMap<Integer, Set<Integer>> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            map.computeIfAbsent(seat[0], k -> new HashSet<>()).add(seat[1]);
        }

        int result = (n - map.size()) * 2;

        for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
            Set<Integer> reserved = entry.getValue();

            boolean left = !reserved.contains(2) && !reserved.contains(3) && !reserved.contains(4) && !reserved.contains(5);
            boolean middle = !reserved.contains(4) && !reserved.contains(5) && !reserved.contains(6) && !reserved.contains(7);
            boolean right = !reserved.contains(6) && !reserved.contains(7) && !reserved.contains(8) && !reserved.contains(9);

            if (left && right) {
                result += 2;
            } else if (left || middle || right) {
                result += 1;
            }
        }

        return result;
    }
}