package uz.abdurahmon.service.base;

import uz.abdurahmon.model.ToDo;
import uz.abdurahmon.request.ToDoRequest;

import java.util.List;

public interface ToDoService {
    void save(ToDoRequest toDo);

    void saveWithMpInsert(ToDoRequest toDo);

    void saveWithSqlSource(ToDoRequest toDo);

    void saveWithNamedParameter(ToDoRequest toDo);

    void update(ToDoRequest toDo, Long id);

    void updateWithNamedParameter(ToDoRequest toDo, Long id);

    void delete(Long id);

    void deleteWithNamedParameter(Long id);

    List<ToDo> getAll();

    List<ToDo> getAllWithNamedParameter();

    ToDo getById(Long id);

    ToDo getByIdWithNamedParameter(Long id);

    List<ToDo> getDone();

    boolean isDone(ToDo toDo);

    boolean isNotDone(ToDo toDo);

    boolean isExistToDo(Long id);

    void makeADone(Long id);
}