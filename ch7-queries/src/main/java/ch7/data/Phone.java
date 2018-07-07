package ch7.data;

import common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
public class Phone extends BaseEntity {
    private String type;
    private String number;
}
