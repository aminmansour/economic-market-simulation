package model;

import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Amans on 06/12/2016.
 */
public class CommentStore {
    private TreeMap<String, Pair<String, String>> storeOfComments;
    private Pattern p;
    private ArrayList<String> storeOfCommentColors;

    public CommentStore() {
        p = Pattern.compile("[^\\+]*");
        loadComments();

    }

    private void loadComments() {
        storeOfComments = new TreeMap<String, Pair<String, String>>();
        storeOfCommentColors = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/resources/storage/comments.txt")));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                Matcher matcher = p.matcher(currentLine);
                String[] commentData = new String[3];
                int counter = 0;
                while (matcher.find()) {
                    String val = matcher.group(0);
                    if (val.trim().length() != 0) {
                        commentData[counter] = val;
                        counter++;
                    }
                }
                storeOfComments.put(commentData[0], new Pair<String, String>(commentData[1], commentData[2]));
                storeOfCommentColors.add(commentData[2]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(storeOfComments.size());
        System.out.println(storeOfCommentColors.size());
    }

    public TreeMap<String, Pair<String, String>> getComments() {
        return storeOfComments;
    }

    public ArrayList<String> getCommentColors() {
        return storeOfCommentColors;
    }

    public Pair<String, Pair<String, String>> addToComments(String commment, String date, String color) {
        storeOfComments.put(commment, new Pair<String, String>(date, color));
        return new Pair<String, Pair<String, String>>(commment, new Pair<String, String>(date, color));

    }

    public void removeToComments(String comment) {
        storeOfComments.remove(comment);
    }


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