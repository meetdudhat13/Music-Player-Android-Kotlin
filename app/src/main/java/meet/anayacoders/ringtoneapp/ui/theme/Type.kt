package meet.anayacoders.ringtoneapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import meet.anayacoders.ringtoneapp.R

//val provider = GoogleFont.Provider(
//    providerAuthority = "com.google.android.gms.fonts",
//    providerPackage = "com.google.android.gms",
//    certificates = R.array.com_google_android_gms_fonts_certs
//)

val bodyFontFamily = FontFamily(
//    Font(
//        R.font.poppins_regular,
//        weight = FontWeight.Normal
//    )
    Font(
        R.font.pw_regular,
        weight = FontWeight.Normal
    )
)



val msdFontFamily = FontFamily(
//    Font(
//        R.font.poppins_bold,
//        weight = FontWeight.Bold
//    ),
//    Font(
//        R.font.poppins_extra_bold,
//        weight = FontWeight.ExtraBold
//    ),
//    Font(
//        R.font.poppins_extra_light,
//        weight = FontWeight.ExtraLight
//    ),
//    Font(
//        R.font.poppins_medium,
//        weight = FontWeight.Medium
//    ),
//    Font(
//        R.font.poppins_regular,
//        weight = FontWeight.Normal
//    ),
//    Font(
//        R.font.poppins_semibold,
//        weight = FontWeight.SemiBold
//    ),
    Font(
        R.font.pw_light,
        weight = FontWeight.Light
    ),
    Font(
        R.font.pw_regular,
        weight = FontWeight.Normal
    )
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
    displayLarge = baseline.displayLarge.copy(fontFamily = bodyFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = bodyFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = bodyFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = bodyFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = bodyFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = bodyFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = bodyFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = bodyFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = bodyFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
)

