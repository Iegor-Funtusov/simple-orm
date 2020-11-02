package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;

/**
 * @author Iehor Funtusov, created 02/11/2020 - 9:12 PM
 */

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
