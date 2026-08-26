class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        for (int r = 0; r < numRows; r++) {
            List<Integer> row = new ArrayList<>(r + 1);
            for (int c = 0; c <= r; c++) {
                if (c == 0 || c == r) {
                    row.add(1);
                } else {
                    List<Integer> above = triangle.get(r - 1);
                    row.add(above.get(c - 1) + above.get(c));
                }
            }
            triangle.add(row);
        }
        return triangle;
    }
}