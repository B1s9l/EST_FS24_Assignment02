package zest;


import net.jqwik.api.*;
import org.jgrapht.graph.DefaultEdge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.jgrapht.Graph;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;

class CourseScheduleTest {

    CourseSchedule courseSchedule;
    @BeforeEach
    public void init(){
        courseSchedule = new CourseSchedule();

    }

    // T1
    @Test
    public void numCourses0() {
        int[][] emptyArray = new int[0][0];
        Exception exception = assertThrows(RuntimeException.class, () -> {
            courseSchedule.canFinish(0, emptyArray);
        });

        String expectedMessage = "numCourses must be larger than 0";
        String actualMessage = exception.getMessage();
        assertEquals(true,actualMessage.contains(expectedMessage), actualMessage);
    }

    // T2
    @Test
    public void preReqNull() {
        int[][] nullArray = null;
        Exception exception = assertThrows(RuntimeException.class, () -> {
            courseSchedule.canFinish(1, nullArray);
        });

        String expectedMessage = "Prerequisites can't be null";
        String actualMessage = exception.getMessage();
        assertEquals(true,actualMessage.contains(expectedMessage), actualMessage);
    }

    //T3
    @Test
    public void preReqLengthOne() {
        int[][] lengthOneArray = new int[1][2];
        lengthOneArray[0][0] = 0;
        lengthOneArray[0][1] = 1;
        assertEquals(true, courseSchedule.canFinish(2, lengthOneArray), "Array Length One test failed");
    }

    //T4
    @Test
    public void preReqLengthTwoHasCycle() {
        int[][] lengthTwoArray = new int[2][2];
        lengthTwoArray[0][0] = 0;
        lengthTwoArray[0][1] = 1;
        lengthTwoArray[1][0] = 1;
        lengthTwoArray[1][1] = 0;
        assertEquals(false, courseSchedule.canFinish(2, lengthTwoArray), "Array Length Two with cycle test failed");
    }

    //T5
    @Test
    public void preReqLengthTwoNoCycle() {
        int[][] lengthTwoArray = new int[2][2];
        lengthTwoArray[0][0] = 0;
        lengthTwoArray[0][1] = 1;
        lengthTwoArray[1][0] = 0;
        lengthTwoArray[1][1] = 2;
        assertEquals(true, courseSchedule.canFinish(3, lengthTwoArray), "Array Length Two with cycle test failed");
    }

    //T6
    @Test
    public void preReqLengthFourNoCycle() {
        int[][] lengthFourArray = new int[4][4];
        lengthFourArray[0][0] = 0;
        lengthFourArray[0][1] = 1;
        lengthFourArray[1][0] = 0;
        lengthFourArray[1][1] = 2;
        lengthFourArray[2][0] = 1;
        lengthFourArray[2][1] = 2;
        lengthFourArray[3][0] = 0;
        lengthFourArray[3][1] = 3;
        assertEquals(true, courseSchedule.canFinish(4, lengthFourArray), "Array Length Two with cycle test failed");
    }

    //T7
    @Test
    public void preReqLengthFourHasCycle() {
        int[][] lengthFourArray = new int[4][2];
        lengthFourArray[0][0] = 0;
        lengthFourArray[0][1] = 1;
        lengthFourArray[1][0] = 0;
        lengthFourArray[1][1] = 2;
        lengthFourArray[2][0] = 1;
        lengthFourArray[2][1] = 2;
        lengthFourArray[3][0] = 1;
        lengthFourArray[3][1] = 0;
        assertEquals(false, courseSchedule.canFinish(4, lengthFourArray), "Array Length Two with cycle test failed");
    }


    //T8
    @Test
    public void validateNormalOperation() {
        int[][] lengthFourArray = new int[4][2];
        lengthFourArray[0][0] = 0;
        lengthFourArray[0][1] = 1;
        lengthFourArray[1][0] = 0;
        lengthFourArray[1][1] = 2;
        lengthFourArray[2][0] = 1;
        lengthFourArray[2][1] = 2;
        lengthFourArray[3][0] = 1;
        lengthFourArray[3][1] = 0;
        assertEquals(false, courseSchedule.canFinish(4, lengthFourArray), "Array Length Two with cycle test failed");
    }

    //T9
    @Test
    public void preCondition1Violated() {
        int[][] arrayNumCoursesTooLarge = new int[1][2];
        arrayNumCoursesTooLarge[0][0] = 0;
        arrayNumCoursesTooLarge[0][1] = 1;
        Exception exception = assertThrows(RuntimeException.class, () -> {
            courseSchedule.canFinish(1, arrayNumCoursesTooLarge);
        });

        String expectedMessage = "Prerequisite has to be smaller than numCourses";
        String actualMessage = exception.getMessage();
        assertEquals(true,actualMessage.contains(expectedMessage), actualMessage);
    }

    // T10
    @Test
    public void preCondition2Violated() {
        int[][] preRequisitesHasDuplicate = new int[1][2];
        preRequisitesHasDuplicate[0][0] = 0;
        preRequisitesHasDuplicate[0][1] = 0;
        Exception exception = assertThrows(RuntimeException.class, () -> {
            courseSchedule.canFinish(1, preRequisitesHasDuplicate);
        });

        String expectedMessage = "No prerequisite pair can have itself as a prerequisite";
        String actualMessage = exception.getMessage();
        assertEquals(true,actualMessage.contains(expectedMessage), actualMessage);
    }

    // T11
    @Test
    public void preCondition3Violated() {
        int[][] preRequisitesHasDuplicate = new int[1][2];
        preRequisitesHasDuplicate[0][0] = 0;
        preRequisitesHasDuplicate[0][1] = 1;
        Exception exception = assertThrows(RuntimeException.class, () -> {
            courseSchedule.canFinish(0, preRequisitesHasDuplicate);
        });

        String expectedMessage = "numCourses must be larger than 0";
        String actualMessage = exception.getMessage();
        assertEquals(true,actualMessage.contains(expectedMessage), actualMessage);
    }

    // T12
    @Test
    public void preCondition4Violated() {
        int[][] preRequisitesIsNull = null;
        Exception exception = assertThrows(RuntimeException.class, () -> {
            courseSchedule.canFinish(0, preRequisitesIsNull);
        });

        String expectedMessage = "Prerequisites can't be null";
        String actualMessage = exception.getMessage();
        assertEquals(true,actualMessage.contains(expectedMessage), actualMessage);
    }

    // T13
    @Test
    public void validatePostConditionHasCycle() {
        int[][] lengthThreeArray = new int[3][2];
        lengthThreeArray[0][0] = 0;
        lengthThreeArray[0][1] = 1;
        lengthThreeArray[1][0] = 0;
        lengthThreeArray[1][1] = 2;
        lengthThreeArray[2][0] = 1;
        lengthThreeArray[2][1] = 0;
        assertEquals(false, courseSchedule.canFinish(4, lengthThreeArray), "Array Length Two with cycle test failed");
    }

    // T14
    @Test
    public void validatePostConditionNoCycle() {
        int[][] lengthThreeArray = new int[3][2];
        lengthThreeArray[0][0] = 0;
        lengthThreeArray[0][1] = 1;
        lengthThreeArray[1][0] = 0;
        lengthThreeArray[1][1] = 2;
        lengthThreeArray[2][0] = 1;
        lengthThreeArray[2][1] = 3;
        assertEquals(true, courseSchedule.canFinish(4, lengthThreeArray), "Array Length Two with cycle test failed");
    }

    //T15
    @Report(Reporting.GENERATED)
    @Property
    public void validSchedulesReturnTrue(
            @ForAll("validCourses") int numCourses,
            @ForAll("validPrerequisites") int[][] prerequisites) {
        CourseSchedule courseSchedule1 = new CourseSchedule();
        boolean result = courseSchedule1.canFinish(numCourses, prerequisites);
        assertEquals(true, result);
    }

    //T16
    @Report(Reporting.GENERATED)
    @Property
    public void invalidSchedulesReturnFalse(
            @ForAll("validCourses") int numCourses,
            @ForAll("invalidPrerequisites") int[][] prerequisites) {
        CourseSchedule courseSchedule1 = new CourseSchedule();
        boolean result = courseSchedule1.canFinish(numCourses, prerequisites);
        assertEquals(false, result);
    }

    // Provides a course number that is always bigger than courses included in prerequisites.
    @Provide
    Arbitrary<Integer> validCourses() {
        return Arbitraries.integers().between(100, 150);
    }

    // Provides a 2D array that can be represented as a graph that is acyclic. So, we know the method under test should return true.
    @Provide
    Arbitrary<int[][]> validPrerequisites() {
        Arbitrary<int[]> arbitraryArray = Arbitraries.integers().between(0,99).array(int[].class).ofSize(2).uniqueElements();
        Arbitrary<int[][]> arbitrary2DArray = arbitraryArray.array(int[][].class).ofMaxSize(100).ofMinSize(1);
        return arbitrary2DArray.filter(array -> isCyclic(array) == false);
    }

    // Provides a 2D array that can be represented as a graph with a cycle present. So, we know the method under test should return false.
    @Provide
    Arbitrary<int[][]> invalidPrerequisites() {
        Arbitrary<int[]> arbitraryArray = Arbitraries.integers().between(0,99).array(int[].class).ofSize(2).uniqueElements();
        Arbitrary<int[][]> arbitrary2DArray = arbitraryArray.array(int[][].class).ofMaxSize(100).ofMinSize(1);
        return arbitrary2DArray.filter(array -> isCyclic(array) == true);
    }

    // Checks if a 2D array, represented as a graph, has a cycle. We use the JGraph library for that.
    public static boolean isCyclic(int[][] array){

        Graph<Integer, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);

        for (int[] edge : array) {
            int source = edge[0];
            int target = edge[1];

            if (!graph.containsVertex(source)) {
                graph.addVertex(source);
            }
            if (!graph.containsVertex(target)) {
                graph.addVertex(target);
            }

            graph.addEdge(source, target);
        }

        CycleDetector<Integer, DefaultEdge> cycleDetector = new CycleDetector<>(graph);
        boolean hasCycles = cycleDetector.detectCycles();

        if (hasCycles) {
            return true;
        } else {
            return false;
        }
    }
}
