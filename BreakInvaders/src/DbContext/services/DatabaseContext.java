package DbContext.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DbContext.interfaces.IDatabaseContext;
import DbContext.models.GameRecord;

public class DatabaseContext implements IDatabaseContext {

    Connection connection;

    public DatabaseContext() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            // db parameters
            String url = "jdbc:sqlite:sqlite/db/breakInvaders.db";
            // create a connection to the database
            connection = DriverManager.getConnection(url);

            String gameRecord = "CREATE TABLE IF NOT EXISTS GameRecord ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "date VARCHAR(50),"
                    + "score INTEGER"
                    + ");";

            var sql = connection.createStatement();
            sql.execute(gameRecord);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<GameRecord> getTopGameRecords() {
        List<GameRecord> records = new ArrayList<>();
        try {
            String sql = "SELECT * FROM GameRecord ORDER BY score DESC LIMIT 10;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String date = resultSet.getString("date");
                int score = resultSet.getInt("score");
                GameRecord record = new GameRecord();
                record.setId(id);
                record.setDate(date);
                record.setScore(score);

                records.add(record);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return records;
    }

    @Override
    public boolean AddGameRecord(GameRecord record) {
        try {
            String maxIdSql = "SELECT MAX(id) FROM GameRecord;";
            PreparedStatement maxIdStatement = connection.prepareStatement(maxIdSql);
            ResultSet maxIdResult = maxIdStatement.executeQuery();
            int maxId = maxIdResult.getInt(1);
            record.setId(maxId + 1);

            String sql = "INSERT INTO GameRecord(id, date, score) VALUES(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, record.getId());
            statement.setString(2, record.getDate());
            statement.setInt(3, record.getScore());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
