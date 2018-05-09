import futest.me.FileUtils;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void nullPathTest() throws IOException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Path should not be null");
        futest.me.FileUtils.readAll(null);

    }

    @Test
    public void nonExistingFileTest() throws IOException {
        thrown.expect(FileNotFoundException.class);
        thrown.expectMessage("File does not exist");
        futest.me.FileUtils.readAll("src/test/resources/r");
    }

    @Test
    public void directoryPathTest() throws IOException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("This might be a directory");
        futest.me.FileUtils.readAll("src/test/resources/directory");
    }


    @Test
    public void emptyFileTest() throws IOException {
        Assert.assertEquals(0, futest.me.FileUtils.readAll("src/test/resources/empty.txt").size());
    }

    @Test
    public void normalFileTest() throws IOException {
        Assert.assertEquals(new ArrayList<String>(Arrays.asList
                ("Одна из самых","популярных флеш игр Lines.","Графика в стиле Lines 98.","Вверху можно выбирать режим показа")),
                futest.me.FileUtils.readAll("src/test/resources/lines"));
    }
}