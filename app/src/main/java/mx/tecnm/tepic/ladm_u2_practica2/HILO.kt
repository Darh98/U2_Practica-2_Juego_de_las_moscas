package mx.tecnm.tepic.ladm_u2_practica2

class Hilo(p: MainActivity): Thread(){
    var puntero = p
    override fun run(){
        super.run()

        while(true){
            sleep(200)
            puntero.runOnUiThread {
                puntero.l!!.movimiento()
            }
        }
    }
}