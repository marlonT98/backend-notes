package com.ensolvers.notesapp.controller;


import com.ensolvers.notesapp.model.Note;
import com.ensolvers.notesapp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/notes")
@CrossOrigin(origins = "*")//hailitamos el acceso desde el front
public class NoteController {

    //inyectamos nuestro servicio
    @Autowired
    private NoteService noteService;



    //creamos o actualizamos una nota
    @PostMapping
    public ResponseEntity<Note> saveNote( @RequestBody Note note   ){
        Note saveNote = noteService.saveNote( note );
        return  ResponseEntity.ok( saveNote );

    }

    //obtenemos una nota por su id
    @GetMapping("/{id}")
    public ResponseEntity< Note> getNoteId(@PathVariable Long id   ){
        return noteService.getNoteById( id)
                .map( ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());


    }

    //listar las notas activas
    @GetMapping("/active")
    public List<Note> getActiveNotes( ){
        return noteService.getActiveNotes();

    }

    //listar las notas archivadas
    @GetMapping("/archived")
    public List<Note> getArchivedNotes( ){
        return noteService.getArchiveNotes();

    }

    //archivar o desarchivar notas
    @PutMapping("/{id}/toggle-archive")
    public ResponseEntity<Void>toggleArchive( @PathVariable Long id  ){

        noteService.toggleArchive(id);
        return ResponseEntity.noContent().build();
    }

    //eliminar la nota
    @DeleteMapping("/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable Long id  ){
        noteService.deleteNote(id);
        return  ResponseEntity.noContent().build();

    }


    @GetMapping("/active/category/{category}")
    public List<Note> getActiveNotesByCategory(@PathVariable String category) {
        return noteService.getActiveNotesByCategory(category);
    }

    @GetMapping("/archived/category/{category}")
    public List<Note> getArchivedNotesByCategory(@PathVariable String category) {
        return noteService.getArchivedNotesByCategory(category);
    }












}
