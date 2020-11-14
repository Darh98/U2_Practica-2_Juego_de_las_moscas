package mx.tecnm.tepic.ladm_u2_practica2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    var l : Lienzo?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(Lienzo(this))

        l = Lienzo(this)
        setContentView(l!!)

        Hilo(this).start()
    }
}