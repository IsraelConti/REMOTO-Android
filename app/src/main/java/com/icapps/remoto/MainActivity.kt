package com.icapps.remoto

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
      override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                        val root = LinearLayout(this).apply {
                                      orientation = LinearLayout.VERTICAL
                                      setPadding(32, 48, 32, 32)
                        }
                                val title = TextView(this).apply { text = "REMOTO"; textSize = 30f }
                                        val status = TextView(this).apply { text = "PC: DESCONECTADO"; textSize = 18f }
                                                val host = EditText(this).apply { hint = "IP o dirección del PC" }
                                                        val key = EditText(this).apply { hint = "Clave de conexión" }
                                                                val connect = Button(this).apply { text = "CONECTAR" }
                                                                        val screen = TextView(this).apply {
                                                                                      text = "Pantalla remota aparecerá aquí"
                                                                                      textSize = 18f
                                                                                      gravity = android.view.Gravity.CENTER
                                                                                      setPadding(16, 120, 16, 120)
                                                                        }
                                                                                connect.setOnClickListener {
                                                                                              status.text = if (host.text.isNotBlank() && key.text.isNotBlank())
                                                                                                              "PC: PREPARANDO CONEXIÓN…" else "Introduce dirección y clave"
                                                                                }
                                                                                        root.addView(title); root.addView(status); root.addView(host); root.addView(key)
                                                                                                root.addView(connect); root.addView(screen)
                                                                                                        setContentView(root)
      }
}
