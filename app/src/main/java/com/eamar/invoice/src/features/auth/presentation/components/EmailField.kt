package com.eamar.invoice.src.features.auth.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eamar.invoice.src.features.auth.presentation.AuthViewModel

@Composable
fun EmailField(

    value: String,onValueChange: (String) -> Unit ,
    placeholder: String ,

    isPassword:Boolean = false ,
    authViewModel: AuthViewModel
){
//    TextField(
//        value = value,
//        onValueChange = { textFieldValue -> onValueChange(textFieldValue) },
//        placeholder = { Text(placeholder, color = MaterialTheme.colors.secondary) }
//    )
Column(
    horizontalAlignment = Alignment.Start
) {
    TextField(
        modifier = Modifier
//               . defaultMinSize(minHeight = 40.dp)
            .height(42.dp)

            .fillMaxWidth(),
        value = value,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0xFFf4f4f4),
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),

        onValueChange =  { textFieldValue -> onValueChange(textFieldValue) },

        textStyle = LocalTextStyle.current.copy(
            color = MaterialTheme.colors.onSurface,
            fontSize = 8.sp
        ),
        placeholder = { Text(placeholder,



            style = TextStyle(
                fontSize=8.sp ,
                color = Color(0xFFbcbcbc)
            )

        ) },
        shape = RoundedCornerShape(8.dp),
        singleLine = true,



        )

    Text(
        modifier = Modifier.padding(start = 8.dp),
        text =  authViewModel.emailErrMsg.value
        ,
        fontSize = 8.sp,
        color = Color.Red
    )
}

}