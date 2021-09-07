package utils.parsers;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParserToDB extends DefaultHandler {
    private static final SimpleDateFormat BIRTH_DAY_FORMAT = new SimpleDateFormat("yyyy.MM.dd");
    private static final SimpleDateFormat VISIT_DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private String name;
    private Date birthDay;
    private int station;
    private Date time;

    private ObjectArrayList<String> result;

    public ObjectArrayList<String> getResult() {
        return result;
    }

    @Override
    public void startDocument() {
        if (result == null) {
            result = new ObjectArrayList<>();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("voter")) {
            try {
                name = attributes.getValue("name");
                birthDay = BIRTH_DAY_FORMAT.parse(attributes.getValue("birthDay"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (qName.equalsIgnoreCase("visit")) {
            try {
                station = Integer.parseInt(attributes.getValue("station"));
                time = VISIT_DATE_FORMAT.parse(attributes.getValue("time"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("voter")) {
            String resultVoter = name + ","
                    + BIRTH_DAY_FORMAT.format(birthDay) + ","
                    + station + ","
                    + VISIT_DATE_FORMAT.format(time);
            result.add(resultVoter);
        }
    }
}