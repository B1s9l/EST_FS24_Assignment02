package zest;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // Pre-condition Check for null Array
        if (prerequisites == null){
            throw new RuntimeException("Prerequisites can't be null");
        }

        // Pre-condition: numCourses must be > 0
        if (numCourses <= 0){
            throw new RuntimeException("numCourses must be larger than 0");
        }

        //Pre-condition: each value in prerequisites is < numCourses
        for (int[] prerequisitePair: prerequisites){
            for (int prerequisite: prerequisitePair){
                if (prerequisite >= numCourses){
                   throw new RuntimeException("Prerequisite has to be smaller than numCourses");
                }
            }
        }

        // Pre-condition: No pair in prerequisites contains duplicates
        for (int[] prerequisitePair: prerequisites){
            if (prerequisitePair[0] == prerequisitePair[1]){
                throw new RuntimeException("No prerequisite pair can have itself as a prerequisite");
            }
        }



        // create a variable for the return value:
        boolean canFinish = true;

        // Create a graph from prerequisites
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] onPath = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && hasCycle(graph, i, visited, onPath)) {
                canFinish = false; // Cycle detected
            }
        }

        // Post-condition check if return value is boolean
        if (canFinish != true && canFinish != false){
            throw new RuntimeException("Return Value has to be of type boolean");
        }

        return canFinish; // No cycle detected
    }

    private boolean hasCycle(List<List<Integer>> graph, int current, boolean[] visited, boolean[] onPath) {
        if (onPath[current]) return true; // Cycle detected
        if (visited[current]) return false; // Already visited

        visited[current] = true;
        onPath[current] = true;

        for (int neighbor : graph.get(current)) {
            if (hasCycle(graph, neighbor, visited, onPath)) {
                return true;
            }
        }

        onPath[current] = false; // Backtrack
        return false;
    }
}
