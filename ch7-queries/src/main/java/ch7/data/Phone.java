package ch7.data;

import common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Phone extends BaseEntity {
    private String type;
    private String number;
}
