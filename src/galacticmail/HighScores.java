package galacticmail;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class HighScores {

    File scoreFile = new File("highScores.txt");
    SortedMap<Integer, String> scoreMap;

    public HighScores() {
        scoreMap = new TreeMap<>(Collections.reverseOrder());
    }

    /*public static void main(String[] args) {
        HighScores hs = new HighScores();

        hs.getScoresFromFile();

        hs.writeScoresToFile(24000, "Initials");
    }*/

    public void getScoresFromFile() {
        try {
            if(scoreFile.exists()) {
                Scanner scanner = new Scanner(scoreFile);
                while(scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    StringTokenizer strTok = new StringTokenizer(line, " ", false);
                    scoreMap.put(Integer.parseInt(strTok.nextToken()), strTok.nextToken());
                }
            }
            /*for(Map.Entry<Integer, String> entry : scoreMap.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }*/


        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void writeScoresToFile(int score, String initials) {
        scoreMap.put(score, initials);

        if(scoreMap.firstKey() == score) {
            GameWorld.setNewHighScore(true);
        }

        while(scoreMap.size() > 5) {
            scoreMap.remove( scoreMap.lastKey() );
        }

        try {
            if(scoreFile.createNewFile()) {

            }
            PrintStream pStream = new PrintStream(scoreFile);

            for(Map.Entry<Integer, String> entry : scoreMap.entrySet()) {
                //System.out.println(entry.getKey() + " " + entry.getValue());
                pStream.println(entry.getKey() + " " + entry.getValue() );
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> getScoreArrayList() {
        ArrayList<String> stringList = new ArrayList<>();
        int rank = 1;

        for(Map.Entry<Integer, String> entry : scoreMap.entrySet()) {
            stringList.add(rank + "\t" + entry.getKey());
            rank++;
        }

        return stringList;
    }


}
