package com.kilomkolim84rgb.monedero.emisor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.FirebaseDatabase

data class Moneda(val monto: Int = 0)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("movimientos")
        
        setContent {
            MaterialTheme {
                EmisorScreen { monto ->
                    val nuevaMoneda = ref.push()
                    nuevaMoneda.setValue(Moneda(monto))
                }
            }
        }
    }
}

@Composable
fun EmisorScreen(onEnviar: (Int) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text("Monedero Emisor", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(onClick = { onEnviar(10) }, modifier = Modifier.fillMaxWidth().height(60.dp)) {
            Text("Enviar S/ 0.10", fontSize = 22.sp)
        }
        Button(onClick = { onEnviar(20) }, modifier = Modifier.fillMaxWidth().height(60.dp)) {
            Text("Enviar S/ 0.20", fontSize = 22.sp)
        }
        Button(onClick = { onEnviar(50) }, modifier = Modifier.fillMaxWidth().height(60.dp)) {
            Text("Enviar S/ 0.50", fontSize = 22.sp)
        }
        Button(onClick = { onEnviar(100) }, modifier = Modifier.fillMaxWidth().height(60.dp)) {
            Text("Enviar S/ 1.00", fontSize = 22.sp)
        }
        Button(onClick = { onEnviar(500) }, modifier = Modifier.fillMaxWidth().height(60.dp)) {
            Text("Enviar S/ 5.00", fontSize = 22.sp)
        }
    }
}
