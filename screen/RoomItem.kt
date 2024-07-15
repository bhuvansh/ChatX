package com.example.chatbotapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatbotapp.data.Room

@Composable
fun RoomItem(
    room: Room,
    onJoinClicked:(Room)->Unit){
    Row(modifier= Modifier
        .fillMaxWidth()
        .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween){
        Text(text = room.name,modifier=Modifier.padding(8.dp).align(Alignment.CenterVertically))
        Button(onClick = { onJoinClicked(room)},
            shape = RoundedCornerShape(50),
            modifier = Modifier.padding(8.dp)) {
            Text("Join")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoomItemPreview()
{
    RoomItem(room = Room("1","PUBG")){}
}