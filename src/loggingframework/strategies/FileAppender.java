package loggingframework.strategies;

import loggingframework.entities.LogMessage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements LogAppender{
    private LogFormatter logFormatter;
    private BufferedWriter bufferedWriter;

    public FileAppender(String filename)
    {
        this.logFormatter=new SimpleTextFormatter();
        try {
            this.bufferedWriter=new BufferedWriter(new FileWriter(filename,true));
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize file writer",e);
        }
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
    public synchronized void append(LogMessage logMessage) {
        String formatter = logFormatter.format(logMessage);
        try {
            bufferedWriter.write(formatter);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void close() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
