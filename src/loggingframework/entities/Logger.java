package loggingframework.entities;

import loggingframework.enums.LogLevel;
import loggingframework.strategies.LogAppender;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Logger {
    private Logger parent;
    private List<LogAppender> appenderList;
    private String name;
    private LogLevel level;
    private boolean additivity;

    public Logger(String name, LogLevel logLevel, Logger parent) {
        this.name = name;
        this.parent = parent;
        appenderList = new CopyOnWriteArrayList<>();
        this.level = logLevel;
        this.additivity = true;

    }

    public Logger getParent() {
        return parent;
    }

    public void setParent(Logger parent) {
        this.parent = parent;
    }

    public List<LogAppender> getAppenderList() {
        return appenderList;
    }

    public void setAppenderList(List<LogAppender> appenderList) {
        this.appenderList = appenderList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public boolean isAdditivity() {
        return additivity;
    }

    public void setAdditivity(boolean additivity) {
        this.additivity = additivity;
    }

    public void addAppender(LogAppender logAppender) {
        this.appenderList.add(logAppender);
    }


    public LogLevel getEffectiveLevel() {
        for (Logger logger = this; logger != null; logger = logger.parent) {
            if (logger.getLevel() != null)
                return logger.getLevel();
        }
        return LogLevel.DEBUG;
    }

    public void callAppenders(LogMessage logMessage) {

        if(!this.appenderList.isEmpty())
            LogManager.getInstance().getAsyncLogProcessor().process(logMessage,appenderList);

        if(parent!=null && additivity)
            parent.callAppenders(logMessage);

    }

    public void append(String message, LogLevel logLevel) {
        if (logLevel.getPriority() >= getEffectiveLevel().getPriority()) {
            callAppenders(new LogMessage(logLevel, message, name));
        }
    }

    public void debug(String message)
    {
        append(message,LogLevel.DEBUG);
    }

    public void info(String message)
    {
        append(message,LogLevel.DEBUG);
    }

    public void error(String message)
    {
        append(message, LogLevel.ERROR);
    }

    public void warn(String message)
    {
        append(message, LogLevel.WARN);
    }

    public void fatal(String message)
    {
        append(message, LogLevel.FATAL);
    }



}
