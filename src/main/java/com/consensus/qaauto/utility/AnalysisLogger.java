package com.consensus.qaauto.utility;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A thread-safe logger that creates a unique analysis file for each test.
 * The file is named [testName]_[timestamp].log.
 */
public class AnalysisLogger {

    // 1. ThreadLocal ensures each test thread gets its own separate PrintWriter
    private static final ThreadLocal<PrintWriter> writer = new ThreadLocal<>();

    private static final Path LOG_DIRECTORY = Paths.get("test-output","report","Files");

    /**
     * Called by @BeforeMethod. Creates the file and stores the writer in ThreadLocal.
     */
    public static void startTestLog(String testName) {
        try {
            Files.createDirectories(LOG_DIRECTORY);
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            String fileName = String.format("%s_%s.log", testName, timeStamp);
            Path fullPath = LOG_DIRECTORY.resolve(fileName);

            // true = append mode, false = overwrite. We want false.
            PrintWriter testWriter = new PrintWriter(new FileWriter(fullPath.toFile(), false));
            writer.set(testWriter); // Save this writer for this specific thread

        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize analysis logger", e);
        }
    }

    /**
     * This is the REUSABLE function to write log data from any class.
     */
    public static void write(String message) {
        // Get the writer for the current test thread and write the message
        if (writer.get() != null) {
            writer.get().println(message);
        }
    }

    /**
     * Called by @AfterMethod. Flushes, closes, and removes the writer.
     */
    public static void endTestLog() {
        PrintWriter testWriter = writer.get();
        if (testWriter != null) {
            testWriter.flush();
            testWriter.close();
            writer.remove(); // Clean up the ThreadLocal
        }
    }
}