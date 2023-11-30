package com.samplecode.chapter2;

import java.io.*;
import java.util.concurrent.LinkedBlockingDeque;

public class ThreadReader implements Runnable{

    private String filePath;

    private ThreadReadable threadReadable;

    public ThreadReader(String filePath, ThreadReadable threadReadable) {
        this.filePath = filePath;
        this.threadReadable = threadReadable;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (true) {
                String line = reader.readLine();
                if (line == null)
                    break;
                threadReadable.readLine(line);
            }
            threadReadable.end();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ThreadReader getInstance(String filePath, ThreadReadable threadReadable) {
        return new ThreadReader(filePath, threadReadable);
    }
}
