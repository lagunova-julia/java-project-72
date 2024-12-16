package hexlet.code;

import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@AllArgsConstructor
public class BaseRepository {
    protected DataSource dataSource;

//    protected Connection getConnection() throws SQLException {
//        return dataSource.getConnection();
//    }

    // Можно добавить дополнительные методы, например, для закрытия соединений,
    // хотя, при использовании try-with-resources это не обязательно
}
