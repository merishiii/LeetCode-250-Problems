import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);

        if (!dict.contains(endWord)) {
            return result;
        }

        Map<String, List<String>> parents = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> visited = new HashSet<>();

            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                int step = distance.get(word);
                char[] arr = word.toCharArray();

                for (int j = 0; j < arr.length; j++) {
                    char original = arr[j];

                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        arr[j] = ch;
                        String next = new String(arr);

                        if (!dict.contains(next)) {
                            continue;
                        }

                        if (!distance.containsKey(next)) {
                            distance.put(next, step + 1);
                            queue.offer(next);
                            visited.add(next);
                        }

                        if (distance.get(next) == step + 1) {
                            parents.computeIfAbsent(next, k -> new ArrayList<>()).add(word);
                        }
                    }

                    arr[j] = original;
                }
            }

            if (distance.containsKey(endWord)) {
                break;
            }
        }

        if (!distance.containsKey(endWord)) {
            return result;
        }

        List<String> path = new ArrayList<>();
        path.add(endWord);

        build(endWord, beginWord, parents, path, result);

        return result;
    }

    private void build(String word, String beginWord,
                       Map<String, List<String>> parents,
                       List<String> path,
                       List<List<String>> result) {
        if (word.equals(beginWord)) {
            List<String> temp = new ArrayList<>(path);
            Collections.reverse(temp);
            result.add(temp);
            return;
        }

        if (!parents.containsKey(word)) {
            return;
        }

        for (String prev : parents.get(word)) {
            path.add(prev);
            build(prev, beginWord, parents, path, result);
            path.remove(path.size() - 1);
        }
    }
}