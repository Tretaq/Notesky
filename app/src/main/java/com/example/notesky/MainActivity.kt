
package com.example.notesky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesky.ui.theme.NoteskyTheme
import com.example.notesky.ui.util.Route
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.notesky.ui.screen.note.NoteEvent
import com.example.notesky.ui.screen.note.NoteScreen
import com.example.notesky.ui.screen.note.NoteVievModel
import com.example.notesky.ui.screen.note_list.NoteListScreen
import com.example.notesky.ui.screen.note_list.NoteListViewModel
import com.example.notesky.ui.util.UiEvent


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteskyTheme {
                val navController = rememberNavController()

                NavHost(navController = navController,startDestination = Route.noteList ){
                    composable(route  = Route.noteList ){
                        val viewModel = hiltViewModel<NoteListViewModel>()
                        val noteList  by viewModel.noteList.collectAsStateWithLifecycle()

                        NoteListScreen(
                            noteList = noteList,
                            onNoteClick = {
                                navController.navigate(
                                    Route.note.replace(
                                        "{id}", it.id.toString()
                                    )
                                )
                            },
                            onAddNoteClick = {
                                    navController.navigate(Route.note)
                    }

                        )
                    }
                    composable(route = Route.note){
                        val vievModel = hiltViewModel<NoteVievModel>()
                        val state by vievModel.state.collectAsStateWithLifecycle()

                        LaunchedEffect(key1 = true){
                            vievModel.event.collect{ event ->
                                when(event){
                                    is UiEvent.NavigateBack -> {
                                        navController.popBackStack()
                                    }
                                    else -> Unit
                                }
                            }
                        }

                        NoteScreen(
                            state = state,
                            onEvent = vievModel::onEvent
                        )
                    }
                }

            }
        }
    }
}

//  https://www.youtube.com/watch?v=VQMnFW2RzCE