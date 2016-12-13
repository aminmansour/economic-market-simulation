package model;

import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Amans on 06/12/2016.
 * Acts as storage for inserted comments when the program persists and allows for removal. It also provides definition for how the program retrieve data fromm a local source and
 * how to save the comments to a local source.
 */
public class CommentStore {
    private LinkedHashMap<String, Pair<String, String>> storeOfComments;
    private Pattern dataSplitter;

    /**
     * Defines the regex pattern that will do the spliting at each line and loads the comments from the storage and places it within the store of comments
     */
    public CommentStore() {
        dataSplitter = Pattern.compile("[^\\+]*");
        loadComments();

    }


    //loads the comments from file to the linkedhashmap
    private void loadComments() {
        storeOfComments = new LinkedHashMap<String, Pair<String, String>>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/resources/storage/comments.txt")));
            String currentLine;
            //iterate through each line
            while ((currentLine = reader.readLine()) != null) {
                Matcher matcher = dataSplitter.matcher(currentLine);
                //split each line into each piece of data using regex
                String[] commentData = new String[3];
                int counter = 0;
                //store each piece of data within a array
                while (matcher.find()) {
                    String val = matcher.group(0);
                    if (val.trim().length() != 0) {
                        commentData[counter] = val;
                        counter++;
                    }
                }
                //add the comment with its associated data to store of comments
                storeOfComments.put(commentData[0], new Pair<String, String>(commentData[1], commentData[2]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * returns the store of comments in an ordered fashion.
     */
    public LinkedHashMap<String, Pair<String, String>> getComments() {
        return storeOfComments;
    }

    /**
     * Adds a comment to the map of comments with the associated data mapped to the comment.
     * @param commment The comment message as entered by the user
     * @param date The time/date stamp of the comment
     * @param color The color of note which contains the color
     * @return Returns the pair that was inserted into the map
     */
    public Pair<String, Pair<String, String>> addToComments(String commment, String date, String color) {
        storeOfComments.put(commment, new Pair<String, String>(date, color));
        return new Pair<String, Pair<String, String>>(commment, new Pair<String, String>(date, color));

    }

    /**
     * Removes the comment from the map.
     * @param comment The identifable key of the particular comment entry that you want to remove from a store of comments
     */
    public void removeToComments(String comment) {
        storeOfComments.remove(comment);
    }

    /**
     * Writes to file all the comments located in the map in a fixed order with the associated date and color. All pieces of data are split with + when represented in a file.
     */
    public void saveToFile() {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {

            String content = "";

            fw = new FileWriter("src/main/resources/storage/comments.txt");
            bw = new BufferedWriter(fw);
            int counter = 0;
            for (Map.Entry<String, Pair<String, String>> currentComment : getComments().entrySet()) {
                content += currentComment.getKey() + "+" + currentComment.getValue().getKey() + "+" + currentComment.getValue().getValue() + "\n";
            }

            bw.write(content);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}