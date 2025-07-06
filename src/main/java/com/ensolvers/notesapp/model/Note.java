package com.ensolvers.notesapp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 5000)
    private String content;

    private boolean archived = false;
    private String category="otros";
    private LocalDateTime creartedAt;
    private LocalDateTime updatedAt;

    //actualizamos las fechas automaticamente

    @PrePersist
    protected void onCreate( ){
        this.creartedAt = LocalDateTime.now();
        this.updatedAt = this.creartedAt;
    }

    @PreUpdate
    protected void onUpdate( ){
        this.updatedAt = LocalDateTime.now();
    }









}
