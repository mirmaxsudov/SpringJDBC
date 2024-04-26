package uz.abdurahmon.validation;

import uz.abdurahmon.request.ToDoRequest;

public class ToDoValidation {

    public static String validate(ToDoRequest toDo) {
        if (isNullOrEmpty(toDo.getTitle()))
            return "Title is required";

        if (isNullOrEmpty(toDo.getDescription()))
            return "Description is required";

        return null;
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.isBlank();
    }

}
