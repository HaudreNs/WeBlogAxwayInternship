package Models;

import Helpers.Database;

import java.sql.*;

public class PostDAO {
    public static Post getPost(int id) {
        try (

                Connection connection = Database.getConnection();
                PreparedStatement ps = connection.prepareStatement(
                        "SELECT * from post WHERE id = ?")) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Post(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("body"),
                        rs.getDate("date"),
                        rs.getInt("visibility"),
                        rs.getInt("user_id"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}
