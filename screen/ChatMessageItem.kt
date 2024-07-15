package com.example.chatbotapp.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatbotapp.R
import com.example.chatbotapp.data.Message
import com.example.chatbotapp.data.formatTimestamp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatMessageItem(message: Message){

    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    val messageMaxWidth = with(LocalDensity.current) { screenWidthDp.dp * 0.65f }
    var textColor  by remember{mutableStateOf(Color.Black)}
    var messageBgColor by remember { mutableStateOf(Color.Gray) }
    var messageShape by remember{ mutableStateOf(RoundedCornerShape(30, 30, 30, 0)) }

    if(message.isSentByCurrentUser)
    {
        textColor = Color.White
        messageBgColor = colorResource(id = R.color.purple_700)
        messageShape = RoundedCornerShape(30, bottomEndPercent = 0, bottomStartPercent = 30, topEndPercent = 30)
    }

    else
    {
        textColor=Color.Black
        messageBgColor=Color.Gray
        messageShape=RoundedCornerShape(30, 30, 30, 0)
    }

    Column(
        modifier= Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = if(message.isSentByCurrentUser)Alignment.End else Alignment.Start
    ){
        Box(
            modifier= Modifier
                .background(messageBgColor, shape = messageShape)
                .padding(8.dp)
                .widthIn(max = messageMaxWidth)
        ){
            Text(text = message.text,
                color=textColor,
                fontSize = 16.sp,modifier=Modifier.padding(4.dp))
        }

        Spacer(Modifier.height(4.dp))

        Text(text = message.senderFirstName,
            color=Color.Gray,
            fontSize = 12.sp)

        Text(
            text = formatTimestamp(message.timestamp), // Replace with actual timestamp logic
            style = TextStyle(
                fontSize = 12.sp,
                color = Color.Gray
            )
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ChatMessageItemPreview(){
    val message = Message("Bhuvansh",text="the  v k vk vk m k mk mvk mvk mvk mvk mvk mkv mkv mvk mvk mvk mfirst text message", isSentByCurrentUser = false)
    ChatMessageItem(message = message)
}