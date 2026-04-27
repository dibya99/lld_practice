package loggingframework.strategies;

import loggingframework.entities.LogMessage;

public interface LogFormatter {
    String format(LogMessage message);
}
