package client;

import log.RecordingLog;

import java.io.BufferedReader;
import java.io.IOException;

public class Receiver extends Thread {
    private static final RecordingLog log = RecordingLog.getInstance();

    private final BufferedReader bufferedReader;

    public Receiver(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(bufferedReader.readLine());
            }
        } catch (IOException e) {
            log.log("Ошибка в методе run() у класса " + Receiver.class.getName());
            e.printStackTrace();
        }
    }
}
