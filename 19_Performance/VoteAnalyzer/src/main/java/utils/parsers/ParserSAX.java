package utils.parsers;

import entity.Voter;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import utils.times.WorkTime;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ParserSAX extends DefaultHandler {
    private static final SimpleDateFormat BIRTH_DAY_FORMAT = new SimpleDateFormat("yyyy.MM.dd");
    private static final SimpleDateFormat VISIT_DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private static final HashMap<Integer, WorkTime> VOTE_STATION_WORK_TIMES = new HashMap<>();

    private Voter voter;
    private final HashMap<Voter, Integer> voterCounts;

    public ParserSAX() {
        voterCounts = new HashMap<>();
    }

    public void parseFileSAX(String fileName) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            InputStream inputStream = new FileInputStream(fileName);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            InputSource inputSource = new InputSource(bufferedInputStream);
            SAXParser saxParser = factory.newSAXParser();
            ParserSAX handler = new ParserSAX();
            saxParser.parse(inputSource, handler);
            handler.printDuplicatedVoters();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try {
            if (qName.equals("voter") && voter == null) {
                Date birthDay = BIRTH_DAY_FORMAT.parse(attributes.getValue("birthDay"));
                voter = new Voter(attributes.getValue("name"), birthDay);
            }
            if (qName.equals("visit") && voter != null) {
                int count = voterCounts.getOrDefault(voter, 0);
                voterCounts.put(voter, count + 1);

                int station = Integer.parseInt(attributes.getValue("station"));
                Date time = VISIT_DATE_FORMAT.parse(attributes.getValue("time"));

                WorkTime workTime = VOTE_STATION_WORK_TIMES.get(station);
                if (workTime == null) {
                    workTime = new WorkTime();
                    VOTE_STATION_WORK_TIMES.put(station, workTime);
                }
                workTime.addVisitTime(time.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("voter")) {
            voter = null;
        }
    }

    private void printDuplicatedVoters() {
        System.out.println("Voting station work times: ");
        for (Integer votingStation : VOTE_STATION_WORK_TIMES.keySet()) {
            WorkTime workTime = VOTE_STATION_WORK_TIMES.get(votingStation);
            System.out.println("\t" + votingStation + " - " + workTime);
        }

        System.out.println("Duplicated voters: ");
        for (Voter voter : voterCounts.keySet()) {
            int count = voterCounts.get(voter);
            if (count > 1) {
                System.out.println("\t" + voter + " - " + count);
            }
        }
    }
}