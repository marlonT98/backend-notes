package com.ensolvers.notesapp.service;

import com.ensolvers.notesapp.model.Note;
import com.ensolvers.notesapp.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {


    //inyectamos
    @Autowired
    private NoteRepository noteRepository;


    //crear o actualiza una nota
    public Note saveNote(Note note ){
        return noteRepository.save( note);
    }


    //obtenemos la nota por su id
    public Optional<Note> getNoteById(Long id   ){

        return noteRepository.findById( id );
    }

    //listar  las notas activas
    public List<Note> getActiveNotes(){
        return noteRepository.findByArchivedFalse();

    }
    //listar notas archivadas
    public List<Note> getArchiveNotes(){
        return noteRepository.findByArchivedTrue();
    }

    //eliminar una nota
    public void deleteNote( Long id){
        noteRepository.deleteById(id );
    }


  //archivar o desarchivar una  nota
    public void toggleArchive( Long id  ){

        Optional< Note>  optionalNote = noteRepository.findById( id ) ;

        //si la nota existe
        if ( optionalNote.isPresent()  ){
            Note note = optionalNote.get();
            note.setArchived( !note.isArchived() );//invertimos el valor de false a true
            noteRepository.save( note) ;

        }

    }

    public List<Note> getActiveNotesByCategory(String category) {
        return noteRepository.findByArchivedFalseAndCategory(category);
    }

    public List<Note> getArchivedNotesByCategory(String category) {
        return noteRepository.findByArchivedTrueAndCategory(category);
    }




}
