package utils;

import com.mongodb.client.MongoCollection;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.function.Consumer;

public class QueryDB {
    private final MongoCollection<Document> COLLECTION_NAME =
            Connector.getDbConnection().getCollection("students");

    public void query() {
        BsonDocument query_01 = BsonDocument.parse("{ age: {$gt: 40} }");
        BsonDocument query_02 = BsonDocument.parse("{ }, { name: 1, _id: 0 } ");
        BsonDocument query_03 = BsonDocument.parse("{age: 1}");
        BsonDocument query_04 = BsonDocument.parse("{ }, { courses: 1, _id: 0 } ");
        BsonDocument query_05 = BsonDocument.parse("{age: -1}");

        System.out.println("Общее количество студентов в базе:\n" + COLLECTION_NAME.countDocuments());

        System.out.println("Количество студентов старше 40 лет:\n" + COLLECTION_NAME.countDocuments(query_01));

        COLLECTION_NAME.find(query_02).sort(query_03).limit(1)
                .forEach((Consumer<Document>) document ->
                        System.out.println("Имя самого молодого студента:\n" + document.get("name")));

        COLLECTION_NAME.find(query_04).sort(query_05).limit(1)
                .forEach((Consumer<Document>) document ->
                        System.out.println("Список курсов самого старого студента:\n" + document.get("courses")));
    }
}