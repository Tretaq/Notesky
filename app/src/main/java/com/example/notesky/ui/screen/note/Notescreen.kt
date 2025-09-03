package com.example.notesky.ui.screen.note

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notesky.domain.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    state: NoteState,
    onEvent: (NoteEvent) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { /* To Do */ },
                navigationIcon = {
                    IconButton(onClick = { onEvent(NoteEvent.NavigateBack) } ){
                        Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack , contentDescription = "navigate back" )

                    }
                },
                actions = {
                    IconButton(onClick = { onEvent(NoteEvent.Delete)}) {
                        Icon(imageVector = Icons.Rounded.Delete , contentDescription = "Delete")
                    }
                }

            )
        }
    ) { padding ->
        Column(modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp, vertical = 15.dp),
            verticalArrangement = spacedBy(10.dp)

        ) {
                OutlinedTextField(value = state.title, onValueChange =  {
                    onEvent(NoteEvent.TitleChange(it))
                },
                    placeholder = {
                        Text(text = "Title")
                    }
            )
            OutlinedTextField(value = state.content, onValueChange =  {
                onEvent(NoteEvent.ContentChange(it))
            },
                placeholder = {
                    Text(text = "Content")
                }

            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Button(onClick = {
                    onEvent(NoteEvent.Save)
                }, modifier = Modifier.fillMaxWidth(0.5f)

                ) {
                    Text(text = "Save")
                }
            }
        }

    }
}