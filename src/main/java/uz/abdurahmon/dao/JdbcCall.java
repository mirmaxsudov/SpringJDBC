package uz.abdurahmon.dao;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import uz.abdurahmon.request.ToDoRequest;

@Slf4j
@Component
public class JdbcCall {
    private final SimpleJdbcCall simpleJdbcCall;
    private static final Logger logger = LoggerFactory.getLogger(JdbcCall.class);

    public JdbcCall(SimpleJdbcCall simpleJdbcCall) {
        this.simpleJdbcCall = simpleJdbcCall;
    }

    public void saveCallToDo(ToDoRequest toDoRequest) {

    }

}