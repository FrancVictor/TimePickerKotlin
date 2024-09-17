package com.example.timepickerexample.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.timepickerexample.components.TimePickerCustom
import com.example.timepickerexample.ui.theme.BLACK
import com.example.timepickerexample.ui.theme.GREEN
import com.example.timepickerexample.ui.theme.WHITE


@Composable
fun Home() {
    // Estado para armazenar a hora atual selecionada, inicialmente definida como "00:00".
    var currentTime by remember { mutableStateOf("00:00") }

    // Estado para controlar se o seletor de hora deve ser exibido ou não.
    var selectTime by remember { mutableStateOf(false) }

    // Layout principal da tela, com uma Coluna que organiza os componentes verticalmente.
    Column(
        modifier = Modifier
            .fillMaxSize() // Preenche todo o espaço disponível na tela.
            .background(BLACK), // Define a cor de fundo como preto.
        verticalArrangement = Arrangement.Center, // Centraliza os elementos verticalmente.
        horizontalAlignment = Alignment.CenterHorizontally // Centraliza os elementos horizontalmente.
    ) {
        // Botão que abre o seletor de horário quando clicado.
        Button(
            onClick = {
                selectTime = true // Ativa a exibição do seletor de horário.
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = BLACK, // Define a cor de fundo do botão como preto.
                contentColor = WHITE // Define a cor do texto como branco.
            ),
            border = BorderStroke(2.dp, GREEN) // Adiciona uma borda verde ao botão.
        ) {
            // Texto exibido dentro do botão.
            Text(
                text = "Selecionar Horário", // Texto do botão.
                fontSize = 18.sp // Define o tamanho da fonte como 18sp.
            )
        }

        // Exibe o texto com a hora atual selecionada.
        Text(
            text = buildAnnotatedString {
                append("Hora Atual: ") // Texto fixo "Hora Atual: ".
                withStyle(
                    style = SpanStyle(
                        color = WHITE, // Define a cor do texto da hora como branco.
                    )
                ) {
                    append(currentTime) // Adiciona a hora atual armazenada em `currentTime`.
                }
            },
            fontSize = 25.sp, // Define o tamanho da fonte como 25sp.
            color = GREEN, // Define a cor do texto como verde.
            modifier = Modifier.padding(20.dp) // Adiciona um espaçamento de 20dp ao redor do texto.
        )
    }

    // Se `selectTime` for verdadeiro, exibe o seletor de horário personalizado.
    if (selectTime) {
        // Chama o componente TimePickerCustom passando o estado atual.
        TimePickerCustom(dismiss = selectTime, getValues = { time, timePickerState ->
            if (timePickerState) {
                // Se o usuário confirmou a seleção de horário, atualiza `currentTime` com o valor selecionado.
                currentTime = time
                selectTime = false // Fecha o seletor de horário.
            } else {
                // Se o usuário cancelou, redefine `currentTime` para "00:00".
                currentTime = "00:00"
                selectTime = false // Fecha o seletor de horário.
            }
        })
    } else {
        // Se `selectTime` for falso, mantém o seletor fechado.
        selectTime = false
    }
}



@Preview
@Composable
private fun HomePreview(){
    Home()
}