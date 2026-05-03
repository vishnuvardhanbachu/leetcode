import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        // Step 1: Assign new group IDs to -1 items
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }

        // Graph structures
        List<List<Integer>> itemGraph = new ArrayList<>();
        List<List<Integer>> groupGraph = new ArrayList<>();
        int[] itemIndegree = new int[n];
        int[] groupIndegree = new int[m];

        for (int i = 0; i < n; i++) itemGraph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) groupGraph.add(new ArrayList<>());

        // Build graphs
        for (int i = 0; i < n; i++) {
            for (int prev : beforeItems.get(i)) {

                itemGraph.get(prev).add(i);
                itemIndegree[i]++;

                if (group[i] != group[prev]) {
                    groupGraph.get(group[prev]).add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        List<Integer> groupOrder = topoSort(groupGraph, groupIndegree, m);
        if (groupOrder.isEmpty()) return new int[0];

        List<Integer> itemOrder = topoSort(itemGraph, itemIndegree, n);
        if (itemOrder.isEmpty()) return new int[0];

        // Group items by group
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int item : itemOrder) {
            map.computeIfAbsent(group[item], k -> new ArrayList<>()).add(item);
        }

        List<Integer> result = new ArrayList<>();
        for (int g : groupOrder) {
            if (map.containsKey(g)) {
                result.addAll(map.get(g));
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    private List<Integer> topoSort(List<List<Integer>> graph, int[] indegree, int size) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            for (int next : graph.get(node)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return result.size() == size ? result : new ArrayList<>();
    }
}