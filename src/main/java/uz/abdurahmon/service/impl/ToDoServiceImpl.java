package uz.abdurahmon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import uz.abdurahmon.dao.InsertForTodo;
import uz.abdurahmon.dao.NamedParameterForToDo;
import uz.abdurahmon.dao.ToDoDao;
import lombok.extern.slf4j.Slf4j;
import uz.abdurahmon.model.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uz.abdurahmon.request.ToDoRequest;
import uz.abdurahmon.service.base.ToDoService;
import uz.abdurahmon.validation.ToDoValidation;

import java.util.List;

@Slf4j
@Service
public class ToDoServiceImpl implements ToDoService {
    private final ToDoDao toDoDao;
    private final InsertForTodo insertForTodo;
    private final NamedParameterForToDo namedParameterForToDo;
    private static final Logger logger = LoggerFactory.getLogger(ToDoServiceImpl.class);


    @Autowired
    public ToDoServiceImpl(ToDoDao toDoDao, InsertForTodo insertForTodo, NamedParameterForToDo namedParameterForToDo) {
        this.toDoDao = toDoDao;
        this.insertForTodo = insertForTodo;
        this.namedParameterForToDo = namedParameterForToDo;
    }

    @Override
    public void save(ToDoRequest toDo) {
        String validate = ToDoValidation.validate(toDo);

        if (validate != null) {
            logger.error(validate);
            return;
        }

        toDoDao.save(
                ToDo.builder()
                        .title(toDo.getTitle())
                        .description(toDo.getDescription())
                        .isDone(false)
                        .build()
        );
    }

    @Override
    public void saveWithMpInsert(ToDoRequest toDo) {
        String validate = ToDoValidation.validate(toDo);

        if (validate != null) {
            logger.error(validate);
            return;
        }

        insertForTodo.saveWithMap(toDo);
    }

    @Override
    public void saveWithSqlSource(ToDoRequest toDo) {
        String validate = ToDoValidation.validate(toDo);

        if (validate != null) {
            logger.error(validate);
            return;
        }

        insertForTodo.saveWithSqlParams(toDo);
    }

    @Override
    public void saveWithNamedParameter(ToDoRequest toDo) {
        String validate = ToDoValidation.validate(toDo);

        if (validate != null) {
            logger.error(validate);
            return;
        }

        namedParameterForToDo.save(toDo);
    }

    @Override
    public void update(ToDoRequest toDo, Long id) {
        String validate = ToDoValidation.validate(toDo);

        logger.debug(validate);

        if (validate != null) {
            logger.error(validate);
            return;
        }

        ToDo targetToDo = toDoDao.getById(id);
        targetToDo.setTitle(toDo.getTitle());
        targetToDo.setDescription(toDo.getDescription());

        toDoDao.update(targetToDo);
    }

    @Override
    public void updateWithNamedParameter(ToDoRequest toDo, Long id) {
        String validate = ToDoValidation.validate(toDo);

        if (validate != null) {
            logger.error(validate);
            return;
        }

        ToDo target = namedParameterForToDo.getById(id);

        if (target == null) {
            logger.info("There is no toDo with this id : {}", id);
            return;
        }

        target.setTitle(toDo.getTitle());
        target.setDescription(toDo.getDescription());

        namedParameterForToDo.update(target, id);
    }

    @Override
    public void delete(Long id) {
        toDoDao.delete(id);
    }

    @Override
    public void deleteWithNamedParameter(Long id) {
        namedParameterForToDo.delete(id);
    }

    @Override
    public List<ToDo> getAll() {
        return toDoDao.getAll();
    }

    @Override
    public List<ToDo> getAllWithNamedParameter() {
        return namedParameterForToDo.getAll();
    }

    @Override
    public ToDo getById(Long id) {
        return toDoDao.getById(id);
    }

    @Override
    public ToDo getByIdWithNamedParameter(Long id) {
        return namedParameterForToDo.getById(id);
    }

    @Override
    public List<ToDo> getDone() {
        return toDoDao.getDone();
    }

    @Override
    public boolean isDone(ToDo toDo) {
        return toDo.getIsDone();
    }

    @Override
    public boolean isNotDone(ToDo toDo) {
        return !toDo.getIsDone();
    }

    @Override
    public boolean isExistToDo(Long id) {
        return toDoDao.isExist(id);
    }

    @Override
    public void makeADone(Long id) {
        toDoDao.makeADone(id);
    }
}
