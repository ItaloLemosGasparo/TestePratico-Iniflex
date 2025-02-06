package model;

import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
public class Person {

    private String name;

    private LocalDate birthDate;

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Person{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate.format(formatter) +
                '}';
    }
}
