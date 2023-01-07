package com.shorbgy.composenoteapp.feature_note.presentation.notes.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shorbgy.composenoteapp.feature_note.domain.model.Note
import com.shorbgy.composenoteapp.feature_note.presentation.notes.NotesEvent
import com.shorbgy.composenoteapp.feature_note.presentation.notes.NotesViewModel
import com.shorbgy.composenoteapp.ui.theme.DarkGray

@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    viewModel: NotesViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                backgroundColor = MaterialTheme.colors.background
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
            }
        },
        scaffoldState = scaffoldState
    ) { padding ->
        modifier.padding(padding)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your Notes",
                    style = MaterialTheme.typography.h4
                )
                IconButton(onClick = {
                    viewModel.onEvent(NotesEvent.ToggleOrdersSection)
                }) {
                    Icon(imageVector = Icons.Filled.Settings, contentDescription = "Sort")
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    noteOrder = state.noteOrder,
                    onOrderChange = {
                        viewModel.onEvent(NotesEvent.Order(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(dummyNotes()){
                    NoteItem(
                        modifier = Modifier.padding(vertical = 8.dp),
                        note = it,
                        color = Note.noteColors[it.color]
                    ){
                        
                    }
                }
            }
        }
    }

}

fun dummyNotes(): List<Note>{
    return listOf(
        Note(1, "1", "alkm lksm lskm lsm lmsdl ", 200, 0),
        Note(1, "1", "alkm lksm lskm lsm lmsdl ", 200, 1),
        Note(1, "1", "alkm lksm lskm lsm lmsdl ", 200, 2),
        Note(1, "1", "alkm lksm lskm lsm lmsdl ", 200, 3),
        Note(1, "1", "alkm lksm lskm lsm lmsdl ", 200, 4),
        Note(1, "1", "alkm lksm lskm lsm lmsdl ", 200, 0),
        Note(1, "1", "alkm lksm lskm lsm lmsdl ", 200, 0),
        Note(1, "1", "alkm lksm lskm lsm lmsdl ", 200, 0),
        Note(1, "1", "alkm lksm lskm lsm lmsdl ", 200, 0),
        Note(1, "1", "alkm lksm lskm lsm lmsdl ", 200, 0)
    )
}
