package com.shorbgy.composenoteapp.di

import android.app.Application
import androidx.room.Room
import com.shorbgy.composenoteapp.feature_note.data.data_source.NoteDatabase
import com.shorbgy.composenoteapp.feature_note.data.repository.NoteRepositoryImpl
import com.shorbgy.composenoteapp.feature_note.domain.repository.NoteRepository
import com.shorbgy.composenoteapp.feature_note.domain.use_case.AddNoteUseCase
import com.shorbgy.composenoteapp.feature_note.domain.use_case.DeleteNoteUseCase
import com.shorbgy.composenoteapp.feature_note.domain.use_case.GetNotesUseCase
import com.shorbgy.composenoteapp.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(application: Application): NoteDatabase{
        return Room.databaseBuilder(
            application,
            NoteDatabase::class.java,
            NoteDatabase.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository{
        return NoteRepositoryImpl(db.noteDoa)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases{
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            addNoteUseCase = AddNoteUseCase(repository)
        )
    }
}