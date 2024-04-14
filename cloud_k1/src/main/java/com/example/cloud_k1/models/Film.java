package com.example.cloud_k1.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private Long year;
    @Transient
    @Value("${datasource.movie-table-name}")
    private String tableName;

    @PostLoad
    public void setTableName() {
        this.tableName = tableName;
    }
}