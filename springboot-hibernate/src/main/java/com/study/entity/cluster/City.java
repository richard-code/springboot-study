package com.study.entity.cluster;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;

/**
 * Created by liqing on 2017/5/15 0015.
 */
@Setter
@Getter
@Entity
@Table(name = "tbcity")
public class City {

    private static final long serialVersionUID = -1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "description")
    private String description;
}
