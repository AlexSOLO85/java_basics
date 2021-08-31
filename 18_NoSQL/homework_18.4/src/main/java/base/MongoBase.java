package base;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;
import utils.Connector;

import java.util.ArrayList;
import java.util.Arrays;

public class MongoBase {
    private static final String COLLECTION_STORE = "store_name";
    private static final String COLLECTION_PRODUCT = "products";

    private static final MongoCollection<Document> STORE =
            Connector.getDbConnection().getCollection(COLLECTION_STORE);
    private static final MongoCollection<Document> PRODUCT =
            Connector.getDbConnection().getCollection(COLLECTION_PRODUCT);

    public static void addStore(String storeName) {
        if (storeName.isEmpty()) {
            throw new RuntimeException();
        }

        try {
            Document documentStore = new Document("name", storeName);
            documentStore.append("products", new ArrayList<String>());
            if (getStore(storeName) == null) {
                STORE.insertOne(documentStore);
                System.out.println("Магазин " + storeName + " добавлен!");
            } else {
                System.out.println("Магазин уже был добавлен!");
            }
        } catch (Exception e) {
            System.out.println("Неверный ввод!");
        }
    }

    public static void addProducts(String input) {
        try {
            String[] var = split(input);
            String name = var[0];
            Document documentProduct = new Document("name", name);
            documentProduct.append("price", Integer.parseInt(var[1]));
            if (getProduct(name) == null) {
                PRODUCT.insertOne(documentProduct);
                System.out.println("Продукт " + name + " добавлен!");
            } else {
                System.out.println("Товар уже был добавлен");
            }
        } catch (Exception e) {
            System.out.println("Неверный ввод!");
        }
    }

    public static void addProductsToStore(String input) {
        try {
            String[] var = split(input);
            String storeName = var[1];
            String productName = var[0];
            STORE.updateOne(getStore(storeName), new Document("$addToSet",
                    new Document("products", getProduct(productName).get("name"))));
            System.out.println("Продукт " + productName + " добавлен в магазин " + storeName);
        } catch (Exception e) {
            System.out.println("Магазин или продукт не существуют! ");
        }
    }

    public static void printStatistic() {
        printInfo(getAggregate());
    }

    private static AggregateIterable<Document> getAggregate() {
        return PRODUCT.aggregate(
                Arrays.asList(
                        Aggregates.lookup("store_name", "name", "products", "store_list"),
                        Aggregates.unwind("$store_list"),
                        Aggregates.group("$store_list.name",
                                Accumulators.sum("count_products", 1),
                                Accumulators.min("min_price", "$price"),
                                Accumulators.max("max_price", "$price"),
                                Accumulators.avg("avg_price", "$price"))
                )
        );
    }

    private static void printInfo(AggregateIterable<Document> documents) {
        for (Document document : documents) {
            String shopName = (String) document.get("_id");
            System.out.println("Магазин " + shopName);
            System.out.println("Количество товара: " + document.get("count_products"));
            System.out.println("Средняя цена товара: " + document.get("avg_price"));
            System.out.println("Самый дорогой товар:  " + document.get("max_price"));
            System.out.println("Самый дешевый товар:  " + document.get("min_price"));
            System.out.println("Количество товаров, дешевле 100 рублей: " + cheapestProductCount(shopName));
        }
    }

    @SuppressWarnings("unchecked")
    private static long cheapestProductCount(String shopName) {
        Document shop = getStore(shopName);
        ArrayList<String> products = (ArrayList<String>) shop.get("products");
        return products.stream().filter(s ->
                (int) getProduct(s).get("price") < 100).count();
    }

    public static void shutdownDB() {
        STORE.drop();
        PRODUCT.drop();
        System.out.println("Работа завершена!");
    }

    private static Document getStore(String name) {
        return STORE.find(new Document("name", name)).first();
    }

    private static Document getProduct(String name) {
        return PRODUCT.find(new Document("name", name)).first();
    }

    public static String[] split(String var) {
        return var.split(" ", 2);
    }
}