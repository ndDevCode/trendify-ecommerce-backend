package edu.icet.trendify.entity.inventory;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "size")
public class SizeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String size;

    @OneToMany(mappedBy = "size")
    @ToString.Exclude
    private List<SubInventoryEntity> subInventory;
}
