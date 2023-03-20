package server;
import settingsFile.SettingsFile;

public class MainServer {
    public static void main(String[] args) {
        SettingsFile settingsFile = SettingsFile.getInstance();
        Server server = new Server();
        server.listen(settingsFile.getPort());
    }
}
