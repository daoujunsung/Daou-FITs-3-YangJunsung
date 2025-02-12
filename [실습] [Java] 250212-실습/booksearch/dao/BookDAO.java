package booksearch.dao;

import booksearch.vo.BookVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO {
    private ConnectionMaker connectionMaker;
    private ConnectionCloser connectionCloser;

    public BookDAO(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public ObservableList<BookVO> select(String keyword) {
        ObservableList<BookVO> books = FXCollections.observableArrayList();

        // Database 처리 6단계
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = connectionMaker.makeNewConnection();

            String sql = "SELECT bisbn, btitle, bprice, bauthor " +
                    "FROM book WHERE btitle LIKE ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");

            rs = pstmt.executeQuery();
            while(rs.next()) {
                BookVO book = new BookVO(
                        rs.getString("bisbn"),
                        rs.getString("btitle"),
                        rs.getInt("bprice"),
                        rs.getString("bauthor"));
                books.add(book);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());;
        } finally {
            connectionCloser.closeConnection(conn, pstmt, rs);
        }
        return books;
    }

    public int delete(BookVO book) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            conn = connectionMaker.makeNewConnection();

            String sql = "DELETE FROM book WHERE bisbn = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getBisbn());

            result = pstmt.executeUpdate();
        } catch(Exception e) {
            System.out.println(e.getMessage());;
        } finally {
            connectionCloser.closeConnection(conn, pstmt, null);
        }

        return result;
    }

    public int update(BookVO book) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = connectionMaker.makeNewConnection();

            String sql = "UPDATE book SET btitle = ?, bprice = ?, bauthor = ? WHERE bisbn = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getBtitle());
            pstmt.setInt(2, book.getBprice());
            pstmt.setString(3, book.getBauthor());
            pstmt.setString(4, book.getBisbn());

            result = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            connectionCloser.closeConnection(conn, pstmt, null);
        }

        return result;
    }
}
