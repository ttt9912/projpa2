package ch5.embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@AllArgsConstructor
@Embeddable
public class Color {
    private String name;

    public Color() {
    }
}
