class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int surplusOpen = 0, surplusClose = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                surplusOpen++;
            } else if (ch == ')') {
                if (surplusOpen > 0) surplusOpen--;
                else surplusClose++;
            }
        }

        Set<String> collected = new HashSet<>();
        walk(s, 0, 0, surplusOpen, surplusClose, new StringBuilder(), collected);
        return new ArrayList<>(collected);
    }

    private void walk(String s, int pos, int balance, int dropOpen, int dropClose,
                      StringBuilder path, Set<String> collected) {
        if (pos == s.length()) {
            if (balance == 0 && dropOpen == 0 && dropClose == 0) {
                collected.add(path.toString());
            }
            return;
        }

        char ch = s.charAt(pos);
        int mark = path.length();

        if (ch == '(') {
            if (dropOpen > 0) {
                walk(s, pos + 1, balance, dropOpen - 1, dropClose, path, collected);
            }
            path.append(ch);
            walk(s, pos + 1, balance + 1, dropOpen, dropClose, path, collected);
            path.setLength(mark);
        } else if (ch == ')') {
            if (dropClose > 0) {
                walk(s, pos + 1, balance, dropOpen, dropClose - 1, path, collected);
            }
            if (balance > 0) {
                path.append(ch);
                walk(s, pos + 1, balance - 1, dropOpen, dropClose, path, collected);
                path.setLength(mark);
            }
        } else {
            path.append(ch);
            walk(s, pos + 1, balance, dropOpen, dropClose, path, collected);
            path.setLength(mark);
        }
    }
}