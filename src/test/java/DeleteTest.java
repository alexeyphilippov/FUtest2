import futest.me.FileUtils;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class DeleteTest {
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();
    public final ExpectedException thrown = ExpectedException.none();
    @Test
    public void testDelete () throws IOException {
        File targetFile = temporaryFolder.newFile("target.txt");
        FileUtils.delete(targetFile.getAbsolutePath());
        assertTrue("Not deleted", !targetFile.exists());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testNullDest () throws NullPointerException, FileNotFoundException {
        thrown.expectMessage("path string shouldn't be null");
        FileUtils.delete(null);
    }
    @Test(expected = FileNotFoundException.class)
    public void testWrongDest() throws FileNotFoundException {
        thrown.expectMessage(String.format("path string is wrong","file already exists"));
        FileUtils.delete("src/test/resources/d");
    }
}