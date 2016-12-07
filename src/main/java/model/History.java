package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

/**
 * Created by hanitawil on 06/12/2016.
 */
public class History {

    private HashMap<String, ArrayList<ArrayList<DataPiece>>> histories;

    public History() {

        histories = new HashMap<>();

        try
        {
            FileInputStream fis = new FileInputStream("src/main/resources/storage/hashmap.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            histories = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }catch(ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
        }

    }

    public ArrayList<ArrayList<DataPiece>> getLineChart(String key) {

        return histories.get(key);

    }

    public String getId(ArrayList<ArrayList<DataPiece>> chartIn) {

        String answer = null;

        for(Map.Entry<String, ArrayList<ArrayList<DataPiece>>> chart : histories.entrySet()) {

            if(chart.getValue() == chartIn) {

                answer = chart.getKey();
            }

        }

        return answer;
    }

    public HashMap<String, ArrayList<ArrayList<DataPiece>>> getHistories() {
        return histories;
    }

    public boolean compareIds(String queryId1, String queryId2) {

        String id1 = queryId1.split("\\+")[0];
        String restOfId1 = queryId1.split("\\+")[1];
        String id2 = queryId2.split("\\+")[0];
        String restOfId2 = queryId2.split("\\+")[1];

        String[] id1Countries = id1.split("(?<=\\G..)");
        String[] id2Countries = id2.split("(?<=\\G..)");

        HashSet<String> id1Set = new HashSet<String>(Arrays.asList(id1Countries));
        HashSet<String> id2Set = new HashSet<String>(Arrays.asList(id2Countries));

        if(id1Set.equals(id2Set)) {
            if(Objects.equals(restOfId1, restOfId2)) {
                return true;
            }

        }

        return false;
    }

    public void clear(){
        histories.clear();
    }
}
