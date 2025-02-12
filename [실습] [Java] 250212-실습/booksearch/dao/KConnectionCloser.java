package booksearch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class KConnectionCloser implements ConnectionCloser{
    @Override
    public void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
