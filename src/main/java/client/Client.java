package client;

import settingsFile.SettingsFile;
import log.RecordingLog;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final RecordingLog log = RecordingLog.getInstance();

    private Scanner scanner;
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public Client() {
        try {
            final SettingsFile config = SettingsFile.getInstance();

            scanner = new Scanner(System.in);
            socket = new Socket(config.getHost(), config.getPort());
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            log.log("Клиенту предлагают ввести свой никнейм");
            System.out.println("Введите свой никнейм:");
            printWriter.println(scanner.nextLine());

            final Receiver receiver = new Receiver(bufferedReader);
            receiver.start();

            String message = "";
            while (!"exit".equals(message)) {
                message = scanner.nextLine();
                printWriter.println(message);
            }

            receiver.interrupt();
        } catch (IOException e) {
            log.log("Ошибка в конструкторе у класса " + Client.class.getName());
            e.printStackTrace();
        } finally {
            log.log("Закрытие потоков у класса " + Client.class.getName());
            closeAll();
        }
    }

    private void closeAll() {
        try {
            scanner.close();
            bufferedReader.close();
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            log.log("Ошибка при закрытии потоков у класса " + Client.class.getName());
            e.printStackTrace();
        }
    }
}
