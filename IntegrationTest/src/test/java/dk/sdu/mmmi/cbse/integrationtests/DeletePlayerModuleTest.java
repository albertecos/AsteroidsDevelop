package dk.sdu.mmmi.cbse.integrationtests;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeletePlayerModuleTest {
    //Points to root directory
    private static final String PROJECT_ROOT = new File(System.getProperty("user.dir")).getParent();

    @Test
    public void testRemovalOfPlayer() throws IOException, InterruptedException {
        //Specify the JAR file, Player
        File original = new File(PROJECT_ROOT, "/mods-mvn/Player-1.0-SNAPSHOT.jar");

        //Delete the JAR file
        assertTrue(original.delete());

        int exitCodeNoPlayer = runMain();
        assertEquals(0, exitCodeNoPlayer);

        //Rebuild Player module after the test
        ProcessBuilder mvnProcessBuilder = new ProcessBuilder("mvn", "install", "-pl", "Player", "-am");
        mvnProcessBuilder.directory(new File(PROJECT_ROOT));
        Process mvnProcess = mvnProcessBuilder.start();
        assertEquals(0, mvnProcess.waitFor());

        int exitCodeWithPlayer = runMain();
        assertEquals(0, exitCodeWithPlayer);
    }

    private int runMain() throws IOException, InterruptedException {
        //Execute the system
        ProcessBuilder processBuilder = new ProcessBuilder(
                "java",
                "--module-path", "mods-mvn",
                "--class-path", "libs/*",
                "--module", "Core/dk.sdu.mmmi.cbse.main.Main"
        );
        processBuilder.directory(new File(PROJECT_ROOT));
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        return process.waitFor();
    }
}
