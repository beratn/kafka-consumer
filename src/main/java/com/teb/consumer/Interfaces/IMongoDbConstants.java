package com.teb.consumer.Interfaces;

public interface IMongoDbConstants {
    public static String MONGODB_HOST = System.getenv().get("MONGODB_HOST");
    public static int MONGODB_PORT = Integer.valueOf(System.getenv().get("MONGODB_PORT"));
    public static String DB_NAME = "log-monitoring";
    public static String COLLECTION_NAME = "logs";
    public static String USERNAME = "root";
    public static String PASSWORD = "root";
}
