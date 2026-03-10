package com.example.vk_homework

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    var textState by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = textState,
            onValueChange = { textState = it },
            label = { Text(stringResource(R.string.input_label)) },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(10.dp))

        // 1. Явный Intent
        IntentButton(
            text = stringResource(R.string.btn_open_activity),
            currentInput = textState,
            context = context
        ) {
            val intent = Intent(context, SecondActivity::class.java).apply {
                putExtra("EXTRA_TEXT", textState)
            }
            context.startActivity(intent)
        }

        // 2. Неявный Intent (Звонок)
        IntentButton(
            text = stringResource(R.string.btn_call_friend),
            currentInput = textState,
            context = context,
            isValid = { isPhoneMobile(textState) }
        ) {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$textState")
            }
            context.startActivity(intent)
        }

        // 3. Системный Intent (Поделиться)
        IntentButton(
            text = stringResource(R.string.btn_share_text),
            currentInput = textState,
            context = context
        ) {
            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, textState)
                type = "text/plain"
            }
            context.startActivity(Intent.createChooser(sendIntent, null))
        }
    }
}

@Composable
fun IntentButton(
    text: String,
    currentInput: String,
    context: Context,
    isValid: () -> Boolean = { true },
    onClick: () -> Unit
) {
    val errorEmpty = stringResource(R.string.error_empty_field)
    val errorInvalid = stringResource(R.string.error_invalid_phone)

    Button(onClick = {
        when {
            currentInput.isBlank() ->
                Toast.makeText(context, errorEmpty, Toast.LENGTH_SHORT).show()
            !isValid() ->
                Toast.makeText(context, errorInvalid, Toast.LENGTH_SHORT).show()
            else -> onClick()
        }
    }) {
        Text(text)
    }
}

fun isPhoneMobile(phone: String): Boolean {
    val digitsOnly = phone.filter { it.isDigit() }
    return when {
        phone.startsWith("+79") -> phone.length == 12 && digitsOnly.length == 11
        phone.startsWith("89") -> phone.length == 11 && digitsOnly.length == 11
        else -> false
    }
}