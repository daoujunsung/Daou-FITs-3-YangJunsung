package booksearch.dao;

import java.sql.Connection;

public interface ConnectionMaker {
    Connection makeNewConnection();
}
