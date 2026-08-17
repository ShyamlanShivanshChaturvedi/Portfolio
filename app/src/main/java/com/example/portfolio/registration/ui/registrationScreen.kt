package com.example.portfolio.registration.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.portfolio.R
import com.example.portfolio.ui.theme.PortfolioTheme

@Composable
fun RegistrationScreen(
    onSignUpClick: () -> Unit = {},
    onSignInClick: () -> Unit = {},
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme()
) {
    val buttonBackgroundColor = if (isSystemInDarkTheme) Color.White else Color.Black
    val buttonContentColor = if (isSystemInDarkTheme) Color.Black else Color.White

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LazyColumn(
                    modifier = Modifier
                        .widthIn(max = 600.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f)
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    item {
                        Image(
                            painterResource(R.drawable.app_logo),
                            contentDescription = "this is an logo of app",
                            modifier = Modifier
                                .clip(CircleShape)
                                .sizeIn(maxWidth = 130.dp, maxHeight = 130.dp)
                        )
                    }

                    item {
                        Spacer(Modifier.height(20.dp))
                    }

                    item {
                        Text(
                            text = "Shivansh Shyamlan",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontWeight = FontWeight.Bold,
                            ),
                            letterSpacing = MaterialTheme.typography.titleMedium.letterSpacing,
                            textAlign = TextAlign.Center
                        )
                    }

                    item {
                        Spacer(
                            Modifier.height(7.dp)
                        )
                    }

                    item {
                        Text(
                            text = "Welcome, here you can see my work and let me and other know your thoughts about my projects,posts, and other stuff",
                            style = MaterialTheme.typography.bodyMedium,
                            letterSpacing = MaterialTheme.typography.bodyMedium.letterSpacing,
                            textAlign = TextAlign.Center
                        )
                    }

                    item {
                        Spacer(
                            Modifier.height(27.dp)
                        )
                    }

                    item {
                        OutlinedButton(
                            onClick = onSignUpClick,
                            colors = ButtonDefaults.outlinedButtonColors().copy(
                                contentColor = buttonContentColor,
                                containerColor = buttonBackgroundColor
                            ),
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(59.dp),
                            border = ButtonDefaults.outlinedButtonBorder(true).copy(
                                width = 1.dp
                            ),
                            shape = RoundedCornerShape(18.dp)
                        ) {
                            Text(
                                text = "Get Started",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }

                    item {
                        Spacer(
                            Modifier.height(10.dp)
                        )
                    }

                    item {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            HorizontalDivider(
                                modifier = Modifier.weight(1f),
                                color = buttonBackgroundColor
                            )
                            Text(
                                text = "Or",
                                modifier = Modifier.padding(start = 6.dp, end = 6.dp)
                            )
                            HorizontalDivider(
                                modifier = Modifier.weight(1f),
                                color = buttonBackgroundColor
                            )
                        }
                    }

                    item {
                        Spacer(
                            Modifier.height(10.dp)
                        )
                    }

                    item {
                        OutlinedButton(
                            onClick = onSignInClick,
                            colors = ButtonDefaults.outlinedButtonColors().copy(
                                contentColor = buttonContentColor,
                                containerColor = buttonBackgroundColor
                            ),
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(59.dp),
                            border = ButtonDefaults.outlinedButtonBorder(true).copy(
                                width = 1.dp
                            ),
                            shape = RoundedCornerShape(18.dp)
                        ) {
                            Text(
                                text = "Login",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Phone View", device = "id:pixel_8")
@Preview(
    showBackground = true,
    name = "Tablet View",
    device = "spec:width=1280dp,height=800dp,dpi=240"
)
@Composable
fun RegistrationScreenPreview() {
    PortfolioTheme {
        RegistrationScreen()
    }
}

@Preview(showBackground = true, name = "Dark Mode")
@Composable
fun RegistrationScreenPreviewDark() {
    PortfolioTheme(darkTheme = true) {
        RegistrationScreen()
    }
}