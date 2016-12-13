package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

/**
 * Created by hanitawil on 06/12/2016.
 * Saves the data that has been queried in a hash map which will save when the program is closed or goes out of scope
 */
public class History {

    private HashMap<String, ArrayList<ArrayList<DataPiece>>> hpDataStore;

    /**
     *  An object that saves the data from the queries of the user and saves them in a hashmap with a unique id
     */
    public History() {

        hpDataStore = new HashMap<>();

        try
        {
            //retrieves from cache
            FileInputStream fis = new FileInputStream("src/main/resources/storage/hashmap.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            hpDataStore = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        }catch(IOException | ClassNotFoundException ioe)
        {
            ioe.printStackTrace();
        }

    }

    /**
     *  Returns particular data with that key
     * @param key a key to identify the dataPieces with in a arraylist
     * @return The data(list of datPieces) with that associated index
     */

    public ArrayList<ArrayList<DataPiece>> getChartData(String key) {
        return hpDataStore.get(key);
    }

    /**
     * Checks if any of the data objects have been previously cached
     * @param chartIn the data to make a chart
     * @return an key for the data if its already exists in cache
     */

    public String getKey(ArrayList<ArrayList<DataPiece>> chartIn) {

        String answer = null;

        for (Map.Entry<String, ArrayList<ArrayList<DataPiece>>> chart : hpDataStore.entrySet()) {

            if(chart.getValue() == chartIn) {

                answer = chart.getKey();
            }

        }

        return answer;
    }

    /**
     *returns the data storage stored within history
     * @return the data storage hashmap
     */

    public HashMap<String, ArrayList<ArrayList<DataPiece>>> getDataStore() {
        return hpDataStore;
    }

    /**
     * compares two keys to check if they are the same
     * @param queryId1 key one
     * @param queryId2 key two
     * @return true if matches, false if not
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
     * clears all data from the cache
     */

    public void clear(){
        hpDataStore.clear();
    }

    public ArrayList<ArrayList<DataPiece>> getLastEntry() {
        return histories.get(1);
    }
}
