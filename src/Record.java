/**
 * this class represents the records that will be stored in the hash table
 * @author Jawaad Ahmar
 */

public class Record {
    
    // instance variables for class Record
    private String key;
    private int score;
    private int level;

    // constructor initializes instance variables
    public Record (String key, int score, int level) {
        this.key = key;
        this.score = score;
        this.level = level;
    }

    // accessor gets key instance variable 
    public String getKey() {
        return key;
    }

    // accessor gets score instance variable
    public int getScore() {
        return score;
    }

    // accessor gets level instance variable
    public int getLevel() {
        return level;
    }
}
