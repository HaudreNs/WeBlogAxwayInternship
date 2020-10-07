package Models;

import Helpers.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public static User getUser(String loginInfo, String password, boolean byEmail) {
        if (byEmail) {
            try (
                    Connection connection = Database.getConnection();
                    PreparedStatement ps = connection.prepareStatement(
                            "SELECT * FROM user WHERE Email=? AND Password=MD5(?)")) {

                ps.setString(1, loginInfo);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return UserFactory.createFromResultSet(rs);
                }
            } catch (Exception e) {
                System.out.println(e);
            }

            return null;
        } else {
            try (
                    Connection connection = Database.getConnection();
                    PreparedStatement ps = connection.prepareStatement(
                            "SELECT * FROM user WHERE username=? AND Password=MD5(?)")) {

                ps.setString(1, loginInfo);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return UserFactory.createFromResultSet(rs);
                }
            } catch (Exception e) {
                System.out.println(e);
            }

            return null;
        }
    }

    public static User getUser(int id) {
        try (
                Connection connection = Database.getConnection();
                PreparedStatement ps = connection.prepareStatement(
                        "SELECT * FROM user WHERE id = ?")
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return UserFactory.createFromResultSet(rs);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}
