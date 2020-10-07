package Models;

import Helpers.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CommentDAO {
    public static Comment getComment(int id) {
        try (
                Connection connection = Database.getConnection();
                PreparedStatement ps = connection.prepareStatement(
                        "SELECT * from comment WHERE id = ?")) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Comment(rs.getInt("id"),
                        rs.getString("body"),
                        rs.getDate("date"),
                        rs.getInt("userId"),
                        rs.getInt("postId"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}
