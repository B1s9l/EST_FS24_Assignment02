import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import org.example.SortColors;
import org.junit.jupiter.api.Order;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.awt.geom.Path2D.contains;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class TestSortColors {

    @Property
//    @Report(Reporting.GENERATED)
    void sortColorTester(
            @ForAll
            @Size(value = 300) List<@IntRange(min = 0, max = 2)
                        Integer> numbers,
            @ForAll
            @IntRange(min = 1, max = 300) int slicer) {

        numbers = numbers.subList(0, slicer);
        int[] array = numbers.stream().mapToInt(x -> x).toArray();

        SortColors.sortColors(array);


        Collections.sort(numbers);

        int[] expected = numbers.stream().mapToInt(x -> x).toArray();

        assertArrayEquals(expected, array);

    }
}
