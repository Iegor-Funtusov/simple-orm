package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Iehor Funtusov, created 02/11/2020 - 9:15 PM
 */

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @Column
    private String name;

    @Column(unique = true)
    private String email;
}
