package com.eamar.invoice.src.features.invoice.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eamar.invoice.R
import com.eamar.invoice.src.utils.Constants
import java.io.File


@Composable
fun FilesPage(navController: NavController ,
              mainNavController:NavController){
    var files  = listOf<FileData>(
        FileData(
            name = "Invoice 1223" ,

        ),
        FileData(
            name = "Invoice 1223" ,

            )
    ,
        FileData(
            name = "Invoice 1223" ,

            ),
        FileData(
            name = "Invoice 1223" ,

            )
    )
    Box(modifier = Modifier.fillMaxSize()) {
    LazyColumn(content ={
items(files.size)
{
    FileContainer(fileData = files[it])
}

    } )


    }
}

@Composable
fun FileContainer(fileData:FileData){
   Surface(elevation = 5.dp  ) {
       Box(
           modifier = Modifier
               .padding(5.dp)
               .fillMaxWidth()
               .height(60.dp)
               .background(
                   color = Color(
                       0xFFf5f5f7,


                       )
               )
               .padding(5.dp)
       ) {
         Row  (     
             horizontalArrangement = Arrangement.SpaceEvenly
                 ) {
             
             
             
             Row (    ){
                 Image(painter = painterResource(id =
                 R.drawable.ic_pdf

                 )


                     ,
                     contentDescription =null ,

                     modifier = Modifier

                         .width(30.dp)
                         .height(30.dp)
             

                     )
                 Column(  horizontalAlignment = 
                 
                 Alignment.Start
                 ) {
                     Text(text = fileData.name)
                     Text(text = fileData.date ,
                     
                         style = TextStyle(
                             fontWeight = FontWeight.W300
                         )
                         )
                     
                 }
                 
                 
                 
             }





             Row (     ){
                 Icon(Icons.Default.Share , contentDescription ="" )



                 Icon(painter = painterResource(id = R.drawable.ic_printer) , contentDescription ="" )
             }

         }
       }
   }
}
data class FileData (
    var name:String ,
    var date:String ="1/1/2003" ,

        )