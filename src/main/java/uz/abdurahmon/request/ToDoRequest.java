package uz.abdurahmon.request;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ToDoRequest {
    private String title;
    private String description;
}
