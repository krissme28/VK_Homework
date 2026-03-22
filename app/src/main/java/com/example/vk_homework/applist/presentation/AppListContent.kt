package com.example.vk_homework.applist.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.vk_homework.applist.domain.AppListItem

@Composable
fun AppListContent(
    appList: List<AppListItem>,
    innerPadding: PaddingValues,
    onClick: (String) -> Unit,
    onLogoClick: (AppListItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        color = Color.White
    ) {
        LazyColumn(
            contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
        ) {
            itemsIndexed(appList) { index, appListItem ->
                AppListItemCard(
                    appListItem,
                    onClick = onClick,
                    onLogoClick = { onLogoClick(appListItem) }
                )

                if (index < appList.lastIndex) {
                    HorizontalDivider(
                        thickness = 0.5.dp,
                        modifier = Modifier.padding(start = 88.dp, end = 16.dp),
                        color = Color.LightGray.copy(alpha = 0.5f)
                    )
                }
            }
        }
    }
}