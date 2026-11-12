package com.example;

/**
 * Maven GitHub Demo Application
 * DevOps Experiment 3 - Maven + GitHub + GitHub Actions + Docker Hub
 */
public class App {

    public static final String APP_NAME = "Maven GitHub Demo App";
    public static final String APP_VERSION = "1.0-SNAPSHOT";

    public String getGreeting() {
        return "Hello from " + APP_NAME + " v" + APP_VERSION + "!";
    }

    public int add(int a, int b) {
        return a + b;
    }

    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static void main(String[] args) {
        App app = new App();
        System.out.println("=================================================");
        System.out.println(app.getGreeting());
        System.out.println("Java Version : " + System.getProperty("java.version"));
        System.out.println("Java Vendor  : " + System.getProperty("java.vendor"));
        System.out.println("OS Name      : " + System.getProperty("os.name"));
        System.out.println("Addition Demo: 5 + 7 = " + app.add(5, 7));
        System.out.println("Application executed successfully!");
        System.out.println("=================================================");
    }
}
