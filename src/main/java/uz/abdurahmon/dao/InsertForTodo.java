package uz.abdurahmon.dao;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import uz.abdurahmon.model.ToDo;
import uz.abdurahmon.request.ToDoRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@Slf4j
@Component
public class InsertForTodo {
    private final SimpleJdbcInsert simpleJdbcInsert;
    private static final Logger logger = LoggerFactory.getLogger(InsertForTodo.class);

    public InsertForTodo(SimpleJdbcInsert simpleJdbcInsert) {
        this.simpleJdbcInsert = simpleJdbcInsert;
    }


    public void saveWithMap(ToDoRequest toDo) {
        LinkedHashMap<String, Object> mp = new LinkedHashMap<>();

        mp.put("title", toDo.getTitle());
        mp.put("description", toDo.getDescription());
        mp.put("created_at", LocalDateTime.now());
        mp.put("is_done", false);

        System.out.println("mp = " + mp);

        int executed = simpleJdbcInsert.execute(mp);

        getLoggerResult(executed);
    }

    private static void getLoggerResult(int executed) {
        if (executed == 0) {
            logger.error("Failed to insert data");
        } else {
            logger.info("Data inserted successfully");
        }
    }

    public void saveWithSqlParams(ToDoRequest toDo) {
        ToDo target = new ToDo();
        target.setTitle(toDo.getTitle());
        target.setDescription(toDo.getDescription());
        target.setCreatedAt(LocalDateTime.now());
        target.setIsDone(false);

        System.out.println("target = " + target);

        SqlParameterSource source = new BeanPropertySqlParameterSource(target);
        int executed = simpleJdbcInsert.execute(source);

        getLoggerResult(executed);
    }
}