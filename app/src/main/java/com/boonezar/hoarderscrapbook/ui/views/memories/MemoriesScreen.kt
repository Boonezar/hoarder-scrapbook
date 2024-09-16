package com.boonezar.hoarderscrapbook.ui.views.memories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.boonezar.hoarderscrapbook.R
import com.boonezar.hoarderscrapbook.models.Memory
import com.boonezar.hoarderscrapbook.ui.composables.PrimaryButton
import com.boonezar.hoarderscrapbook.ui.composables.ScreenHeader
import com.boonezar.hoarderscrapbook.ui.theme.HoarderScrapbookTheme
import com.boonezar.hoarderscrapbook.ui.views.memories.MemoriesContract.*

@Composable
fun MemoriesScreen(
    viewState: androidx.compose.runtime.State<State>,
    onEvent: (event: Event) -> Unit
) {
    val state = viewState.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        ScreenHeader(
            title = stringResource(id = R.string.memories),
            onBack = { onEvent(Event.OnBack) }
        )
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(
                items = state.memories,
                key = { it.id }
            ) { memory ->
                MemoryListItem(memory = memory, onClick = { onEvent(Event.OnMemory(memory.id)) })
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton(
            text = stringResource(id = R.string.add_memory),
            onClick = { onEvent(Event.OnAddMemory) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
private fun MemoryListItem(memory: Memory, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable { onClick() }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(vertical = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(MaterialTheme.colorScheme.primary)
            ) {}
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = memory.name)
                Text(text = memory.description)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(text = memory.estimateDateOfMemory)
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MemoriesScreenPreview() {
    HoarderScrapbookTheme {
        val viewState = remember { mutableStateOf(State(listOf(
            Memory(id = 0, entryDate = "", estimateDateOfMemory = "03/03/1993", name = "Birth", description = "I was born."),
            Memory(id = 1, entryDate = "", estimateDateOfMemory = "03/03/1993", name = "Birth", description = "I was born."),
            Memory(id = 2, entryDate = "", estimateDateOfMemory = "03/03/1993", name = "Birth", description = "I was born."),
            Memory(id = 3, entryDate = "", estimateDateOfMemory = "03/03/1993", name = "Birth", description = "I was born."),
            Memory(id = 4, entryDate = "", estimateDateOfMemory = "03/03/1993", name = "Birth", description = "I was born."),
            Memory(id = 5, entryDate = "", estimateDateOfMemory = "03/03/1993", name = "Birth", description = "I was born."),
            Memory(id = 6, entryDate = "", estimateDateOfMemory = "03/03/1993", name = "Birth", description = "I was born."),
        )))}
        MemoriesScreen(viewState = viewState, onEvent = { /* no-op */ })
    }
}