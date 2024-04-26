package uz.abdurahmon.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ToDo implements Serializable {
    private Long id;
    private String title;
    private String description;
    private Boolean isDone;
    private LocalDateTime createdAt;


}