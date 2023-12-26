package com.example.educationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.educationapp.ui.theme.EducationAppTheme

class MainActivity : ComponentActivity() {

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EducationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MainContent() {
    val genres = listOf("Brainstorm","Books","Movies","Other")

    val rec1 = Recommend("Chatting","5 minutes",R.drawable.ic_chatting, colorResource(id = R.color.pinkRed))
    val rec2 = Recommend("Listen","3 minutes",R.drawable.ic_listen, colorResource(id = R.color.purple_500))
    val rec3 = Recommend("Speak","2 minutes",R.drawable.ic_speak, colorResource(id = R.color.orange))
    val recommends = listOf<Recommend>(rec1,rec2,rec3)

    Scaffold {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(it)
        ) {
            Text(fontSize = 40.sp,
                modifier = Modifier
                    .padding(bottom = 25.dp)
                    .padding(horizontal = 20.dp),
                text = buildAnnotatedString {
                append("Choose What \n\n")
                withStyle(style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ))
                {
                    append("to Learn Today?")
            } })

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(bottom = 25.dp)
                    .padding(horizontal = 20.dp)
                    .clipToBounds()
            ){
                items(genres){
                    Genre(text = it)
                }
            }

            Box (modifier = Modifier
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(10))
                .background(colorResource(id = R.color.purple_700))){

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier
                        .padding(vertical = 30.dp)
                        .padding(start = 20.dp))
                    {
                        Text(text = "Vocabulary",
                            color = Color.White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold)

                        Spacer(modifier = Modifier.size(10.dp))

                        Text(text = "Listen to 20 new words",
                            color = Color.White,
                            fontSize = 20.sp)

                        Spacer(modifier = Modifier.size(10.dp))

                        Button(
                            onClick = {
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White
                            ))
                        {
                            Text(text = "Start", color = Color.Black, fontSize = 18.sp)
                            Spacer(modifier = Modifier.size(10.dp))
                            Box (
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                                    .background(Color.Black),
                                contentAlignment = Alignment.Center
                            ){
                                Icon(
                                    modifier = Modifier.padding(5.dp),
                                    imageVector = Icons.Filled.PlayArrow,
                                    contentDescription = "Play Icon",
                                    tint = Color.White
                                )
                            }
                        }
                    }

                    Image(painterResource(id = R.drawable.brainy),
                        contentDescription = "avatar image?",
                        modifier = Modifier.weight(1f , fill = false))
                }
            }

            Text(text = "Recommended",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp))

            LazyColumn(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                items(recommends){
                    Recommend(it)
                }
            }
        }


    }
}

@Composable
fun Recommend(recommend: Recommend) {
    var isSelected by remember { mutableStateOf(false) }
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20))
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(20))
                    .background(recommend.backgroundColor),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painterResource(id = recommend.image ),
                    contentDescription = "icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(6.dp))
            }

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(text = recommend.name,
                    color = Color.Black,
                    fontSize = 18.sp,
                )

                Text(text = recommend.duration,
                    color = Color.DarkGray,
                    fontSize = 14.sp,
                )

            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = {
                isSelected = !isSelected
            }) {
                Icon(imageVector = Icons.Filled.Favorite,
                    contentDescription = "icon",
                    tint =  if(isSelected) recommend.backgroundColor else colorResource(id = R.color.seal),
                    modifier = Modifier.size(28.dp)

               )
            }
        }
    }
}

@Composable
fun Genre(text: String) {
    var isSelected by remember { mutableStateOf(false) }
    Box (modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .clipToBounds()
        .background(if (isSelected) colorResource(id = R.color.purple_500) else Color.LightGray)
        .clickable {
            isSelected = !isSelected
        }, contentAlignment = Alignment.Center
    ){
        Text(text = text,
            modifier = Modifier.padding(10.dp),
            color = if(isSelected) Color.White else Color.Black,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EducationAppTheme {
        MainContent()
    }
}