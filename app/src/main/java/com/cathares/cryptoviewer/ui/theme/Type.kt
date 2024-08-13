package com.cathares.cryptoviewer.ui.theme

import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cryptoviewer.R

val robotoFamily = FontFamily(
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_bold, FontWeight.Bold)
)
val titleLarge = TextStyle(
    fontFamily = robotoFamily,
    fontWeight = FontWeight.Medium,
    letterSpacing = 0.15.sp,
    color = Black.copy(alpha = 0.87f),
    fontSize = 20.sp,
)
val bodyLarge = TextStyle(
    fontFamily = robotoFamily,
    fontWeight = FontWeight.Medium,
    letterSpacing = 0.15.sp,
    color = Black,
    fontSize = 20.sp,
)

val labelMedium = TextStyle(
    color = Black,
    fontSize = 14.sp,
    fontFamily = robotoFamily,
    fontWeight = FontWeight.Normal,

    )
val bodyMedium = TextStyle(
    fontFamily = robotoFamily,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.15.sp,
    color = Black,
    fontSize = 16.sp,
)

val tokenNameStyle = TextStyle(
    fontFamily = robotoFamily,
    fontWeight = FontWeight.Medium,
    color = Color(0xFF525252),
    fontSize = 16.sp,
    letterSpacing = 0.sp
)

val tickerStyle = TextStyle(
    fontFamily = robotoFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    letterSpacing = 0.sp,
    color = Color(0xFF9B9B9B)
)
val priceStyle = TextStyle(
    fontFamily = robotoFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    letterSpacing = 0.sp,
    color = Color(0xFF525252)
)
val percentageStyle = TextStyle(
    fontFamily = robotoFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    letterSpacing = 0.sp
)
// Set of Material typography styles to start with
val Typography = Typography(


    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)