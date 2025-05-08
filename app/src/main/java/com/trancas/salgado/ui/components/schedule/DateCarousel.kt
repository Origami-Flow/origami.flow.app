package com.trancas.salgado.ui.components.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate

@Composable
fun DateCarousel(
    selectedDate: LocalDate = LocalDate.now(),
    onDateSelected: (LocalDate) -> Unit = {},
    dates: List<LocalDate>
) {
    val today = LocalDate.now()

    val listState = rememberLazyListState()
    val todayIndex = today.dayOfMonth - 1

    LaunchedEffect(Unit) {
        snapshotFlow { listState.layoutInfo.totalItemsCount }
            .collectLatest { totalItems ->
                if (totalItems > todayIndex) {
                    listState.animateScrollToItem(todayIndex)
                }
            }
    }

    LazyRow(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(dates) { date ->
            BubbleDay(
                date = date,
                isSelected = date == selectedDate,
                onClick = { onDateSelected(date) }
            )

        }
    }
}

