package utils.parsers;

import entity.Voter;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.times.WorkTime;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ParserDOM {
    private static final SimpleDateFormat BIRTH_DAY_FORMAT = new SimpleDateFormat("yyyy.MM.dd");
    private static final SimpleDateFormat VISIT_DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    private static final HashMap<Integer, WorkTime> VOTE_STATION_WORK_TIMES = new HashMap<>();
    private static final HashMap<Voter, Integer> VOTER_COUNTS = new HashMap<>();

    public void parseFileDOM(String fileName) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(fileName));
            findEqualVoters(doc);
            fixWorkTimes(doc);
            printInfoDOMParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findEqualVoters(Document doc) throws Exception {
        NodeList voters = doc.getElementsByTagName("voter");
        int votersCount = voters.getLength();
        for (int i = 0; i < votersCount; i++) {
            Node node = voters.item(i);
            NamedNodeMap attributes = node.getAttributes();

            String name = attributes.getNamedItem("name").getNodeValue();
            Date birthDay = BIRTH_DAY_FORMAT
                    .parse(attributes.getNamedItem("birthDay").getNodeValue());

            Voter voter = new Voter(name, birthDay);
            Integer count = VOTER_COUNTS.get(voter);
            VOTER_COUNTS.put(voter, count == null ? 1 : count + 1);
        }
    }

    private void fixWorkTimes(Document doc) throws Exception {
        NodeList visits = doc.getElementsByTagName("visit");
        int visitCount = visits.getLength();
        for (int i = 0; i < visitCount; i++) {
            Node node = visits.item(i);
            NamedNodeMap attributes = node.getAttributes();

            Integer station = Integer.parseInt(attributes.getNamedItem("station").getNodeValue());
            Date time = VISIT_DATE_FORMAT.parse(attributes.getNamedItem("time").getNodeValue());
            WorkTime workTime = VOTE_STATION_WORK_TIMES.get(station);
            if (workTime == null) {
                workTime = new WorkTime();
                VOTE_STATION_WORK_TIMES.put(station, workTime);
            }
            workTime.addVisitTime(time.getTime());
        }
    }

    private void printInfoDOMParser() {
        System.out.println("Voting station work times: ");
        for (Integer votingStation : VOTE_STATION_WORK_TIMES.keySet()) {
            WorkTime workTime = VOTE_STATION_WORK_TIMES.get(votingStation);
            System.out.println("\t" + votingStation + " - " + workTime);
        }

        System.out.println("Duplicated voters: ");
        for (Voter voter : VOTER_COUNTS.keySet()) {
            Integer count = VOTER_COUNTS.get(voter);
            if (count > 1) {
                System.out.println("\t" + voter + " - " + count);
            }
        }
    }
}