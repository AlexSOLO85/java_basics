package utils.inserts;

import connector.DBConnection;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import utils.parsers.ParserToDB;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDB {
    private static final int MAX_INSERT_BATCH = 50_000;

    public void parseFileSAXtoDB(String fileName) {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

            InputStream inputStream = new FileInputStream(fileName);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            InputSource inputSource = new InputSource(bufferedInputStream);
            SAXParser saxParser = saxParserFactory.newSAXParser();
            ParserToDB handler = new ParserToDB();
            saxParser.parse(inputSource, handler);

            ObjectArrayList<String> result = handler.getResult();

            String sqlQuery = "INSERT INTO voters(name, birthDate, station, time)" +
                    "VALUES" +
                    " (?, ?, ?, ?)";

            DBConnection.getConnectionVoters().setAutoCommit(false);

            PreparedStatement preparedStatement =
                    DBConnection.getConnectionVoters().prepareStatement(sqlQuery);

            int count = 1;
            for (String value : result) {
                String[] array = value.split(",");
                preparedStatement.setString(1, array[0]);
                preparedStatement.setString(2, array[1]);
                preparedStatement.setString(3, array[2]);
                preparedStatement.setString(4, array[3]);
                preparedStatement.addBatch();
                if (count % MAX_INSERT_BATCH == 0) {
                    preparedStatement.executeBatch();
                    DBConnection.getConnectionVoters().commit();
                }
                count++;
            }

            preparedStatement.executeBatch();
            System.out.println("Количество записей " + count);
            DBConnection.getConnectionVoters().commit();

            preparedStatement.close();
            DBConnection.getConnectionVoters().close();
        } catch (ParserConfigurationException | SAXException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}