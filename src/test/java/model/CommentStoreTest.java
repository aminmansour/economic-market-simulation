package model;

import org.junit.Test;
import javafx.util.Pair;
import java.util.*;



import static org.junit.Assert.*;

public class CommentStoreTest {

    private CommentStore commentStore = new CommentStore();

    /**
     *
     * This method is performed by producing two boolean type variable.
     * A beforeMethod variable representing the existence of comment before call addToComment and a afterMethod variable representing the existence of comment after the method.
     * If beforeMethod is false and afterMethod is true, it means that the addToComments method does perform as expected.
     *
     * @throws Exception
     */
    @Test
    public void TestAddToComments() throws Exception {

        String comment = "Comment";
        String date = "today";
        String color = "White";
        LinkedHashMap<String, Pair<String, String>> storeOfComments1 = new LinkedHashMap<String, Pair<String, String>>();
        Boolean actualOutput = false;

        storeOfComments1.put(comment, new Pair<String , String>(date , color));
        Boolean beforeMethod = commentStore.getComments().containsKey(comment);
        commentStore.addToComments(comment , date , color);
        Boolean afterMethod = commentStore.getComments().containsKey(comment);

        Boolean expectedOutput = storeOfComments1.containsKey(comment);
        //Boolean actualOutput = commentStore.getStoreOfComments().containsKey(comment);
        if(beforeMethod == false && afterMethod == true){
            actualOutput = true;
        }

        assertEquals(expectedOutput , actualOutput);




    }

    /**
     *
     * This method is performed by producing two boolean type variable.
     * A beforeMethod variable representing the existence of comment before calling removeToComment and a afterMethod variable representing the existence of comment after the method.
     * If beforeMethod is true and afterMethod is false, it means that the removeToComments method does perform as expected.
     * @throws Exception
     */
    @Test
    public void TestRemoveToComments() throws Exception {

        String comment = "Comment";
        String date = "today";
        String color = "White";
        LinkedHashMap<String, Pair<String, String>> storeOfComments1 = new LinkedHashMap<String, Pair<String, String>>();;
        Boolean actualOutput = false;

        storeOfComments1.put(comment, new Pair<String , String>(date , color));
        commentStore.addToComments(comment , date , color);
        Boolean beforeMethod = commentStore.getComments().containsKey(comment);
        commentStore.removeToComments(comment);
        Boolean afterMethod = commentStore.getComments().containsKey(comment);

        Boolean expectedOutput = storeOfComments1.containsKey(comment);
        //Boolean actualOutput = commentStore.getStoreOfComments().containsKey(comment);
        if(beforeMethod == true && afterMethod == false){
            actualOutput = true;
        }

        assertEquals(expectedOutput , actualOutput);

    }

}