class Solution {
    public int totalNumbers(int[] digits) {
        int[] count = new int[10];

        for (int digit : digits) {
            count[digit]++;
        }

        int total = 0;

        for (int first = 1; first <= 9; first++) {
            if (count[first] == 0) {
                continue;
            }

            count[first]--;

            for (int second = 0; second <= 9; second++) {
                if (count[second] == 0) {
                    continue;
                }

                count[second]--;

                for (int third = 0; third <= 8; third += 2) {
                    if (count[third] > 0) {
                        total++;
                    }
                }

                count[second]++;
            }

            count[first]++;
        }

        return total;
    }
}