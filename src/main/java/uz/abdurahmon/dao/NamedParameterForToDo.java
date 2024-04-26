package uz.abdurahmon.dao;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import uz.abdurahmon.dao.rowMappers.ToDoRowMapper;
import uz.abdurahmon.model.ToDo;
import uz.abdurahmon.request.ToDoRequest;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class NamedParameterForToDo {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public NamedParameterForToDo(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void save(ToDoRequest toDoRequest) {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("title", toDoRequest.getTitle());
        map.put("description", toDoRequest.getDescription());
        map.put("isDone", false);
        map.put("createdAt", LocalDateTime.now());

        namedParameterJdbcTemplate.update("INSERT INTO todos (title, description, is_done, created_at) " +
                "VALUES (:title, :description, :isDone, :createdAt)", map
        );
    }

    public void delete(Long id) {
        Map<String, Object> mp = new HashMap<>();

        mp.put("id", id);

        namedParameterJdbcTemplate.update("DELETE FROM todos WHERE id = :id", mp);
    }

    public void update(ToDo todo, Long id) {
        Map<String, Object> map = new HashMap<>();

        map.put("title", todo.getTitle());
        map.put("description", todo.getDescription());
        map.put("isDone", todo.getIsDone());
        map.put("id", id);

        namedParameterJdbcTemplate.update(
                "UPDATE todos SET title = :title, description = :description, is_done = :isDone WHERE id = :id", map
        );
    }

    public ToDo getById(Long id) {
        Map<String, Object> source = new HashMap<>();
        source.put("id", id);

        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM todos WHERE id = :id", source, new ToDoRowMapper());
    }

    public List<ToDo> getAll() {
        return namedParameterJdbcTemplate.query("SELECT * FROM todos", new ToDoRowMapper());
    }
}