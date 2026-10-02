package com.example.sendmessage

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sendmessage.model.Message // (IA) importamos el modelo Message

/**
 * Segunda pantalla de la aplicación.
 * Muestra el mensaje recibido desde [SendMessageActivity] en un TextView.
 *
 * @author Ángel
 */
class ViewMessageActivity : AppCompatActivity() {

    companion object {
        const val TAG: String = "LogViewMessageActivity"
    }

    /**
     * Inicializa la actividad, recupera los datos pasados por el Intent
     * y los muestra en la interfaz
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_message)

        // Ajuste para mostrar el contenido respetando las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // (IA) obtenemos las referencias de los TextViews
        val tvRemitente = findViewById<TextView>(R.id.tvRemitente) // (IA)
        val tvDestinatario = findViewById<TextView>(R.id.tvDestinatario) // (IA)
        val tvMessage = findViewById<TextView>(R.id.tvMensaje)

        // (IA) pillamos el bundle del intent y deserializamos el objeto Message
        val bundle = intent.extras // (IA)
        val message = bundle?.getSerializable("KEY_MESSAGE") as? Message // (IA)

        // (IA) mostramos los datos sacados del objeto message
        if (message != null) { // (IA)
            tvRemitente.text = message.sender.name // (IA)
            tvDestinatario.text = message.receiver.name // (IA)
            tvMessage.text = message.content // (IA)
        } // (IA)


        Log.d(TAG, "ViewMessageActivity -> onCreate()")

    }



    //region Ciclo de Vida de una Actividad
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "ViewMessageActivity -> onDestroy()")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "ViewMessageActivity -> onStart()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "ViewMessageActivity -> onStop()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "ViewMessageActivity -> onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "ViewMessageActivity -> onPause()")
    }

    //endregion
}