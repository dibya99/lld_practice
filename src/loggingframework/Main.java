package loggingframework;

import loggingframework.entities.LogManager;
import loggingframework.entities.Logger;
import loggingframework.enums.LogLevel;
import loggingframework.strategies.ConsoleAppender;
import loggingframework.strategies.FileAppender;

import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        LogManager logManager = LogManager.getInstance();

        // ===============================
        // TEST 1: Basic logging
        // ===============================
        System.out.println("\n===== TEST 1: Basic Logging =====");
        Logger root = logManager.getRootLogger();
        root.addAppender(new ConsoleAppender());

        root.info("Hello World");
        root.error("Something failed");

        Thread.sleep(200);

        // ===============================
        // TEST 2: File logging
        // ===============================
        System.out.println("\n===== TEST 2: File Logging =====");
        root.addAppender(new FileAppender("logging.txt"));

        root.info("This should go to file + console");

        Thread.sleep(200);

        // ===============================
        // TEST 3: Log level filtering
        // ===============================
        System.out.println("\n===== TEST 3: Log Level Filtering =====");
        root.setLevel(loggingframework.enums.LogLevel.ERROR);

        root.debug("This should NOT print");
        root.info("This should NOT print");
        root.error("This SHOULD print");

        Thread.sleep(200);

        // ===============================
        // TEST 4: Logger hierarchy
        // ===============================
        System.out.println("\n===== TEST 4: Logger Hierarchy =====");

        Logger child = logManager.getLogger("com.oracle.component");

        child.error("Child logger error (should propagate to root)");

        Thread.sleep(200);

        // ===============================
        // TEST 5: Multiple appenders
        // ===============================
        System.out.println("\n===== TEST 5: Multiple Appenders =====");
        root.setLevel(LogLevel.DEBUG);

        Logger multiLogger = logManager.getLogger("multi.test");
        multiLogger.addAppender(new ConsoleAppender());
        multiLogger.addAppender(new FileAppender("multi_log.txt"));

        multiLogger.info("This should go to console + file");

        Thread.sleep(200);

        // ===============================
        // TEST 6: Async stress test
        // ===============================
        System.out.println("\n===== TEST 6: Async Stress =====");

        Logger asyncLogger = logManager.getLogger("async.test");

        for (int i = 0; i < 50; i++) {
            asyncLogger.info("Log message " + i);
        }

        Thread.sleep(500);

        // ===============================
        // TEST 7: Multi-threaded logging
        // ===============================
        System.out.println("\n===== TEST 7: Multi-threaded Logging =====");

        Runnable task = () -> {
            Logger threadLogger = logManager.getLogger("thread.test");
            for (int i = 0; i < 10; i++) {
                threadLogger.info("Thread: " + Thread.currentThread().getName() + " -> " + i);
            }
        };

        Thread t1 = new Thread(task, "T1");
        Thread t2 = new Thread(task, "T2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        Thread.sleep(500);

        // ===============================
        // TEST 8: Deep hierarchy
        // ===============================
        System.out.println("\n===== TEST 8: Deep Hierarchy =====");

        Logger deepLogger = logManager.getLogger("a.b.c.d.e");

        deepLogger.error("Deep hierarchy log");

        Thread.sleep(200);

        // ===============================
        // TEST 9: Rapid logging (burst)
        // ===============================
        System.out.println("\n===== TEST 9: Burst Logging =====");

        for (int i = 0; i < 100; i++) {
            root.info("Burst log " + i);
        }

        Thread.sleep(500);

        // ===============================
        // TEST 10: Shutdown behavior
        // ===============================
        System.out.println("\n===== TEST 10: Shutdown =====");

        logManager.shutdown(); // assuming you wired stop() here

        root.info("This should NOT be logged");

        System.out.println("Shutdown complete");
    }
}
