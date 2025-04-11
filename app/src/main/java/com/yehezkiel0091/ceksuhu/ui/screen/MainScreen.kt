package com.yehezkiel0091.ceksuhu.ui.screen

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yehezkiel0091.ceksuhu.R
import com.yehezkiel0091.ceksuhu.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    var input by remember { mutableStateOf("") }
    var resultF by remember { mutableStateOf("") }
    var resultK by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }
    var showImage by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = input,
                onValueChange = {
                    input = it
                    error = it.toFloatOrNull() == null
                },
                label = { Text(stringResource(id = R.string.input_temp)) },
                isError = error,
                supportingText = {
                    if (error) Text(text = stringResource(id = R.string.invalid_input))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = {
                        val celsius = input.toFloatOrNull()
                        if (celsius != null) {
                            resultF = context.getString(R.string.result_f, celsius * 9 / 5 + 32)
                            resultK = context.getString(R.string.result_k, celsius + 273.15)
                            showImage = true
                        } else {
                            error = true
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(id = R.string.convert))
                }

                Button(
                    onClick = {
                        input = ""
                        resultF = ""
                        resultK = ""
                        error = false
                        showImage = false
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(id = R.string.reset))
                }
            }

            if (resultF.isNotEmpty()) Text(text = resultF)
            if (resultK.isNotEmpty()) Text(text = resultK)

            if (showImage) {
                Image(
                    painter = painterResource(id = R.drawable.temperature),
                    contentDescription = "Temperature Image",
                    modifier = Modifier.size(120.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "$resultF\n$resultK")
                }
                context.startActivity(Intent.createChooser(shareIntent, "Bagikan hasil"))
            }) {
                Text(stringResource(id = R.string.share))
            }

            Button(onClick = {
                navController.navigate(Screen.About.route)
            }) {
                Text(text = stringResource(id = R.string.about))
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewMain() {

}