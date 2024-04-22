package zest;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Array;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

class MergeKSortedListsTest {

    //Task 1
    private MergeKSortedLists obj;
    ListNode l1 = new ListNode(1, new ListNode(4, new ListNode(5)));
    ListNode l2 = new ListNode(1, new ListNode( 3, new ListNode(4)));
    ListNode l3 = new ListNode( 2, new ListNode(6));
    ListNode l4 = new ListNode( -100, new ListNode( 0, new ListNode(4)));
    ListNode l5 = new ListNode( -97, new ListNode( -80, new ListNode( -1, new ListNode(3))));

    @BeforeEach
    public void setUp(){
        obj = new MergeKSortedLists();
    }

    @Test
    public void test_example_linked_list(){
        List<Integer> sol = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 4, 4, 5, 6));
        ListNode[] l = new ListNode[3];
        l[0] = l1;
        l[1] = l2;
        l[2] = l3;
        ListNode res = obj.mergeKLists(l);
        int c = 0;

        while (res != null){
            Assertions.assertEquals(res.val, sol.get(c));
            c += 1;
            res = res.next;
        }
    }

    @Test
    public void test_empty_linked_list(){
        ListNode[] l = new ListNode[0];
        ListNode res = obj.mergeKLists(l);
        Assertions.assertNull(res);
        Assertions.assertNull(obj.mergeKLists(null));
    }

    @Test
    public void test_null_input(){
        ListNode res = obj.mergeKLists(null);
        Assertions.assertNull(res);
        Assertions.assertNull(obj.mergeKLists(null));
    }


    @Test
    public void test_empty_node(){
        ListNode[] l = new ListNode[1];
        l[0] = new ListNode();
        ListNode res = obj.mergeKLists(l);
        Assertions.assertNull(res);
    }

    @Test
    public void test_empty_node_in_normal_list(){
        List<Integer> sol = new ArrayList<>(Arrays.asList(1, 1, 3, 4, 4, 5));
        ListNode[] l = new ListNode[4];
        l[0] = l1;
        l[1] = l2;
        l[2] = new ListNode();
        ListNode res = obj.mergeKLists(l);
        int c = 0;

        while (res != null){
            Assertions.assertEquals(res.val, sol.get(c));
            c += 1;
            res = res.next;
        }
    }


    // Task 3
    @Test
    public void test_list_with_too_many_linked_lists(){
        List<Integer> sol = new ArrayList<>(Arrays.asList(1, 1, 3, 4, 4, 5));
        ListNode[] l = new ListNode[10001];
        for (int i = 0; i<10001; i++){
            l[i] = new ListNode(1);
        }
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            obj.mergeKLists(l);
        });

        String expectedMessage = "The input contains too many linked lists, at most 10^4";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void test_list_with_too_many_values(){
        ListNode[] l = new ListNode[5001];
        for (int i = 0; i<5001; i++){
            l[i] = new ListNode(1, new ListNode(2));
        }
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            obj.mergeKLists(l);
        });

        String expectedMessage = "The input contains too many values, at most 10^4";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void test_node_value_outOfBounds(){
        ListNode[] l = new ListNode[1];
        l[0] = new ListNode(10005);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            obj.mergeKLists(l);
        });

        String expectedMessage = "Values in linked lists must be between -10^4 and 10^4";
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void test_values_are_sorted(){
        List<Integer> sol = new ArrayList<>(Arrays.asList(-100, -97, -80, -1, 0, 1, 3, 4, 4, 5));
        ListNode[] l = new ListNode[3];
        l[0] = l1;
        l[1] = l4;
        l[2] = l5;
        ListNode res = obj.mergeKLists(l);
        int c = 0;
        while (res != null){
            Assertions.assertEquals(res.val, sol.get(c));
            c += 1;
            res = res.next;
        }
    }


    @Test
    public void test_sorted_with_many_duplicates(){
        List<Integer> sol = new ArrayList<>(Arrays.asList(-1, -1, -1, 0, 0, 0, 0, 0, 1, 1, 2, 2));
        ListNode[] l = new ListNode[3];
        l[0] = new ListNode( -1, new ListNode( -1, new ListNode(0, new ListNode(2))));
        l[1] = new ListNode( -1, new ListNode(0, new ListNode(1, new ListNode(1))));
        l[2] = new ListNode(0, new ListNode(0, new ListNode(0, new ListNode(2))));
        ListNode res = obj.mergeKLists(l);
        int c = 0;
        while (res != null){
            Assertions.assertEquals(res.val, sol.get(c));
            c += 1;
            res = res.next;
        }
    }

    @Test
    public void test_one(){
        ListNode[] l = new ListNode[1];
        l[0] = new ListNode(70, null);
        ListNode res = obj.mergeKLists(l);
        ListNode runner = res;
        while(runner != null){
            System.out.println(runner.val);
            runner = runner.next;
        }

    }

    @Property
    void property_variable_size_lists(
            @ForAll
            @IntRange(min = 1, max = 100) int number_of_linked_lists
    ){
        obj = new MergeKSortedLists();
        List<ListNode> list_of_linked_lists = new ArrayList<>();
        for (int i=0; i<number_of_linked_lists; i++){
            Arbitrary<Integer> size = Arbitraries.integers().between(1, 100);
            list_of_linked_lists.add(linked_list(size.sample()));
        }

        ListNode[] l = list_of_linked_lists.toArray(new ListNode[0]);

        List<Integer> expected = new ArrayList<>();
        for(int i=0; i<l.clone().length; i++){
            ListNode k = l.clone()[i];
            while(k != null){
                expected.add(k.val);
                k = k.next;
            }
        }

        ListNode actual = obj.mergeKLists(l.clone());

        Collections.sort(expected);

        ListNode final_runner = actual;

        int i = 0;
        while(final_runner != null && i < expected.size()){
            assertThat(final_runner.val).isEqualTo(expected.get(i));
            final_runner = final_runner.next;
            i++;
        }


    }

    @Property
    @Report(Reporting.GENERATED)
    void property_sortedness_10lists(
            @ForAll
            @IntRange(min = 0, max = 10) int size,
            @ForAll
            @IntRange(min = 0, max = 1000) int length
    ){
        obj = new MergeKSortedLists();
        List<ListNode> list_of_linked_lists = generate_list_of_head_nodes(size, length);


        ListNode[] l = list_of_linked_lists.toArray(new ListNode[0]);

        List<Integer> expected = new ArrayList<>();
        for(int i=0; i<l.clone().length; i++){
            ListNode k = l.clone()[i];
            while(k != null){
                expected.add(k.val);
                k = k.next;
            }
        }

        ListNode actual = obj.mergeKLists(l.clone());

        Collections.sort(expected);
        //expected holds all nodes in a java linked list in a sorted manner

        ListNode final_runner = actual;

        int i = 0;
        while(final_runner != null && i < expected.size()){
            assertThat(final_runner.val).isEqualTo(expected.get(i));
            final_runner = final_runner.next;
            i++;
        }

        if(size != 0 && length != 0){
            assertThat(final_runner).isNull(); //all nodes are in actual
            assertThat(i).isEqualTo(length*size); // all nodes are in expected
        }
        else{
            assertThat(final_runner).isNull();
            assertThat(i).isEqualTo(0);
        }

    }

    @Property
    void property_sortedness_100lists(
            @ForAll
            @IntRange(min = 0, max = 100) int size,
            @ForAll
            @IntRange(min = 0, max = 100) int length
    ){
        obj = new MergeKSortedLists();

        List<ListNode> list_of_linked_lists = generate_list_of_head_nodes(size, length);

        ListNode[] l = list_of_linked_lists.toArray(new ListNode[0]);

        List<Integer> expected = new ArrayList<>();
        for(int i=0; i<l.clone().length; i++){
            ListNode k = l.clone()[i];
            while(k != null){
                expected.add(k.val);
                k = k.next;
            }
        }
        ListNode actual = obj.mergeKLists(l.clone());

        Collections.sort(expected);

        //expected holds all nodes in a java linked list in a sorted manner

        ListNode final_runner = actual;
        int i = 0;

        while(final_runner != null && i < expected.size()){
            assertThat(final_runner.val).isEqualTo(expected.get(i));
            final_runner = final_runner.next;
            i++;
        }

        if(size != 0 && length != 0){
            assertThat(final_runner).isNull(); //all nodes are in actual
            assertThat(i).isEqualTo(length*size); // all nodes are in expected
        }
        else{
            assertThat(final_runner).isNull();
            assertThat(i).isEqualTo(0);
        }
    }

    @Property
    void property_sortedness_1000lists(
            @ForAll
            @IntRange(min = 0, max = 1000) int size,
            @ForAll
            @IntRange(min = 0, max = 10) int length
    ){
        obj = new MergeKSortedLists();

        List<ListNode> list_of_linked_lists = generate_list_of_head_nodes(size, length);

        ListNode[] l = list_of_linked_lists.toArray(new ListNode[0]);

        List<Integer> expected = new ArrayList<>();
        for(int i=0; i<l.clone().length; i++){
            ListNode k = l.clone()[i];
            while(k != null){
                expected.add(k.val);
                k = k.next;
            }
        }

        ListNode actual = obj.mergeKLists(l.clone());

        Collections.sort(expected);

        //expected holds all nodes in a java linked list in a sorted manner

        ListNode final_runner = actual;
        int i = 0;

        while(final_runner != null && i < expected.size()){
            assertThat(final_runner.val).isEqualTo(expected.get(i));
            final_runner = final_runner.next;
            i++;
        }

        if(size != 0 && length != 0){
            assertThat(final_runner).isNull(); //all nodes are in actual
            assertThat(i).isEqualTo(length*size); // all nodes are in expected
        }
        else{
            assertThat(final_runner).isNull();
            assertThat(i).isEqualTo(0);
        }
    }

    @Property
    void property_too_large_input(
            @ForAll
            @IntRange(min=1000, max=2000) int size,
            @ForAll
            @IntRange(min=11, max=20) int length
            ){
        obj = new MergeKSortedLists();

        List<ListNode> list_of_linked_lists = generate_list_of_head_nodes(size, length);

        ListNode[] l = list_of_linked_lists.toArray(new ListNode[0]);

        assertThatThrownBy(() -> {
            obj.mergeKLists(l);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("The input contains too many values, at most 10^4");

    }


    /**
     *
     * @param size: the number of linked list to pass to mergeKSortedLists
     * @param length: the amount of element/nodes in each linked list
     * @return list of ListNodes that are head nodes to the linked lists
     */
    @Provide
    private List<ListNode> generate_list_of_head_nodes(int size, int length){
        List<ListNode> res = new ArrayList<ListNode>();
        for (int i = 0; i<size; i++){
            res.add(linked_list(length));
        }
        return res;
    }

    /**
     *
     * @param length: the number of element in a linked list
     * @return the head ListNode of a linked list with length elements and sorted values
     */
    @Provide
    private ListNode linked_list(int length) {
        ListNode head = null;
        ListNode runner = null;
        Arbitrary<int[]> gens = Arbitraries.integers().between(-10000, 10000).array(int[].class).ofSize(length);
        int[] values = Arrays.stream(gens.sample()).sorted().toArray();
        for (int i=0 ; i<length ; i++){
            if(i == 0){
                head = new ListNode(values[i], null);
                runner = head;
                continue;
            }
            runner.next = new ListNode(values[i], null);
            runner = runner.next;
        }

        return head;
    }



}