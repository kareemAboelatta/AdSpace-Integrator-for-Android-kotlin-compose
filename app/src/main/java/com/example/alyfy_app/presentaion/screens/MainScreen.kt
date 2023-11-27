package com.example.alyfy_app.presentaion.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.alyfy_app.presentaion.AdViewModel
import com.example.alyfy_app.presentaion.components.GamifyAdIcon
import com.example.alyfy_app.presentaion.components.RoulaxView


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    adViewModel: AdViewModel
) {
    val pagerState = rememberPagerState()
    val companies = listOf("OKSpin", "Roulax") // List of company names

    Scaffold(
        topBar = { MyAppBar() }
    ) {padding->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            HorizontalPager(
                state = pagerState,
                pageCount = companies.size,
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(horizontal = 100.dp),
                verticalAlignment = Alignment.CenterVertically
            ) { page ->
                when (page) {
                    0 -> GamifyAdIcon() // OKSpin
                    1 -> RoulaxView() // Roulax
                }
            }


            //For name of current Company
            HorizontalPager(
                state = pagerState,
                pageCount = companies.size,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                Text(
                    text = companies[pagerState.currentPage],
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }


        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = "Swipe Horizontal too see another Ad Company",
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color.White,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    )
}


