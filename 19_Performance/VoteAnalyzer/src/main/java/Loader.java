import utils.inserts.InsertDB;

public class Loader {
    private static final String FILE_NAME = "F:\\Voter\\data-18M.xml";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        InsertDB insertDB = new InsertDB();
        insertDB.parseFileSAXtoDB(FILE_NAME);
        long end = System.currentTimeMillis() - start;
        System.out.printf("Время загрузки файла: %d сек.", end / 1000);
    }
}