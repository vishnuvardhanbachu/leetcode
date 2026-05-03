import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        
        // Step 1: Build adjacency lists
        List<Integer>[] redGraph = new ArrayList[n];
        List<Integer>[] blueGraph = new ArrayList[n];
        
        for (int i = 0; i < n; i++) {
            redGraph[i] = new ArrayList<>();
            blueGraph[i] = new ArrayList<>();
        }
        
        for (int[] e : redEdges) {
            redGraph[e[0]].add(e[1]);
        }
        
        for (int[] e : blueEdges) {
            blueGraph[e[0]].add(e[1]);
        }
        
        int[] result = new int[n];
        Arrays.fill(result, -1);
        
        // visited[node][color]
        boolean[][] visited = new boolean[n][2];
        
        Queue<int[]> queue = new LinkedList<>();
        
        // Start from node 0 with both colors
        queue.offer(new int[]{0, 0, 0}); // last edge red
        queue.offer(new int[]{0, 1, 0}); // last edge blue
        
        visited[0][0] = true;
        visited[0][1] = true;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int color = curr[1];
            int dist = curr[2];
            
            // Set result if first time reached
            if (result[node] == -1) {
                result[node] = dist;
            }
            
            // Alternate color
            if (color == 0) { 
                // last was red → now use blue edges
                for (int next : blueGraph[node]) {
                    if (!visited[next][1]) {
                        visited[next][1] = true;
                        queue.offer(new int[]{next, 1, dist + 1});
                    }
                }
            } else {
                // last was blue → now use red edges
                for (int next : redGraph[node]) {
                    if (!visited[next][0]) {
                        visited[next][0] = true;
                        queue.offer(new int[]{next, 0, dist + 1});
                    }
                }
            }
        }
        
        return result;
    }
}