package com.example.sendmessage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.sendmessage.model.Message
import com.example.sendmessage.model.Person

/**
 * Pantalla principal de la aplicación.
 * Permite al usuario introducir un texto en un campo de entrada y enviarlo
 * a una segunda pantalla con un Intent.
 * -
 * Esta es la primera actividad de la aplicación que realiza las operaciones:
 * <ol>
 *     <li>Crear un componente <code>EditText</code> y Butt>on en XML</li>
 *     <li>Lanzar un evento en un componente Visual</li>
 *     <li>Crea el Intent junto con el <code>Bundle</code> para pasar a otra actividad</li>
 *     <li>El ciclo de vida de la Activity</li>
 *     <li> Ver la pila de Actividades</li>
 *
 * </ol>
 *
 * -
 * @author Ángel
 * @version 1.0
 * @see android.widget.Button
 * @see android.widget.EditText
 * @see android.os.Bundle
 * @see Intent
 *
 *
 */
class SendMessageActivity : AppCompatActivity() {
    lateinit var etMessageText: EditText
    // (IA) declaramos los edittext para remitente y destinatario, y el boton de tipo Button
    lateinit var etRemitente: EditText // (IA)
    lateinit var etDestinatario: EditText // (IA)
    lateinit var btSend: Button // (IA)

    companion object {
        const val TAG: String = "LogSendMessageActivity"
    }

    /**
     * Metodo de ciclo de vida que se ejecuta al crear la actividad.
     * Inicializa las vistas y configura el evento de clic en el botón.
     * -
     * Metodo de creacion de una actividad
     * @param android.os.Bundle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)
        // (IA) obtenemos las referencias de todos los elementos de la vista
        etRemitente = findViewById(R.id.etRemitente) // (IA)
        etDestinatario = findViewById(R.id.etDestinatario) // (IA)
        etMessageText = findViewById(R.id.etMessageText)
        btSend = findViewById(R.id.btSend)
        // Listener para enviar el mensaje al pulsar el botón
        btSend.setOnClickListener {

            /* 1 pasar dato a dato en un bundle
            // Se crea el Intent para navegar a ViewMessageActivity
            val intent = Intent(this, ViewMessageActivity::class.java)
            // Creamos un Bundle para empaquetar los datos
            val bundle = Bundle()
            bundle.putString("KEY_MESSAGE", etMesssageText.text.toString())
            // Adjuntamos el Bundle al Intent y lanzamos la actividad
            intent.putExtras(bundle)
            startActivity(intent)
             */
            sendMessage()
        }
        //Se escriben mensajes de depuracion en la consola LogCat
        Log.d(TAG, "SendMessageActivity -> onCreate()")


    }

    /**
     * Función que crea un mensaje con la informacion de la persona que envia
     * y de la persona que recoge el mensaje
     */
    private fun sendMessage() {
        //1 crear el intent
        val intent = Intent(this, ViewMessageActivity::class.java)
        //2 Creamos un Bundle para empaquetar los datos
        val bundle = Bundle()
        
        // (IA) creamos las personas cogiendo los datos introducidos en los EditText
        val sender = Person("12345678A", etRemitente.text.toString(), "") // (IA)
        val receiver = Person("98765432A", etDestinatario.text.toString(), "") // (IA)

        // (IA) metemos el mensaje completo con el remitente y destinatario
        val message = Message(1, etMessageText.text.toString(), sender, receiver)
        bundle.putParcelable("KEY_MESSAGE", message)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    //region Ciclo de Vida de una Actividad
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "SendMessageActivity -> onDestroy()")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "SendMessageActivity -> onStart()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "SendMessageActivity -> onStop()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "SendMessageActivity -> onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "SendMessageActivity -> onPause()")
    }

    //endregion
}