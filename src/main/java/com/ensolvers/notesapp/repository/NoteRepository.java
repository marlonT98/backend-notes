package com.ensolvers.notesapp.repository;

import com.ensolvers.notesapp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note , Long> {

    //devuelve las notas activas
    List<Note> findByArchivedFalse();

    //retorna las notas archivadas
    List<Note> findByArchivedTrue();


    List<Note> findByArchivedFalseAndCategory(String category);
    List<Note> findByArchivedTrueAndCategory(String category);




}
