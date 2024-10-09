package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.compose.labelViewAllGrayColor
import meet.anayacoders.ringtoneapp.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val bodyFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Poppins"),
        fontProvider = provider,
    )
)

val displayFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Poppins"),
        fontProvider = provider,
    )
)

val msdFontFamily = FontFamily(
    androidx.compose.ui.text.font.Font(
        R.font.poppins_bold,
        weight = FontWeight.Bold
    ),
    androidx.compose.ui.text.font.Font(
        R.font.poppins_extra_bold,
        weight = FontWeight.ExtraBold
    ),
    androidx.compose.ui.text.font.Font(
        R.font.poppins_extra_light,
        weight = FontWeight.ExtraLight
    ),
    androidx.compose.ui.text.font.Font(
        R.font.poppins_medium,
        weight = FontWeight.Medium
    ),
    androidx.compose.ui.text.font.Font(
        R.font.poppins_regular,
        weight = FontWeight.Normal
    ),
    androidx.compose.ui.text.font.Font(
        R.font.poppins_semibold,
        weight = FontWeight.SemiBold
    ),
)

val msdNewAlbumLabel = TextStyle(
    fontSize = 8.sp,
    fontFamily = msdFontFamily,
    fontWeight = FontWeight.Bold,
)
val msdAlbumName = TextStyle(
    fontSize = 22.sp,
    fontFamily = msdFontFamily,
    fontWeight = FontWeight.Bold
)
val msdAlbumOwnerName = TextStyle(
    fontSize = 12.sp,
    fontFamily = msdFontFamily,
    fontWeight = FontWeight.Medium
)

val msdLabelHeading = TextStyle(
    fontSize = 20.sp,
    fontFamily = msdFontFamily,
    fontWeight = FontWeight.SemiBold
)
val msdLabelViewAll = TextStyle(
    fontSize = 14.sp,
    fontFamily = msdFontFamily,
    fontWeight = FontWeight.SemiBold,
)
val msdSongSingerName = TextStyle(
    fontSize = 12.sp,
    fontFamily = msdFontFamily,
    fontWeight = FontWeight.Light,
)
val msdAlbumSongName = TextStyle(
    fontSize = 14.sp,
    fontFamily = msdFontFamily,
    fontWeight = FontWeight.Medium,
)
// Default Material 3 typography values
val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
)

