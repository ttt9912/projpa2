package p1_ordering.entity;

import lombok.*;

import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Embeddable
public class IPAddress {
    private String address;
}
