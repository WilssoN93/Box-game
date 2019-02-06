package game;

import game.ui.HUD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class GameDAO {

    private Connection connection;
    private Statement stmt;
    private ResultSet rs;
    private HUD hud;
    private String name;

    public GameDAO(HUD hud) {
        this.hud = hud;
        try {
            connection = DriverManager.getConnection("jdbc:mysql:// localhost"
                    + "/highscores?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT", "root", "pass");
           // connection = DriverManager.getConnection("jdbc:mysql:// 213.185.225.38:3306"
             //       + "/highscores?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT", "WilssoN", "pass");
            stmt = connection.createStatement();
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e);
            throw new RuntimeException(e);

        }

    }

    public String printHighScores(int offset) {
        String s = "";
        try {
            rs = stmt.executeQuery("SELECT name,scores FROM scores ORDER BY scores DESC lIMIT 1 OfFSET " + (offset - 1) + ";");
            while (rs.next()) {
                s = rs.getString("name") + " " + rs.getString("scores");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            throw new RuntimeException(e);
        }

        return s;
    }

    public void addScore() {

        if (hud.healthPlayerOne <= 0) {

            name = JOptionPane.showInputDialog("Write Your name");
            try {

                stmt.executeUpdate("INSERT INTO scores (name,scores)"
                        + "VALUES('" + name + "'," + (int) hud.getaScore() + ");");

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
                throw new RuntimeException(e);
            }
        }

    }
}
