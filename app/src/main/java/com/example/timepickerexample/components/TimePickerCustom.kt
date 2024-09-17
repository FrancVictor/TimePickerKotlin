package com.example.timepickerexample.components

import android.widget.TimePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timepickerexample.ui.theme.BLACK
import com.example.timepickerexample.ui.theme.GREEN
import com.example.timepickerexample.ui.theme.RED
import com.example.timepickerexample.ui.theme.WHITE
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerCustom(
    dismiss: Boolean,
    getValues: (String, Boolean) -> Unit
) {
    // Obtém a hora atual do sistema usando a classe Calendar.
    val currentTime = Calendar.getInstance()

    // Cria uma variável de estado para controlar a visibilidade do seletor de tempo.
    // Inicialmente, o valor é passado pelo parâmetro `dismiss`.
    var onDismiss by remember { mutableStateOf(dismiss) }

    // Define o estado inicial do seletor de hora com a hora e o minuto atuais.
    // Configura o seletor no formato de 24 horas.
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )

    // Coluna principal que organiza o seletor de hora e os botões.
    Column(
        modifier = Modifier
            .fillMaxSize() // Preenche o espaço total disponível na tela.
            .background(BLACK), // Define a cor de fundo como preto.
        verticalArrangement = Arrangement.Center, // Centraliza verticalmente os itens.
        horizontalAlignment = Alignment.CenterHorizontally // Centraliza horizontalmente.
    ) {
        // Componente TimePicker para o seletor de hora.
        TimePicker(
            state = timePickerState, // Vincula o seletor ao estado de hora e minuto.
            colors = TimePickerDefaults.colors( // Define as cores do seletor de hora.
                clockDialColor = WHITE, // Cor do fundo do relógio.
                clockDialUnselectedContentColor = BLACK, // Cor dos números não selecionados.
                selectorColor = GREEN, // Cor do marcador do seletor.
                timeSelectorSelectedContainerColor = GREEN, // Cor do fundo do número selecionado.
                timeSelectorSelectedContentColor = WHITE, // Cor do conteúdo selecionado (números).
                timeSelectorUnselectedContentColor = BLACK, // Cor dos números não selecionados.
                periodSelectorBorderColor = GREEN // Cor da borda do seletor de período.
            )
        )

        // Linha para organizar os botões "Fechar" e "Confirmar".
        Row(
            modifier = Modifier
                .fillMaxSize() // Preenche a largura total disponível.
                .padding(10.dp), // Adiciona espaçamento de 10dp ao redor da linha.
            horizontalArrangement = Arrangement.SpaceBetween, // Distribui os botões uniformemente.
            verticalAlignment = Alignment.CenterVertically // Centraliza verticalmente os botões.
        ) {
            // Botão "Fechar" que chama a função getValues com "00:00" e false.
            Button(
                onClick = {
                    getValues(
                        "00:00",
                        false
                    ) // Retorna o valor "00:00" e indica que não foi confirmado.
                    onDismiss = false // Fecha o seletor de hora.
                },
                // Define as cores do botão "Fechar".
                colors = ButtonDefaults.buttonColors(
                    containerColor = BLACK, // Cor de fundo do botão.
                    contentColor = RED // Cor do texto e conteúdo do botão.
                ),
                border = BorderStroke(2.dp, RED) // Borda vermelha de 2dp ao redor do botão.
            ) {
                // Texto do botão.
                Text(text = "Fechar")
            }

            // Botão "Confirmar" que retorna a hora/minuto selecionados ao usuário.
            Button(
                onClick = {
                    // Verifica se os valores de hora e minuto são menores que 10
                    // e adiciona o zero à esquerda quando necessário.
                    when {
                        timePickerState.hour <= 9 && timePickerState.minute <= 9 -> {
                            getValues("0${timePickerState.hour}:0${timePickerState.minute}", true)
                            onDismiss = false // Fecha o seletor após a confirmação.
                        }

                        timePickerState.hour <= 9 && timePickerState.minute > 9 -> {
                            getValues("0${timePickerState.hour}:${timePickerState.minute}", true)
                            onDismiss = false // Fecha o seletor após a confirmação.
                        }

                        timePickerState.hour > 9 && timePickerState.minute <= 9 -> {
                            getValues("${timePickerState.hour}:0${timePickerState.minute}", true)
                            onDismiss = false // Fecha o seletor após a confirmação.
                        }

                        else -> {
                            getValues("${timePickerState.hour}:${timePickerState.minute}", true)
                            onDismiss = false // Fecha o seletor após a confirmação.
                        }
                    }
                },
                // Define as cores do botão "Confirmar".
                colors = ButtonDefaults.buttonColors(
                    containerColor = BLACK, // Cor de fundo do botão.
                    contentColor = GREEN // Cor do texto e conteúdo do botão.
                ),
                border = BorderStroke(2.dp, GREEN) // Borda verde de 2dp ao redor do botão.
            ) {
                // Texto do botão.
                Text(text = "Confirmar")
            }
        }
    }
}

@Preview
@Composable
private fun TimePickerCustomPreview() {
    TimePickerCustom(
        dismiss = true, getValues = { _, _ ->

        })


}