package loggingframework.entities;

import loggingframework.enums.LogLevel;
import loggingframework.strategies.LogAppender;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogManager {
    private static LogManager logManager;
    private AsyncLogProcessor asyncLogProcessor;
    private Logger rootLogger;
    private Map<String, Logger> loggerMap;

    public void setAsyncLogProcessor(AsyncLogProcessor asyncLogProcessor) {
        this.asyncLogProcessor = asyncLogProcessor;
    }

    public Logger getRootLogger() {
        return rootLogger;
    }

    public void setRootLogger(Logger rootLogger) {
        this.rootLogger = rootLogger;
    }

    public Map<String, Logger> getLoggerMap() {
        return loggerMap;
    }

    public void setLoggerMap(Map<String, Logger> loggerMap) {
        this.loggerMap = loggerMap;
    }

    private LogManager() {
        asyncLogProcessor = new AsyncLogProcessor();
        loggerMap = new ConcurrentHashMap<>();
        rootLogger = new Logger("root", LogLevel.DEBUG, null);
        loggerMap.put("root", rootLogger);
    }

    public static LogManager getInstance() {
        if (logManager == null)
            logManager = new LogManager();
        return logManager;
    }

    public AsyncLogProcessor getAsyncLogProcessor() {
        return asyncLogProcessor;
    }

    public Logger getLogger(String name) {
        if (loggerMap.containsKey(name))
            return loggerMap.get(name);
        Logger logger = createLogger(name);
        loggerMap.put(name, logger);
        return logger;
    }

    public Logger createLogger(String name) {
        if (name.equals("root"))
            return rootLogger;
        String parent = "";

        int indexOfDot = name.lastIndexOf('.');
        if (indexOfDot == -1)
            parent = "root";
        else
            parent = name.substring(0, indexOfDot);
        Logger parentLogger = getLogger(parent);
        return new Logger(name, null, parentLogger);

    }

    public void shutdown()
    {
        asyncLogProcessor.stop();
        for(Map.Entry<String,Logger> entry: loggerMap.entrySet())
        {
            for(LogAppender appender:entry.getValue().getAppenderList())
                appender.close();
        }
    }


}
