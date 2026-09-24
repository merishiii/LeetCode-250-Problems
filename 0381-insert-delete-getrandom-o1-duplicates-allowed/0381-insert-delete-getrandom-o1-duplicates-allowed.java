class RandomizedCollection {
    private List<Integer> values;
    private Map<Integer, Set<Integer>> indices;
    private Random random;

    public RandomizedCollection() {
        values = new ArrayList<>();
        indices = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        Set<Integer> positions = indices.computeIfAbsent(val, key -> new LinkedHashSet<>());
        boolean absent = positions.isEmpty();

        values.add(val);
        positions.add(values.size() - 1);

        return absent;
    }

    public boolean remove(int val) {
        Set<Integer> positions = indices.get(val);

        if (positions == null || positions.isEmpty()) {
            return false;
        }

        int removeIndex = positions.iterator().next();
        positions.remove(removeIndex);

        int lastIndex = values.size() - 1;
        int lastValue = values.get(lastIndex);

        values.set(removeIndex, lastValue);
        values.remove(lastIndex);

        if (removeIndex != lastIndex) {
            Set<Integer> lastPositions = indices.get(lastValue);
            lastPositions.remove(lastIndex);
            lastPositions.add(removeIndex);
        }

        return true;
    }

    public int getRandom() {
        return values.get(random.nextInt(values.size()));
    }
}