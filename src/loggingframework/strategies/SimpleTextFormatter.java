package loggingframework.strategies;

import loggingframework.entities.LogMessage;

import java.time.format.DateTimeFormatter;

public class SimpleTextFormatter implements LogFormatter {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String format(LogMessage message) {
        String timestamp = message.getTimestamp().format(formatter);
        return String.format("[%s] [%s] [%s] [%s] - %s",
                timestamp, message.getThreadName(), message.getLogLevel(),
                message.getLoggerName(), message.getMessage());

    }
}
