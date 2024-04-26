package uz.abdurahmon.dao;

import org.springframework.stereotype.Component;
import uz.abdurahmon.dao.rowMappers.ToDoRowMapper;
import uz.abdurahmon.model.ToDo;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ToDoDao {
    private final JdbcTemplate jdbcTemplate;

    public ToDoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(ToDo toDo) {
        jdbcTemplate.update("INSERT INTO todos (title, description, is_done, created_at) VALUES (?, ?, ?, ?)",
                toDo.getTitle(), toDo.getDescription(), toDo.getIsDone(), LocalDateTime.now());
    }

    public void update(ToDo toDo) {
        jdbcTemplate.update("UPDATE todos SET title = ?, description = ?, is_done = ? WHERE id = ?",
                toDo.getTitle(), toDo.getDescription(), toDo.getIsDone(), toDo.getId());
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM todos WHERE id = ?", id);
    }

    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM todos");
    }

    public ToDo getById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM todos WHERE id = ?",
                new ToDoRowMapper(), id);
    }

    public List<ToDo> getAll() {
        return jdbcTemplate.query("SELECT * FROM todos", new ToDoRowMapper());
    }

    public List<ToDo> getDone() {
        return jdbcTemplate.query("SELECT * FROM todos where is_done)", new ToDoRowMapper());
    }

    public List<ToDo> getAllByCreatedAt(boolean asc) {
        if (!asc)
            return jdbcTemplate.query("SELECT * FROM todos ORDER BY created_at DESC", new ToDoRowMapper());
        return jdbcTemplate.query("SELECT * FROM todos ORDER BY created_at ASC", new ToDoRowMapper());
    }

    public boolean isExist(Long id) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject("SELECT COUNT(*) = 1 FROM todos WHERE id = ?", Boolean.class, id));
    }

    public void makeADone(Long id) {
        jdbcTemplate.update("UPDATE todos SET is_done = NOT is_done WHERE id = ?", id);
    }
}