class Solution {
    public String removeKdigits(String num, int k) {
        StringBuilder stack = new StringBuilder();

        for (int i = 0; i < num.length(); i++) {
            char digit = num.charAt(i);

            while (k > 0 && stack.length() > 0 && stack.charAt(stack.length() - 1) > digit) {
                stack.deleteCharAt(stack.length() - 1);
                k--;
            }

            stack.append(digit);
        }

        while (k > 0) {
            stack.deleteCharAt(stack.length() - 1);
            k--;
        }

        int index = 0;

        while (index < stack.length() && stack.charAt(index) == '0') {
            index++;
        }

        return index == stack.length() ? "0" : stack.substring(index);
    }
}