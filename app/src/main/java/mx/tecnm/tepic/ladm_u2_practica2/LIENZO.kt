package mx.tecnm.tepic.ladm_u2_practica2

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.CountDownTimer
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AlertDialog
import java.lang.String
import java.util.*

class Lienzo(p: MainActivity): View(p){

    var puntos = 0
    var tiempo =""
    var moscas : Array<Mosca> = Array(100, { Mosca(this) })

    var countDownTimer: CountDownTimer? = object : CountDownTimer(60000, 5) {
        override fun onTick(millisUntilFinished: Long) {
            tiempo= (String.format(Locale.getDefault(),"%d sec.",millisUntilFinished / 1000L))
            if(puntos==100){
                resultado("GANASTE CRACK")
                this.cancel()    }
            if(millisUntilFinished/1000L==0.toLong()){
                resultado("PERDISTE, LLORA PUES")}
        }

        override fun onFinish() {
            this.cancel()
        }
    }.start()

    override fun onDraw(c: Canvas){
        super.onDraw(c)
        var paint = Paint()

        c.drawColor(Color.WHITE)
        paint.setColor(Color.BLACK)

        (0..99).forEach {
            moscas[it].pintar(c, paint)
        }

        paint.textSize = 70f
        paint.setColor(Color.RED)
        c.drawText("Puntuacion: " +puntos.toString(), 80f, 100f, paint)

        paint.textSize = 70f
        c.drawText("Tiempo Restante: "+ tiempo, 80f, 200f, paint)

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){

            MotionEvent.ACTION_DOWN -> {
                var puntero = 0

                while (puntero <= 99) {

                    if (moscas[puntero].hitbox(event.x, event.y)) {
                        if (moscas[puntero].vida) {
                            puntos++
                            var muerte = BitmapFactory.decodeResource(this.resources, R.drawable.mancha)
                            moscas[puntero].imagen1 = muerte
                            moscas[puntero].imagen2 = muerte
                            moscas[puntero].vida = false
                        }
                    }
                    puntero++
                }
            }
        }
        invalidate()
        return true
    }

    fun movimiento(){
        (0..99).forEach {
            if(moscas[it].vida)
                moscas[it].giro(width, height)
        }
        invalidate()
    }

    fun resultado(m: kotlin.String){
        AlertDialog.Builder(this.context)
            .setTitle("=RESULTADOS=")
            .setMessage("APLASTASTE UN TOTAL DE: " + puntos + " MOSCAS \n"+m)
            .setPositiveButton("OK"){p, i ->}
            .show()
    }
}