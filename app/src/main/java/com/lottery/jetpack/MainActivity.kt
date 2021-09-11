package com.lottery.jetpack

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lottery.jetpack.ui.theme.JetpackTheme
import com.lottery.jetpack.ui.theme.ShapesOthers
import java.io.File

class MainActivity : ComponentActivity() {

    private val mainPath: String = Environment.getExternalStorageDirectory().path

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackTheme {
                MessageCard(Message("Ramirez ", "Que se dicee"))
            }
            /*JetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    val listFiles = File(mainPath).listFiles()

                    val list: List<File> = listFiles.toMutableList()

                    Conversation(list,this)
                }
            }*/
        }
    }

    data class Message(val author: String, val msg: String)

    @Composable
    fun MessageCard(msg: Message) {
        //add padding around our message
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(R.drawable.profile_picture),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    //set img size to 40dp
                    .size(40.dp)
                    //clip img to be shaped as a circle
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )
        // add a horizontal space between the img and the column
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(text = msg.author,
                color = MaterialTheme.colors.secondaryVariant)
                //add a vertical space between the author and message texts
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = msg.msg,
                color = Color.White)
            }
        }
    }

    //preview no puede tener parametros
    @Preview
    @Composable
    fun PreviewMessageCard() {
        JetpackTheme {
            MessageCard(
                msg = Message("Colleague", "sap")
            )
        }
    }

    @Composable
    fun Conversation(listFile: List<File>, context: Context) {
        LazyColumn {
            items(listFile) { file ->
                ItemFile(file = file, context = context)
            }
        }
    }

    @Composable
    fun ItemFile(file: File, context: Context) {
        Row {
            Image(
                painter = painterResource(R.drawable.folder_),
                contentDescription = "Contact profile picture",
                Modifier
                    .size(75.dp, 75.dp)
                    .padding(10.dp)
            )
            Column {
                Text(text = file.name, style = MaterialTheme.typography.h6)
                Text(text = file.absolutePath)
                OutlinedButton(
                    onClick = {
                        Toast.makeText(context, "Hi ${file.name}", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.textButtonColors(),
                    shape = ShapesOthers.small
                ) {
                    Text("Button")
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        val context = LocalContext.current
        JetpackTheme {
            ItemFile(file = File("file.txt"), context)
        }
    }
}