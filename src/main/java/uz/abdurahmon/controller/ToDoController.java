package uz.abdurahmon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.abdurahmon.model.ToDo;
import uz.abdurahmon.request.ToDoRequest;
import uz.abdurahmon.service.base.ToDoService;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
public class ToDoController {
    private final ToDoService toDoService;
    private static final Logger logger = Logger.getLogger(ToDoController.class.getName());

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.setViewName("main");

        List<ToDo> all = toDoService.getAllWithNamedParameter();
        modelAndView.addObject("todos", all);

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.setViewName("new_todo");
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        toDoService.deleteWithNamedParameter(id);
        logger.info("Deleted todo: " + id);
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(
            @PathVariable("id") Long id
    ) {
        ToDo toDo = toDoService.getByIdWithNamedParameter(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("todo", toDo);
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.setViewName("update");

        return modelAndView;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                    @RequestParam("title") String title,
                                    @RequestParam("description") String description) {

        toDoService.updateWithNamedParameter(
                ToDoRequest.builder()
                        .title(title)
                        .description(description)
                        .build(), id);
        logger.info("Updated todo: " + id);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(ServletUriComponentsBuilder.fromCurrentContextPath().path("/").build().toUri())
                .build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> makeADone(@PathVariable("id") Long id) {
        toDoService.makeADone(id);
        logger.info("Done todo: " + id);

        return ResponseEntity.status(HttpStatus.OK)
                .location(
                        ServletUriComponentsBuilder.fromCurrentContextPath().path("/").
                                build()
                                .toUri())
                .build();
    }

    @PostMapping("/create")
    public ResponseEntity<?> save(
            @RequestParam("title") String title,
            @RequestParam("description") String description
    ) {

        ToDoRequest toDoRequest = new ToDoRequest();
        toDoRequest.setTitle(title);
        toDoRequest.setDescription(description);

        toDoService.saveWithNamedParameter(toDoRequest);
        logger.info("Created todo: " + toDoRequest);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(ServletUriComponentsBuilder.fromCurrentContextPath().path("/").build().toUri())
                .build();
    }
}