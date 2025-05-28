import java.sql.*;

public class Loginservice {
    public static boolean pruefeLogin(String benutzername, String passwort) {
        String sql = "SELECT Passwort FROM benutzer_bereitsregistriert WHERE Benutzername = ?";

        try (Connection conn = Datenbankverbindung.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, benutzername);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String gespeichertesPasswort = rs.getString("Passwort");
                return passwort.equals(gespeichertesPasswort);
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}