
package com.example.madarandroid.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
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
    fun DetailsScreen(viewModel:UserViewModel) {

    val list by viewModel.usersList.collectAsStateWithLifecycle()


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