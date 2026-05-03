class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
        }
        
        int[] state = new int[numCourses]; // 0=unvisited
        
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(graph, state, i)) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean dfs(List<List<Integer>> graph, int[] state, int course) {
        
        if (state[course] == 1) return false; // cycle
        if (state[course] == 2) return true;
        
        state[course] = 1;
        
        for (int next : graph.get(course)) {
            if (!dfs(graph, state, next)) {
                return false;
            }
        }
        
        state[course] = 2;
        return true;
    }
}