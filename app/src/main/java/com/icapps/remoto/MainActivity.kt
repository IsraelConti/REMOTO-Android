package com.icapps.remoto

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
       override fun onCreate(savedInstanceState: Bundle?) {
               super.onCreate(savedInstanceState)
                 val root=LinearLayout(this).apply{orientation=LinearLayout.VERTICAL;setPadding(32,48,32,32)}
                   val title=TextView(this).apply{text="REMOTO Android";textSize=30f}
                     val status=TextView(this).apply{text="Agente móvil preparado";textSize=18f}
                       val command=EditText(this).apply{hint="¿Qué quieres que haga en este móvil?"}
                         val execute=Button(this).apply{text="EJECUTAR"}
                           val accessibility=Button(this).apply{text="ACTIVAR CONTROL DEL MÓVIL"}
                             val log=TextView(this).apply{text="REMOTO trabaja directamente en este Android. No necesita IP de PC.";textSize=16f;setPadding(0,24,0,24)}
                               accessibility.setOnClickListener{startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))}
                                 execute.setOnClickListener{
                                          val q=command.text.toString().trim()
                                             if(q.isBlank()){status.text="Escribe una orden";return@setOnClickListener}
                                                when{
                                                          q.startsWith("busca ",true)->{startActivity(Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.com/search?q="+Uri.encode(q.substring(6)))));status.text="Buscando"}
                                                              q.contains("chrome",true)->{packageManager.getLaunchIntentForPackage("com.android.chrome")?.let{startActivity(it)};status.text="Chrome abierto"}
                                                                  q.contains("gmail",true)->{packageManager.getLaunchIntentForPackage("com.google.android.gm")?.let{startActivity(it)};status.text="Gmail abierto"}
                                                                      q.contains("ajustes",true)->{startActivity(Intent(Settings.ACTION_SETTINGS));status.text="Ajustes abiertos"}
                                                                          q.equals("inicio",true)->{RemotoAccessibilityService.instance?.performGlobalAction(android.accessibilityservice.AccessibilityService.GLOBAL_ACTION_HOME);status.text="Inicio"}
                                                                              q.equals("volver",true)->{RemotoAccessibilityService.instance?.performGlobalAction(android.accessibilityservice.AccessibilityService.GLOBAL_ACTION_BACK);status.text="Volver"}
                                                                                  q.contains("recientes",true)->{RemotoAccessibilityService.instance?.performGlobalAction(android.accessibilityservice.AccessibilityService.GLOBAL_ACTION_RECENTS);status.text="Recientes"}
                                                                                      else->{startActivity(Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.com/search?q="+Uri.encode(q))));status.text="Orden enviada"}
                                                }
                                                   log.text="Última orden: $q"
                                 }
                                   root.addView(title);root.addView(status);root.addView(command);root.addView(execute);root.addView(accessibility);root.addView(log)
                                     setContentView(root)
       }
}
