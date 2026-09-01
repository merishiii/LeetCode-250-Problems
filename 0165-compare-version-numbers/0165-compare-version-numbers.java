class Solution {
    public int compareVersion(String version1, String version2) {
        String[] first = version1.split("\\.");
        String[] second = version2.split("\\.");
        int length = Math.max(first.length, second.length);

        for (int i = 0; i < length; i++) {
            int a = i < first.length ? Integer.parseInt(first[i]) : 0;
            int b = i < second.length ? Integer.parseInt(second[i]) : 0;

            if (a < b) {
                return -1;
            }

            if (a > b) {
                return 1;
            }
        }

        return 0;
    }
}