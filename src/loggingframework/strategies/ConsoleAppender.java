package loggingframework.strategies;

import loggingframework.entities.LogMessage;

public class ConsoleAppender implements LogAppender{

    private LogFormatter logFormatter;

    public ConsoleAppender()
    {
        logFormatter= new SimpleTextFormatter();
    }

    @Override
    public void setFormatter(LogFormatter logFormatter) {
        this.logFormatter=logFormatter;
    }

    @Override
    public LogFormatter getFormatter() {
        return logFormatter;
    }

    @Override
    public void append(LogMessage logMessage) {
        System.out.println(logFormatter.format(logMessage));
    }

    @Override
    public void close() {

    }
}
