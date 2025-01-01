@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.madarandroid.presentation

import android.util.Log
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.madarandroid.data.data.entity.UserEntity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.compose.material3.Button
import androidx.compose.runtime.*




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel:UserViewModel,navController: NavController) {

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
                  Content(navController,viewModel = viewModel)
          }

        }
    )


}

@Composable
fun Content(navController: NavController,viewModel: UserViewModel) {

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
            TopContent(navController,viewModel = viewModel)
        }

    }
}

@Composable
fun TopContent(navController: NavController,
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

        val age by viewModel.userAge.collectAsStateWithLifecycle()
        val onAgeEntered: (value: String) -> Unit = remember {
            return@remember viewModel::setUserAge
        }

        val job by viewModel.userJob.collectAsStateWithLifecycle()
        val onJobEntered: (value: String) -> Unit = remember {
            return@remember viewModel::setUserJob
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
        val pattern = remember { Regex("^\\d+\$") }

        OutlinedTextField(
            value = age,
            onValueChange = {
                if (it.isEmpty() || it.matches(pattern)) {
                    onAgeEntered(it)
                }
            },
             placeholder = {
                Text(text = "User age")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(), maxLines = 1
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value =job,
            onValueChange = {
                onJobEntered(it)
            },
            placeholder = {
                Text(text = "User job title")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(), maxLines = 1
        )
        Spacer(modifier = Modifier.height(15.dp))

        var expanded by remember { mutableStateOf(false) }
        val options = listOf("Male", "Female")
        var selectedOptionText by remember { mutableStateOf(options[0]) }

        Box {
            Button(onClick = { expanded = !expanded }) {
                Text(selectedOptionText)
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                options.forEach { label ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = label
                            expanded = false

                        },
                        text = { Text( label) }
                    )

                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(onClick = {
            if(name.isNotEmpty()){
                onSubmit(
                    UserEntity(
                        userName = name,
                        userAge = age,
                        userJobTitle = job,
                        userGender= selectedOptionText
                    )
                )
            }

            navController.navigate(route = Screen.Detail.route)

            Log.i("mydb", viewModel.usersList.value.size.toString())

        }) {
            Text(text = "Submit")
        }

    }
}

