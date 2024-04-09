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

    @ManyToMany(fetch = FetchType.EAGER,
            mappedBy = "mainCategoryList",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    List<SubCategoryEntity> subCategoryList;
}
