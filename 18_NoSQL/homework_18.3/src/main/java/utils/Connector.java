package utils;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.util.List;

public class Connector {
    private static final String HOST = "localhost";
    private static final int PORT = 27017;
    private static final String USER = "admin";
    private static final String SOURCE = "admin";
    private static final char[] PASSWORD = "test".toCharArray();

    public static MongoDatabase getDbConnection() {
        MongoCredential credential = MongoCredential.createScramSha256Credential(USER, SOURCE, PASSWORD);
        MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(List.of(new ServerAddress(HOST, PORT))))
                        .credential(credential)
                        .build());
        return mongoClient.getDatabase(SOURCE);
    }
}