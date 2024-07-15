package com.example.chatbotapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatbotapp.data.Room
import com.example.chatbotapp.viewmodel.RoomViewModel

@Composable
fun ChatRoomListScreen(
    roomViewModel: RoomViewModel = viewModel(),
    onJoinClicked:(Room)->Unit
){

    var showDialog by remember{ mutableStateOf(false) }
    var name by remember{ mutableStateOf("") }

    val rooms by roomViewModel.rooms.observeAsState(emptyList())

    Column(modifier= Modifier
        .fillMaxSize()
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally){

        Text("Chat Rooms", fontWeight = FontWeight.ExtraBold, fontSize = 30.sp,style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(16.dp))

        LazyColumn(modifier=Modifier.fillMaxWidth())
        {
            items(rooms){room->
                RoomItem(room = room,onJoinClicked={onJoinClicked(room)})
//                RoomItem(room = room)
            }
        }

        //create new room button
        Button(onClick = {
            roomViewModel.loadRooms()
            showDialog =true },
            Modifier.fillMaxWidth()) {
            Text("Create Room")
        }
    }

    //dialog to create new room
    if(showDialog)
    {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween){
                    
                    Button(onClick = {
                        if(name.isNotBlank()) {
                            roomViewModel.createRoom(name)
                            showDialog = false
                            name=""
                        }
                    }) {
                        Text("Add")
                    }
                    
                    Button(onClick = {
                        showDialog=false
                        name=""
                    }) {
                        Text(text = "Cancel")
                    }
                }
            },
            title = {
                Row(modifier= Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                    horizontalArrangement = Arrangement.Center)
                {
                    Text(text = "Creat a new Room")
                }
                    },
            text = {
                OutlinedTextField(value = name,
                    onValueChange = { name = it },
                    label = {Text("Room Name")})
            },
            shape = RoundedCornerShape(10)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatRoomListScreenPreview(){
    ChatRoomListScreen(){}
}
