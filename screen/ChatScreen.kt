package com.example.chatbotapp.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatbotapp.viewmodel.MessageViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatScreen(roomId : String,
               roomName:String,
               messageViewModel:MessageViewModel = viewModel())
{
    messageViewModel.setUser()
    val text = remember{ mutableStateOf("") }

//    messageViewModel.loadMessages()
    val messages by messageViewModel.messages.observeAsState(emptyList())

    //setting the current roomId to the room which we have joined
    messageViewModel.setRoomId(roomId)

    println("Current user is ${messageViewModel.currentUser.value}")
    println(messages)

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){

        Text(text = "$roomName", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold)
        Spacer(Modifier.height(8.dp))
        LazyColumn(
            modifier=Modifier.weight(1f),
            reverseLayout = true,
        ){
            messageViewModel.setUser()
            items(messages.asReversed()){message->
                messageViewModel.setUser()
                ChatMessageItem(message = message.copy(
                    isSentByCurrentUser = message.senderId == messageViewModel.currentUser.value?.email ))
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(value = text.value,
                onValueChange = {text.value = it},
                modifier= Modifier
                    .weight(1f)
                    .padding(8.dp),
                shape = RoundedCornerShape(25),
                maxLines = 5,
            )

            //send button
            IconButton(onClick = {
                if(text.value.isNotEmpty()){
                    println("BEFORE SEND MESSAGE CALL")
                    messageViewModel.sendMessage(text.value.trim())
                    println("AFTER SEND MESSAGE CALL")
                    text.value=""
                }
                messageViewModel.loadMessages()
            }) {
                Icon(Icons.Default.Send,null)
            }
        }
    }
}
