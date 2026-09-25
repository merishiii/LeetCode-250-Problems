class Solution {
    private String expression;
    private int position;

    public List<String> braceExpansionII(String expression) {
        this.expression = expression;
        this.position = 0;

        Set<String> result = parseUnion();

        return new ArrayList<>(new TreeSet<>(result));
    }

    private Set<String> parseUnion() {
        Set<String> result = new HashSet<>();
        result.addAll(parseConcat());

        while (position < expression.length() && expression.charAt(position) == ',') {
            position++;
            result.addAll(parseConcat());
        }

        return result;
    }

    private Set<String> parseConcat() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (position < expression.length()
                && expression.charAt(position) != ','
                && expression.charAt(position) != '}') {
            Set<String> factor = parseFactor();
            Set<String> next = new HashSet<>();

            for (String prefix : result) {
                for (String suffix : factor) {
                    next.add(prefix + suffix);
                }
            }

            result = next;
        }

        return result;
    }

    private Set<String> parseFactor() {
        Set<String> result = new HashSet<>();

        if (expression.charAt(position) == '{') {
            position++;
            result = parseUnion();
            position++;
        } else {
            StringBuilder builder = new StringBuilder();

            while (position < expression.length()
                    && Character.isLowerCase(expression.charAt(position))) {
                builder.append(expression.charAt(position));
                position++;
            }

            result.add(builder.toString());
        }

        return result;
    }
}