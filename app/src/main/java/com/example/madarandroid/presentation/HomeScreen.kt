@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.madarandroid.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.madarandroid.data.data.entity.UserEntity
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen() {

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
                 val viewModel = hiltViewModel<UserViewModel>()
                 Content(viewModel = viewModel)
           //     Text("This is the Main Body of the Application", modifier = Modifier.align(Alignment.Center))
          }

        }
    )


}

@Composable
fun Content(viewModel: UserViewModel) {

    LaunchedEffect(key1 = true, block = {
        viewModel.getUsers()
    })

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f), contentAlignment = Alignment.TopCenter
        ) {
            TopContent(viewModel = viewModel)
        }

    }
}

@Composable
fun TopContent(
    viewModel: UserViewModel
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val name by viewModel.userName.collectAsStateWithLifecycle()

        val onNameEntered: (value: String) -> Unit = remember {
            return@remember viewModel::setUserName
        }

        val onSubmit: (value: UserEntity) -> Unit = remember {
            return@remember viewModel::insertUser
        }
        OutlinedTextField(
            value = name,
            onValueChange = {
                onNameEntered(it)
            },
            placeholder = {
                Text(text = "User name")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(), maxLines = 1
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {
                onNameEntered(it)
            },
            placeholder = {
                Text(text = "User age")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(), maxLines = 1
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {
                onNameEntered(it)
            },
            placeholder = {
                Text(text = "User job title")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(), maxLines = 1
        )
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {
                onNameEntered(it)
            },
            placeholder = {
                Text(text = "User gender")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(), maxLines = 1
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton(onClick = {
            onSubmit(
                UserEntity(
                    userName = name,
                    userAge = 1 ,
                    userJobTitle = "",
                    userGender= ""
                )
            )
        }) {
            Text(text = "Submit")
        }

    }
}

