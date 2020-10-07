package Models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFactory {
    public static User createFromResultSet(ResultSet rs) throws SQLException {

        return new User(rs.getInt("id"),
                rs.getString("fullName"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("town"),
                rs.getInt("age"),
                rs.getString("motto"),
                rs.getBoolean("is_admin")
        );
    }
}
