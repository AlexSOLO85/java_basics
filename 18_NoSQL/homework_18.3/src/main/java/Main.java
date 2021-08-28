import utils.QueryDB;
import utils.ReaderCSV;
import utils.WriterDB;

public class Main {

    public static void main(String[] args) {
        ReaderCSV readerCsv = new ReaderCSV();
        readerCsv.reader();
        WriterDB writerDb = new WriterDB();
        writerDb.write();
        QueryDB queryDB = new QueryDB();
        queryDB.query();
    }
}