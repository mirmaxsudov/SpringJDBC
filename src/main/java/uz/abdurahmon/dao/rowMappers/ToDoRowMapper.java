package uz.abdurahmon.dao.rowMappers;

import uz.abdurahmon.model.ToDo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ToDoRowMapper implements RowMapper<ToDo> {
    @Override
    public ToDo mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ToDo(rs.getLong("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getBoolean("is_done"),
                rs.getTimestamp("created_at").toLocalDateTime());
    }
}
