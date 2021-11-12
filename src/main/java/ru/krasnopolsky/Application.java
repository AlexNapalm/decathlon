package ru.krasnopolsky;

public class Application {

    /**
     * Entry point of the application.
     * All logic starts in AppStarter.start() method.
     */
    public static void main(String[] args) throws Exception {
        AppStarter appStarter = new AppStarter();
        appStarter.start(args);
    }
}
