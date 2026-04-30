package loggingframework.strategies;

import loggingframework.entities.LogMessage;

public interface LogAppender {
    void setFormatter(LogFormatter logFormatter);
    LogFormatter getFormatter();
    void append(LogMessage logMessage);
    void close();
}
