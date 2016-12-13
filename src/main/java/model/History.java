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

    /**
     *  An object that saves the data from the queries of the user and saves them in a hashmap with a unique id
     */

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
            c.printStackTrace();
        }

    }

    /**
     *
     * @param key a unique id for a linechart
     * @return a saved chart based on key
     */

    public ArrayList<ArrayList<DataPiece>> getLineChart(String key) {

        return histories.get(key);

    }

    /**
     *
     * @param chartIn the data to make a linechart
     * @return an id for the data if its already saved
     */

    public String getId(ArrayList<ArrayList<DataPiece>> chartIn) {

        String answer = null;

        for(Map.Entry<String, ArrayList<ArrayList<DataPiece>>> chart : histories.entrySet()) {

            if(chart.getValue() == chartIn) {

                answer = chart.getKey();
            }

        }

        return answer;
    }

    /**
     *
     * @return the hasmap
     */

    public HashMap<String, ArrayList<ArrayList<DataPiece>>> getHistories() {
        return histories;
    }

    /**
     * compares two idds to check if they are the same
     * @param queryId1
     * @param queryId2
     * @return a boolean
     */

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

    /**
     * clears history
     */

    public void clear(){
        histories.clear();
    }

    public ArrayList<ArrayList<DataPiece>> getLastEntry() {
        return histories.get(1);
    }
}
