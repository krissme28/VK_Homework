package com.example.vk_homework.appdetails.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.vk_homework.R

@Composable
fun AppDetailsScreen(
    viewModel: AppDetailsViewModel,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val underDevelopmentText = stringResource(R.string.under_developement)
    val scrollState = rememberScrollState()
    var descriptionCollapsed by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            state.error != null -> {
                Text(
                    text = state.error ?: "Unknown error",
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error
                )
            }
            state.appDetails != null -> {
                val details = state.appDetails!!

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .safeDrawingPadding()
                ) {
                    Toolbar(
                        onBackClick = onBackClick,
                        onShareClick = {
                            Toast.makeText(context, underDevelopmentText, Toast.LENGTH_SHORT).show()
                        },
                        onWishlistClick = { viewModel.toggleWishlist() },
                        isInWishlist = details.isInWishlist
                    )

                    Spacer(Modifier.height(8.dp))

                    AppDetailsHeader(
                        appDetails = details,
                        modifier = Modifier.padding(horizontal = 16.dp),
                    )

                    Spacer(Modifier.height(16.dp))

                    InstallButton(
                        onClick = {
                            Toast.makeText(context, underDevelopmentText, Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )

                    Spacer(Modifier.height(12.dp))

                    ScreenshotsList(
                        screenshotUrlList = details.screenshotUrlList,
                        contentPadding = PaddingValues(horizontal = 16.dp),
                    )

                    Spacer(Modifier.height(12.dp))

                    AppDescription(
                        description = details.description,
                        collapsed = descriptionCollapsed,
                        onReadMoreClick = { descriptionCollapsed = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                    )

                    Spacer(Modifier.height(12.dp))

                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.outlineVariant,
                    )

                    Spacer(Modifier.height(12.dp))

                    AppDeveloperRow(
                        developer = details.developer,
                        onClick = {
                            Toast.makeText(context, underDevelopmentText, Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, bottom = 24.dp, end = 16.dp),
                    )
                }
            }
        }
    }
}