package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task {
    private int id;
    private String name;
    private String description;
}