import futest.me.FileUtils;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WriteTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();
    public final ArrayList emptyArrayList = new ArrayList();
    public final ArrayList notEmptyArrayList = new ArrayList<String>(Arrays.asList("Одна из самых",
            "популярных флеш игр Lines.","Графика в стиле Lines 98.",
            "Вверху можно выбирать режим показа"));

    @Test
    public void nullPathTest() throws IOException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Path should not be null");
        FileUtils.writeAll(null,emptyArrayList);
    }

    @Test
    public void wrongPathTest() throws IOException {
        thrown.expect(FileNotFoundException.class);
        thrown.expectMessage(String.format("This might be a directory", "src/test/resources/y"));
        FileUtils.writeAll("src/test/resources/y", emptyArrayList);
    }

    @Test
    public void directoryPathTest() throws IOException {
        thrown.expect(FileNotFoundException.class);
        thrown.expectMessage(String.format("This might be a directory", "src/test/resources/directory"));
        FileUtils.writeAll("src/test/resources/directory",emptyArrayList);
    }


    @Test
    public void nullListTest() throws IOException {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("List should not be null");
        FileUtils.writeAll("src/test/resources/lines", null);
    }

    @Test
    public void normalFileTest() throws IOException {
        FileUtils.writeAll("src/test/resources/empty.txt", notEmptyArrayList);
        Assert.assertEquals(notEmptyArrayList, FileUtils.readAll("src/test/resources/empty.txt"));
    }

    @Test
    public void emptyListTest() throws IOException {
        FileUtils.writeAll("src/test/resources/lines", emptyArrayList);
        Assert.assertEquals(0, FileUtils.readAll("src/test/resources/empty.txt").size());
    }
}