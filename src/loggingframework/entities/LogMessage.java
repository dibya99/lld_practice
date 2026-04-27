package loggingframework.entities;

import loggingframework.enums.LogLevel;

import java.time.LocalDateTime;

public class LogMessage {
    private LogLevel logLevel;
    private String message;
    private String threadName;
    private LocalDateTime timestamp;
    private String loggerName;

    public LogMessage(LogLevel logLevel, String message, String loggerName) {
        this.timestamp=LocalDateTime.now();
        this.logLevel = logLevel;
        this.message = message;
        this.loggerName = loggerName;
        this.threadName=Thread.currentThread().getName();
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }
}
