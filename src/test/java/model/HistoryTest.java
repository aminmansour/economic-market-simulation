package model;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class HistoryTest {

    private History history = new History();
    private HashMap<String, ArrayList<ArrayList<DataPiece>>> histories;


    @Test
    public void getLineChart() throws Exception {

        FileInputStream fis = new FileInputStream("src/main/resources/storage/hashmap.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        histories = (HashMap) ois.readObject();
        ArrayList<ArrayList<DataPiece>> expectedOutput = histories.get("GDP") ;
        ArrayList<ArrayList<DataPiece>> actualOutput = history.getChartData("GDP");
        assertEquals(expectedOutput , actualOutput);

    }


}