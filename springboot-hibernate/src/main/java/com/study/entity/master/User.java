package com.study.entity.master;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/17 0017.
 */
@Setter
@Getter
@Entity
@Table(name = "tbuser")
public class User implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "description")
    private String description;
}
