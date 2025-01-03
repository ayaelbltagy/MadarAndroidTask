
package com.example.madarandroid.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.madarandroid.ui.theme.MadarANDROIDTheme
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.room.Entity
import com.example.madarandroid.data.data.entity.UserEntity


@Composable
fun myView(viewModel:UserViewModel) {
    val list by viewModel.usersList.collectAsStateWithLifecycle()
    Column(Modifier.fillMaxSize()) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp)
    ) {
        items(
            count = list.size,
            key = { index -> list[index].id },
            itemContent = { index ->
                val item = list[index]
                listItem(item)
            }
        )
    }
        }




}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun DetailsScreen(viewModel:UserViewModel) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Madar") },
                modifier = Modifier.background(MaterialTheme.colorScheme.primary) // Modifier applied properly
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()) {
                myView( viewModel)
            }

        }
    )

}



@Composable
fun listItem(item :UserEntity
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = "User Name "+" "+item.userName,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text ="User Gender"+" "+item.userGender,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text ="User Age"+" "+item.userAge,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text ="User Job"+"" +item.userJobTitle,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MadarANDROIDTheme {
        NavigationStack()
    }
}