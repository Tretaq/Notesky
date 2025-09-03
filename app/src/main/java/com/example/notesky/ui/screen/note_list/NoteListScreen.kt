package com.example.notesky.ui.screen.note_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextOverflow
import com.example.notesky.domain.model.Note

@Composable
fun NoteListScreen(
    noteList: List<com.example.notesky.domain.model.Note>,
    onNoteClick: (com.example.notesky.domain.model.Note) -> Unit,
    onAddNoteClick: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {onAddNoteClick()}) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add Note"
                )
            }
        }
    ) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 15.dp + padding.calculateTopPadding(),
                bottom = 15.dp + padding.calculateBottomPadding()
            )
        ) {
            item {
                Text(
                    text = "Notes",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            items(noteList) { note ->
                ListItem(
                    headlineContent = {
                        Text(
                            text = note.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    supportingContent = {
                        Text(
                            text = note.content,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    modifier = Modifier.clickable(onClick =  { onNoteClick(note) })
                )
            }
        }
    }
}