package edu.icet.trendify.entity.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "main_category")
public class MainCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "mainCategoryList")
    List<SubCategoryEntity> subCategoryList;
}
