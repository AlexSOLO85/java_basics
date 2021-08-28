package utils;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.List;

public class WriterDB {
    private static final String COLLECTION_NAME = "students";
    private static final int ID_NAME_COLUMN = 0;
    private static final int ID_AGE_COLUMN = 1;
    private static final int ID_COURSES_COLUMN = 2;
    private static final String DB_NAME_COLUMN = "name";
    private static final String DB_AGE_COLUMN = "age";
    private static final String DB_COURSES_COLUMN = "courses";

    public void write() {
        MongoCollection<Document> collection = Connector.getDbConnection().getCollection(COLLECTION_NAME);
        collection.drop();
        ReaderCSV records = new ReaderCSV();
        for (List<String> value : records.reader()) {
            Document document = new Document()
                    .append(DB_NAME_COLUMN, value.get(ID_NAME_COLUMN))
                    .append(DB_AGE_COLUMN, Integer.parseInt(value.get(ID_AGE_COLUMN)))
                    .append(DB_COURSES_COLUMN, value.get(ID_COURSES_COLUMN));
            collection.insertOne(document);
        }
    }
}