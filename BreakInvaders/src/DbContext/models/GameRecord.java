package DbContext.models;

public class GameRecord {
    private int id;
    private String date;
    private int score;

    public GameRecord() {
        
    }

    public GameRecord(String date,int score) {
        this.date = date;
        this.score = score;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
}
