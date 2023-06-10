package com.rovlkr.documentbase.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "tag",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id", "name"})
)
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    private long id;

    @Column
    private String name;
}
