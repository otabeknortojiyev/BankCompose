package uz.gita.otabek.bankauthcompose.screens.language

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.otabek.bankauthcompose.R
import uz.gita.otabek.bankauthcompose.ui.theme.LightBlack
import uz.gita.otabek.bankauthcompose.ui.theme.MainGreen
import uz.gita.otabek.bankauthcompose.utils.Lang
import uz.gita.otabek.bankauthcompose.utils.setLanguage
import uz.gita.otabek.presenter.language.LanguageContract
import uz.gita.otabek.presenter.language.LanguageViewModel

object LanguageScreen : Screen {
    private fun readResolve(): Any = LanguageScreen

    @Composable
    override fun Content() {
        val viewModel: LanguageContract.ViewModel = getViewModel<LanguageViewModel>()
        val uiState = viewModel.collectAsState()
        LanguageScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@Composable
private fun LanguageScreenContent(
    uiState: State<LanguageContract.UiState>, onEventDispatcher: (LanguageContract.Intent) -> Unit
) {
    val context = LocalContext.current
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val guideline1 = createGuidelineFromTop(0.09f)
        val guideline3 = createGuidelineFromTop(0.26f)
        val guideline7 = createGuidelineFromTop(0.7f)
        val guideline8 = createGuidelineFromTop(0.8f)
        val guideline9 = createGuidelineFromTop(0.9f)
        Row(
            modifier = Modifier
                .padding(start = 20.dp)
                .constrainAs(createRef()) {
                    top.linkTo(guideline1)
                    bottom.linkTo(guideline1)
                }, verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.hamkor_icon), contentDescription = null, modifier = Modifier
                    .width(36.dp)
                    .height(36.dp)
            )

            Text(
                text = "HAMKORBANK", style = TextStyle(
                    fontSize = 20.sp,
                    color = MainGreen,
                    fontFamily = FontFamily(Font(R.font.archivo_black)),
                ), modifier = Modifier.padding(start = 10.dp)
            )
        }

        val isRussianText = remember {
            mutableIntStateOf(0)
        }

        LaunchedEffect(Unit) {
            while (true) {
                delay(3000)
                isRussianText.intValue = 0
                delay(3000)
                isRussianText.intValue = 1
                delay(3000)
                isRussianText.intValue = 2
            }
        }

        Column(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .constrainAs(createRef()) {
                top.linkTo(guideline3)
                bottom.linkTo(guideline3)
            }) {
            when (isRussianText.intValue) {
                1 -> {
                    Text(
                        text = "Выберите язык\nприложения", style = TextStyle(
                            color = LightBlack, letterSpacing = 2.sp, fontSize = 32.sp, fontFamily = FontFamily(Font(R.font.mulish_bold))
                        )
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Вы всегда можете изменить язык в\nнастройках приложения", style = TextStyle(
                            color = Color.Gray, letterSpacing = 1.sp, fontSize = 18.sp, fontFamily = FontFamily(Font(R.font.montserrat_light))
                        )
                    )
                }

                0 -> {
                    Text(
                        text = "Ilova tilini tanlang", style = TextStyle(
                            color = LightBlack, letterSpacing = 2.sp, fontSize = 32.sp, fontFamily = FontFamily(Font(R.font.mulish_bold))
                        )
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Siz ilova sozlamalarida tilni har doim\no'zgartirishingiz mumkin", style = TextStyle(
                            color = Color.Gray, letterSpacing = 1.sp, fontSize = 18.sp, fontFamily = FontFamily(Font(R.font.montserrat_light))
                        )
                    )
                }

                else -> {
                    Text(
                        text = "Select\n" + "application language", style = TextStyle(
                            color = LightBlack, letterSpacing = 2.sp, fontSize = 32.sp, fontFamily = FontFamily(Font(R.font.mulish_bold))
                        )
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "You can always change the language in\n" + "application settings", style = TextStyle(
                            color = Color.Gray, letterSpacing = 1.sp, fontSize = 18.sp, fontFamily = FontFamily(Font(R.font.montserrat_light))
                        )
                    )
                }
            }
        }

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .constrainAs(createRef()) {
                top.linkTo(guideline7)
            },
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
            onClick = {
                setLanguage(language = Lang.uz, context = context)
                Toast.makeText(context, "Muvaffaqiyatli", Toast.LENGTH_SHORT).show()
                onEventDispatcher(LanguageContract.Intent.ClickLanguage)
                onEventDispatcher(LanguageContract.Intent.SetLanguage("uz"))
            }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.flag_uz), contentDescription = null)
                Text(
                    text = "O'zbekcha", style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)), color = Color.Black
                    ), modifier = Modifier.padding(start = 10.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(painter = painterResource(id = R.drawable.right_arrow), contentDescription = null, modifier = Modifier.padding(end = 10.dp))
            }
        }

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .constrainAs(createRef()) {
                top.linkTo(guideline8)
            },
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
            onClick = {
                setLanguage(language = Lang.ru, context = context)
                Toast.makeText(context, "Успешно", Toast.LENGTH_SHORT).show()
                onEventDispatcher(LanguageContract.Intent.ClickLanguage)
                onEventDispatcher(LanguageContract.Intent.SetLanguage("ru"))
            }) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.flag_ru), contentDescription = null)
                Text(
                    text = "Русский", style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)), color = Color.Black
                    ), modifier = Modifier.padding(start = 10.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(painter = painterResource(id = R.drawable.right_arrow), contentDescription = null, modifier = Modifier.padding(end = 10.dp))
            }
        }

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .constrainAs(createRef()) {
                top.linkTo(guideline9)
            },
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
            onClick = {
                setLanguage(language = Lang.en, context = context)
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                onEventDispatcher(LanguageContract.Intent.ClickLanguage)
                onEventDispatcher(LanguageContract.Intent.SetLanguage("en"))
            }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(id = R.drawable.flag_us), contentDescription = null)
                Text(
                    text = "English", style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)), color = Color.Black
                    ), modifier = Modifier.padding(start = 10.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(painter = painterResource(id = R.drawable.right_arrow), contentDescription = null, modifier = Modifier.padding(end = 10.dp))
            }
        }
    }
}