class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int factorial = 1;

        for (int i = 1; i <= n; i++) {
            numbers.add(i);
            factorial *= i;
        }

        k--;
        StringBuilder result = new StringBuilder();

        for (int i = n; i >= 1; i--) {
            factorial /= i;
            int index = k / factorial;
            result.append(numbers.get(index));
            numbers.remove(index);
            k %= factorial;
        }

        return result.toString();
    }
}