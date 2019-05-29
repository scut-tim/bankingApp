package jdbcutil;

import java.sql.ResultSet;

public interface ResultAdapter<T> {

    T handle(ResultSet resultSet);
}
