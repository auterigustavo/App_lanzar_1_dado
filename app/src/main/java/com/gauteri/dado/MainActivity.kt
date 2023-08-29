package com.gauteri.dado

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Crea la variable btnLanzar y la asocia al boton del mismo nombre para poder aplicarle funciones
        val btnLanzar: Button = findViewById(R.id.btnLanzar)

        //Defino las acciones a realizar al hacer click sobre el boton
        btnLanzar.setOnClickListener{
            tiempo()

            //Si quisiera que al apretar el boton se reproduzca un sonido se hace lo siguiente
            //Donde sound es la carpeta y sonido el nombre de archivo...
            //val mp = MediaPlayer.create(this, R.sound.sonido)
            //mp.start()
        }
        rollDice()
    }

    //Esta funcion toma el numero obtenido al azar, lo imprime en pantalla y muestra la imagen correspondiente del dado
    private fun rollDice() {
        val numero: Int = lanzar(6)
        val txtResult: TextView = findViewById(R.id.txtResultado)
        txtResult.text = numero.toString()

        //Elige que imagen mostrar segun el numero obtenido
        val drawableResource = when(numero) {
            1 -> R.drawable.uno
            2 -> R.drawable.dos
            3 -> R.drawable.tres
            4 -> R.drawable.cuatro
            5 -> R.drawable.cinco
            else -> R.drawable.seis
        }
            val imgDado: ImageView = findViewById(R.id.imgDado)
            imgDado.setImageResource(drawableResource)

        //La siguiente linea de codigo imprime un mensaje tipo notificacion de error
        //Toast.makeText(this, numero.toString(), Toast.LENGTH_LONG).show()
    }

    //Esta funcion retorna un numero aleatorio comprendido entre 1 y el que decida establecer como maximo
    private fun lanzar(max: Int): Int {
        return (1..max).random()
    }

    //Genero un timer de 3 segundos que ejecuta la funcion rollDice para cambiar de imagenes rapido
    private fun tiempo() {
        object: CountDownTimer(3000, 150) {
            override fun onTick(p0: Long) {
                rollDice()
            }

            override fun onFinish() {

            }

        }.start()
    }
}