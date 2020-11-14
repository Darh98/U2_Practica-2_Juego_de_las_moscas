package mx.tecnm.tepic.ladm_u2_practica2

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint

    var sentido = true

class Mosca(punteroLienzo: Lienzo) {
    var x = (0..1080).random().toFloat()
    var y = (0..1776).random().toFloat()
    var iX = 70
    var iY = 70
    var imagen1 = BitmapFactory.decodeResource(punteroLienzo.resources, R.drawable.mosca1)
    var imagen2 = BitmapFactory.decodeResource(punteroLienzo.resources, R.drawable.mosca2)

    var vida = true

    fun hitbox(toqueX:Float,toqueY:Float): Boolean {
        var x2 = x + imagen1.width
        var y2 = y + imagen1.height

        if(toqueX >= x && toqueX<= x2){
            if(toqueY >= y && toqueY <= y2){
                return true
            }
        }
        return false
    }

    fun giro(ancho:Int, alto:Int){
        x+=iX
        y+=iY
        if(x<= -50||x>=ancho){
            iX*=-1
            sentido=!sentido
        }

        if(y<=-100||y>=alto){
            iY*=-1
        }
    }

    fun pintar(c: Canvas, p: Paint){
        if(sentido) {
            c.drawBitmap(imagen1, x, y, p)
        } else {
            c.drawBitmap(imagen2, x, y, p)
        }
    }
}