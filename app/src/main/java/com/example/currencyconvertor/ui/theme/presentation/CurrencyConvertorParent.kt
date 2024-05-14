package com.example.currencyconvertor.ui.theme.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.currencyconvertor.ui.theme.data.remote.CurrencyResponse

@Composable
fun CurrencyConvertorParent(
    state: CurrencyResponse,
) {
    var text by remember { mutableStateOf("1.0") }
    var dropDownRatio = remember { mutableDoubleStateOf(1.0) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = text,
            onValueChange = { text = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        Spacer(modifier = Modifier.height(20.dp))
        val currencyList = state.rates?.keys?.toList()
        CurrencyDropDown(currencyList, dropDownRatio,state)
        state.rates?.let {
            GridView(it.keys.toList(), it.values.toList(), if (text == "") 1.0 else text.toDouble()/dropDownRatio.doubleValue)
        }
    }

}

@Composable
fun GridView(key: List<String>, items: List<Double>, factor: Double = 1.0) {
    val listState = rememberLazyGridState()
    LazyVerticalGrid(
        GridCells.Fixed(3),
        state = listState,
    ) {
        itemsIndexed(items) { index, item ->
            // You can replace this with your custom item composable
            GridItem(key[index], item = (factor*item).toString())
        }
    }
}

@Composable
fun GridItem(key: String, item: String) {
    Column(
        Modifier
            .size(40.dp)
            .padding(3.dp)
            .background(Color.Green), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "$key $item")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyDropDown(
    currencyList: List<String>?,
    dropDownRatio: MutableDoubleState,
    state: CurrencyResponse,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("USD") }
    var selectedIndex by remember { mutableStateOf(0) }
    dropDownRatio.doubleValue = state.rates?.values?.toList()?.get(selectedIndex) ?: 1.0
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                currencyList?.forEachIndexed {  index, item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            selectedIndex = index
                        }
                    )
                }
            }
        }
    }
}