package booksearch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface ConnectionCloser {
    void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs);
}
