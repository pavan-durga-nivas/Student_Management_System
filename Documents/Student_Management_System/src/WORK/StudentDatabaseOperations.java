package WORK;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDatabaseOperations {

    public static void insertStudent(int sid, String sname, float cgpa) throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO STUDENT VALUES(?,?,?);";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, sid);
        pst.setString(2, sname);
        pst.setFloat(3, cgpa);
        pst.executeUpdate();
        conn.close();
    }

    public static void updateStudent(int sid, String sname, float cgpa) throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE STUDENT SET SNAME = ?, CGPA = ? WHERE SID = ? ;";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, sname);
        pst.setFloat(2, cgpa);
        pst.setInt(3, sid);
        pst.executeUpdate();
        conn.close();
    }

    public static void deleteStudent(int sid) throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM STUDENT WHERE SID = ? ;";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, sid);
        pst.executeUpdate();
        conn.close();
    }

    public static String viewStudent(int sid) throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM STUDENT WHERE SID = ? ;";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, sid);
        ResultSet rst = pst.executeQuery();
        String result = "";
        if (rst.next()) {
            result = rst.getInt(1) + " " + rst.getString(2) + " " + rst.getFloat(3);
        } else {
            result = "Invalid ID";
        }
        conn.close();
        return result;
    }
}
