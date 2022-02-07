package hn.edu.ujcv.pdm_2022_i_l1_equipo4

import java.lang.Exception
import java.util.*

class MainClass() {
    fun main(args: Array<String>) {

        var x: Int
        var y: Int
        var sc = Scanner(System.`in`)
        do {


            x = leerEnteros(sc,"--------------------------------\n" +
                    "             Menú               \n" +
                    "--------------------------------\n" +
                    "Seleccione una opción \n" +
                    "1.Cobrar ENEE \n" +
                    "2.Cobrar SANAA \n" +
                    "3.Recargar TIGO \n" +
                    "4.Recargar CLARO \n" +
                    "5.Debitar Cuenta \n" +
                    "6.Acreditar Cuenta \n" +
                    "7.Salir","Solo se permiten números, seleccione una opción")


            when(x){

                1       -> {

                    var nombre: String
                    do {
                        println("Ingrese el nombre del cliente")
                        nombre = readLine()!!.toString()
                    }while (validarTexto(nombre)!=true)

                    var codigo: Int = leerEnteros(sc,"Ingrese el código del cliente","El código solo debe de contener números")

                    println("Ingrese dirección del cliente")
                    var direccion: String = readLine()!!.toString()

                    var consumo: Int
                    do{
                        consumo = leerEnteros(sc,"Ingrese tipo de consumo \n1.Comercial \n2.Residencial","Solo se permiten números")
                        if(!(consumo>0 && consumo<3)) println("Seleccione una de la dos opciones")
                    }while (!(consumo>0 && consumo<3))

                    val enee:ServicioPublicos.Enee = ServicioPublicos.Enee(nombre,codigo,direccion,consumo)

                    enee.consumoKWH = leerDecimales(sc,"Ingrese el consumo de KW/H","Solo se permiten números")
                    enee.imprimirFactura()

                }

                2       -> {

                    var nombre: String
                    do {
                        println("Ingrese el nombre del cliente")
                        nombre = readLine()!!.toString()
                    }while (validarTexto(nombre)!=true)

                    var codigo: Int = leerEnteros(sc,"Ingrese el código del cliente","El código solo debe de contener números")

                    println("Ingrese dirección del cliente")
                    var direccion: String = readLine()!!.toString()

                    var consumo: Int
                    do{
                        consumo = leerEnteros(sc,"Ingrese tipo de consumo \n1.Comercial \n2.Residencial","Solo se permiten números")
                        if(!(consumo>0 && consumo<3)) println("Seleccione una de la dos opciones")
                    }while (!(consumo>0 && consumo<3))

                    val sanaa:ServicioPublicos.Sanaa = ServicioPublicos.Sanaa(nombre,codigo,direccion,consumo)

                    sanaa.consumoAguaPotable = leerDecimales(sc,"Ingrese el consumo de agua potable","Solo se permiten números")

                    sanaa.valorAlcantarillado = leerDecimales(sc,"Ingrese el valor del alcanterillado","Solo se permiten números")
                    sanaa.imprimirFactura()

                }
                3       -> {
                    var celular1 = celular()
                    celular1.String = "Tigo"
                    println(celular1.String)
                    celular1.recargarTigo()
                }
                4       -> {
                    var celular = celular2()
                    celular.telefono = "Claro"
                    println(celular.telefono)
                    celular.recargarClaro()
                }
                5       -> {
                    var c1:Cuenta = Cuenta()
                    c1.menuDebitar()
                }
                6       ->{
                    var c2:Cuenta = Cuenta()
                    c2.menuAcreditar()
                }

            }

        } while (x != 7)

    }
    fun leerEnteros(readLine: Scanner, mensaje:String, mensajeError:String):Int{
        var retval: Int = 0
        println(mensaje)

        while (!readLine.hasNextInt()) {
            readLine.nextLine();
            println(mensajeError);
        }
        retval = readLine.nextInt()
        readLine.nextLine()

        return retval

    }


    fun leerDecimales(readLine: Scanner, mensaje:String, mensajeError:String):Double{
        var retval: Double = 0.0
        println(mensaje)

        while (!readLine.hasNextDouble()) {
            readLine.nextLine();
            println(mensajeError);
        }
        retval = readLine.nextDouble()
        readLine.nextLine()

        return retval

    }
    fun validarTexto(texto:String):Boolean{
        var c:Char
        for(i in 1..texto.length-1){
            c = texto.get(i)
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                println("Este texto contiene números, sólo se permiten letras.")
                return false;
            }
        }
        return true
    }

}
//MainClass().main(arrayOf(""))



open class ServicioPublicos {
    var nombre : String = "" //nombre de cliente
    var codigo : Int = 0 //codigo de cliente
    var direccion : String = ""  // direccion del cliente
    var tipoConsumo: Int = 0  //1. Comercial, 2. Residencial

    constructor()
    constructor(nombre:String, codigo: Int, direccion:String, tipoConsumo:Int){
        //validacion tipo de consumo
        if(tipoConsumo > 2){
            // println("Solamente puede seleccionar 2 tipos de consumo. 1.Comercial o 2.Residencial")
        }else{
            this.tipoConsumo = tipoConsumo
        }

        this.nombre = nombre
        this.codigo = codigo
        this.direccion = direccion
    }

    fun imprimirTipoConsumo() : String{
        if(tipoConsumo == 1){
            return "Comercial"
        }else{
            return "Residencial"
        }
    }



    class Sanaa(nombreCliente:String, codigo:Int, direccion:String, tipoConsumo:Int):
        ServicioPublicos(nombreCliente, codigo, direccion, tipoConsumo) {

        var cargoFijo:Double = establecerCargoFijo()

        fun establecerCargoFijo():Double{
            when(tipoConsumo){
                1 -> return 175.00 //comercial
                2 -> return 0.00 //residencial
                else ->{
                    return 0.00
                }
            }
        }

        var consumoAguaPotable : Double = 0.00
            set(value) {
                if (value <= 0){
                    println("El valor del consumo no puede ser menor o igual a 0.")
                }else{
                    field = value
                }
            }
        var valorAlcantarillado : Double = 0.00
            set(value) {
                if (value <= 0){
                    println("El valor del consumo no puede ser menor o igual a 0.")
                }else{
                    field = value
                }
            }

        fun calculoValorFactura() : Double{
            return consumoAguaPotable + valorAlcantarillado + cargoFijo;
        }

        fun imprimirFactura(){

            println("**********")
            println("\t\tDatos del cliente")
            println("Nombre: " + this.nombre)
            println("Codigo de cliente:" + this.codigo)
            println("Direccion: " + this.direccion)
            println("Tipo de consumo: " + this.imprimirTipoConsumo())
            println("**********")
            println("\t\tDatos de consumo")
            println("Consumo de agua potable:  $consumoAguaPotable")
            println("Valor de alcantarillado: $valorAlcantarillado")
            println("Cargo fijo: $cargoFijo")
            println("Total factura: Lps.\t" + calculoValorFactura())
            println("**********")
        }
    }

    class Enee(nombreCliente: String, codigo: Int, direccion: String, tipoConsumo: Int) :
        ServicioPublicos(nombreCliente, codigo, direccion, tipoConsumo) {

        var consumoKWH : Double = 0.0
            set(value) {
                if (value <= 0){
                    println("El consumo no puede ser menor o igual a 0.")
                }else{
                    field = value
                }
            }

        fun calculoValorFactura(): Double {
            var calculoMenor = if(consumoKWH >=50) 50 * 4.64 else consumoKWH * 4.64 //calculo de consumo menor o igual a 50 kWh
            var calculoMayor = if(consumoKWH - 50 < 0) 0.00 else (consumoKWH - 50) * 6.03 //calculo de energia restante
            return calculoMenor + calculoMayor;
        }

        fun imprimirFactura(){

            println("**********")
            println("\t\tDatos del cliente")
            println("Nombre: " + this.nombre)
            println("Codigo de cliente:" + this.codigo)
            println("Direccion: " + this.direccion)
            println("Tipo de consumo: " + this.imprimirTipoConsumo())
            println("**********")
            println("\t\tDatos de consumo")
            println("Consumo(kWh): $consumoKWH")
            println("Total factura: Lps.\t" + calculoValorFactura())
            println("**********")
        }
    }


}

class celular{

    var sc = Scanner(System.`in`)
    var String = "Tigo"
    fun recargarTigo(){

        try {

            var telefono = leerEnteros(sc,"Ingrese su número telefónico","Solo se permiten números")

            while(telefono.toString().length != 8){
                println("Error, el teléfono debe de contener 8 digitos")
                telefono = leerEnteros(sc,"Ingrese su número telefónico","Solo se permiten números")
            }

            do{

                var opcion = leerEnteros(sc,"PaqueTigos \n" +
                        "Ingresa una opcion valida: \n" +
                        "1.- Llamadas \n" +
                        "2.- Super Recarga \n" +
                        "3.- Internet \n" +
                        "4.- Salir ","Solo se permiten números, seleccione una opción")

                when (opcion) {
                    1 -> {


                        try{
                            do{
                                var opcion1 = leerEnteros(sc,"Seleccionaste Llamadas \n" +
                                        "1.- 15min TR/USA (L.18/2dias) \n" +
                                        "2.- 20min Tigo (L.17/2dias) \n" +
                                        "3.- Salir ","Solo se permiten números, seleccione una opción")

                                when (opcion1) {
                                    1 -> println("Se ingresó 15min TR/USA (L.18/2dias) con éxito al número $telefono")
                                    2 -> println("Se ingresó 20min Tigo (L.17/2dias) con éxito al número $telefono")
                                    3 -> println("Seleccionó salir")
                                    else -> println("la opción ${opcion1} no está en la lista")
                                }

                            }while (opcion1 != 3)
                        }catch (e: Exception){
                            println("coloque una opción valida ${e}")
                        }
                    }
                    2 ->{
                        try {
                            do{

                                var opcion2 = leerEnteros(sc,"Seleccionaste Super Recarga \n" +
                                        "1.- Super Recarga 7 dias 10.5GB \n" +
                                        "2.- Super Recarga 3 dias 5.5GB \n" +
                                        "3.- Super Recarga 1 dias 1.7GB \n" +
                                        "4.- Salir ","Solo se permiten números, seleccione una opción")


                                when (opcion2) {
                                    1 -> println("Se ingresó Super Recarga 7 dias 10.5GB con éxito al número $telefono")
                                    2 -> println("Se ingresó Super Recarga 3 dias 5.5GB con éxito al número $telefono")
                                    3 -> println("Se ingresó Super Recarga 1 dias 1.7GB con éxito al número $telefono")
                                    4 -> println("Seleccionó salir")
                                    else -> println("la opción ${opcion2} no está en la lista")
                                }
                            }while (opcion2 != 4)

                        }catch (e: Exception){
                            println("coloque una opcion valida ${e}")
                        }
                    }
                    3 -> {
                        try{
                            do{

                                var opcion3 = leerEnteros(sc,"Seleccionaste Internet \n" +
                                        "1.- WhatsApp chat ilim+100MB (L.32/1dia) \n" +
                                        "2.- 100MB+FB/WhatsApp (L.45/1dia) \n" +
                                        "3.- 500MB (L.105/7dias) \n" +
                                        "4.- Salir ","Solo se permiten números, seleccione una opción")

                                when(opcion3){
                                    1 -> println("Se ingresó WhatsApp chat ilim+100MB (L.32/1dia) con éxito al número $telefono")
                                    2 -> println("Se ingresó 100MB+FB/WhatsApp (L.45/1dia) con éxito al número $telefono")
                                    3 -> println("Se ingresó 500MB (L.105/7dias) con éxito al número $telefono")
                                    4 -> println("Seleccionó salir")
                                    else -> println("la opción ${opcion3} no está en la lista")
                                }
                            }while (opcion3 != 4)
                        }catch (e: Exception){
                            println("coloque una opción valida ${e}")
                        }
                    }
                    4 -> {
                        println("Seleccionaste salir")
                    }
                    else -> println("la opción ${opcion} no está en la lista")
                }
            }while (opcion != 4)

        }catch (e: Exception){
            println("coloque una opción valida ${e}")
        }
    }

    fun leerEnteros(readLine: Scanner, mensaje:String, mensajeError:String):Int{
        var retval: Int = 0
        println(mensaje)

        while (!readLine.hasNextInt()) {
            readLine.nextLine();
            println(mensajeError);
        }
        retval = readLine.nextInt()
        readLine.nextLine()

        return retval

    }
}

class celular2{

    var sc = Scanner(System.`in`)
    var telefono = "Claro"
    fun recargarClaro(){


        try {

            var numero = leerEnteros(sc,"Ingrese su número telefónico","Solo se permiten números")

            while(numero.toString().length != 8){
                println("Error, el teléfono debe de contener 8 digitos")
                numero = leerEnteros(sc,"Ingrese su número telefónico","Solo se permiten números")
            }

            do {

                var opcion =  leerEnteros(sc,"Mi Claro \n" +
                        "Ingresa una opcion valida: \n" +
                        "1.- Llamadas \n" +
                        "2.- Super Recarga \n" +
                        "3.- Internet \n" +
                        "4.- Salir ","Solo se permiten números, seleccione una opción")

                when (opcion) {
                    1 -> {

                        try {
                            do {

                                var opcion1 = leerEnteros(sc,"Seleccionaste Llamadas \n" +
                                        "1.- 40min TR/USA (L.22/2dias) \n" +
                                        "2.- 1hr Clar0 (L.30/2dias) \n" +
                                        "3.- Salir ","Solo se permiten números, seleccione una opción")

                                when (opcion1) {
                                    1 -> println("Se ingresó 40min TR/USA (L.22/2dias) con éxito al número $numero")
                                    2 -> println("Se ingresó 1hr Clar0 (L.30/2dias) con éxito al número $numero")
                                    3 -> println("Selecciono salir")
                                    else -> println("la opcion ${opcion1} no esta en la lista")
                                }
                            }while (opcion1 !=3)
                        } catch (e: Exception) {
                            println("coloque una opción valida ${e}")
                        }
                    }
                    2 -> {

                        try {
                            do{

                                var opcion2 = leerEnteros(sc,"Seleccionaste Super Recarga \n" +
                                        "1.- Super Recarga 7 dias 20GB \n" +
                                        "2.- Super Recarga 3 dias 5GB \n" +
                                        "3.- Super Recarga 1 dias 50GB \n" +
                                        "4.- Salir ","Solo se permiten números, seleccione una opción")

                                when (opcion2) {
                                    1 -> println("Se ingresó Super Recarga 7 dias 20GB con éxito al número $numero")
                                    2 -> println("Se ingresó Super Recarga 3 dias 5GB con éxito al número $numero")
                                    3 -> println("Se ingresó Super Recarga 1 dias 50GB con éxito al número $numero")
                                    4 -> println("Selecciono salir")
                                    else -> println("la opción ${opcion2} no esta en la lista")
                                }
                            }while (opcion2 !=4)
                        } catch (e: Exception) {
                            println("coloque una opcion valida ${e}")
                        }
                    }
                    3 -> {

                        try {
                            do{

                                var opcion3 = leerEnteros(sc,"Seleccionaste Internet \n" +
                                        "1.- WhatsApp chat ilim+500MB (L.60/1dia) \n" +
                                        "2.- 500MB+FB/WhatsApp (L.75/1dia) \n" +
                                        "3.- 500MB (L.105/7dias) \n" +
                                        "4.- Salir ","Solo se permiten números, seleccione una opción")

                                when (opcion3) {
                                    1 -> println("Se ingresó WhatsApp chat ilim+500MB (L.60/1dia) con éxito al número $numero")
                                    2 -> println("Se ingresó 500MB+FB/WhatsApp (L.75/1dia) con éxito al número $numero")
                                    3 -> println("Se ingresó 500MB (L.105/7dias) con éxito al número $numero")
                                    4 -> println("Selecciono salir")
                                    else -> println("la opción ${opcion3} no esta en la lista")
                                }
                            }while (opcion3 !=4)
                        } catch (e: Exception) {
                            println("coloque una opcion valida ${e}")
                        }
                    }
                    4 -> {
                        println("Seleccionaste salir")
                    }
                    else -> println("la opcion ${opcion} no esta en la lista")
                }
            }while (opcion != 4)
        }catch (e:Exception){
            println("coloque una opcion valida ${e}")
        }
    }
    fun leerEnteros(readLine: Scanner, mensaje:String, mensajeError:String):Int{
        var retval: Int = 0
        println(mensaje)

        while (!readLine.hasNextInt()) {
            readLine.nextLine();
            println(mensajeError);
        }
        retval = readLine.nextInt()
        readLine.nextLine()

        return retval

    }
}


class Cuenta {

    var numeroCuentaOrigen: Int = 0
    var numeroCuentaDestino: Int = 0
    var monto: Double = 0.0
    var total:Double = 0.0
    var sc = Scanner(System.`in`)

    constructor()

    constructor(numeroOrigen: Int, numeroDestino:Int, Total:Double ){
        numeroCuentaOrigen = numeroOrigen
        numeroCuentaDestino = numeroDestino
        total = Total
    }

    fun menuAcreditar(){

        var x: Int

        var c: Cuenta = Cuenta()
        var estado: Boolean = false
        var arrayCuentas = arrayListOf<Cuenta>()
        val cuenta1: Cuenta = Cuenta(12345678, 122122, 6000.00)
        val cuenta2: Cuenta = Cuenta(98765432, 122122, 500.00)
        val cuenta3: Cuenta = Cuenta(11223344, 122122, 2100.00)
        arrayCuentas.add(cuenta1)
        arrayCuentas.add(cuenta2)
        arrayCuentas.add(cuenta3)

        do {

            var NumCuenta = leerEnteros(sc,"Ingrese la cuenta a acreditar","La cuenta solo debe de contener números")

            while (NumCuenta.toString().length != 8) {
                println("Error, el número de cuenta debe de tener 8 digitos")
                NumCuenta = leerEnteros(sc,"Ingrese la cuenta a acreditar","La cuenta solo debe de contener números")
            }


            for (cuenta in arrayCuentas) {

                if (cuenta.numeroCuentaOrigen == NumCuenta) {
                    c.numeroCuentaDestino = NumCuenta
                    estado = true
                }
            }

            if (estado == true) {
                c.acreditar()
            } else {
                println("La cuenta no existe")
            }

            do {
                x = leerEnteros(sc,"¿Desea acreditar otra vez? \n1. Sí \n2. No","Solo se permiten números")
            }while(x > 2 || x < 1)


        }while (x != 2)

    }

    fun menuDebitar(){

        var x: Int


        var c: Cuenta = Cuenta()
        var estado: Boolean = false
        var arrayCuentas = arrayListOf<Cuenta>()
        val cuenta1: Cuenta = Cuenta(12345678, 122122, 6000.00)
        val cuenta2: Cuenta = Cuenta(98765432, 122122, 500.00)
        val cuenta3: Cuenta = Cuenta(11223344, 122122, 2100.00)
        arrayCuentas.add(cuenta1)
        arrayCuentas.add(cuenta2)
        arrayCuentas.add(cuenta3)

        do {

            var NumCuenta = leerEnteros(sc,"Ingrese la cuenta a debitar","La cuenta solo debe de contener números")

            while (NumCuenta.toString().length != 8) {
                println("Error, el número de cuenta debe de tener 8 digitos")
                NumCuenta = leerEnteros(sc,"Ingrese la cuenta a debitar","La cuenta solo debe de contener números")
            }


            for (cuenta in arrayCuentas) {

                if (cuenta.numeroCuentaOrigen == NumCuenta) {
                    c.numeroCuentaOrigen = NumCuenta
                    c.total = cuenta.total
                    estado = true
                }
            }

            if (estado == true) {
                c.debitar()
            } else {
                println("La cuenta no existe")
            }

            do {
                x = leerEnteros(sc,"¿Desea debitar otra vez? \n1. Sí \n2. No","Solo se permiten números")
            }while(x > 2 || x < 1)


        }while (x != 2)

    }


    fun debitar() {

        println("Tiene un total de: $total disponibles en su cuenta")
        monto = leerDecimales(sc,"-------Ingrese la cantidad que va a debitar------","Solo se permiten números")

        if (monto < 0.0 || monto > 5000.00){
            println("La cantidad ingresada no es permitida, por favor ingrese una cantidad que esté entre 0 y 5,000 Lps")
            debitar()

        }else if (total < monto) {
            println("No puede retirar más de lo que tiene en la cuenta")
            debitar()

        }else{
            total = total - monto
            println("Transacción completada con éxito")
            println("El nuevo total de la cuenta $numeroCuentaOrigen es: $total")
        }
    }

    fun acreditar() {
        monto = leerDecimales(sc,"-------Ingrese la cantidad que va a acreditar------","Solo se permiten números")
        if(monto > 0.0 && monto <= 10000.00){
            println("Transacción realizada éxitosamente, saldo acreditado a la cuenta $numeroCuentaDestino")
        }else{
            println("La cantidad ingresada no es permitida, por favor ingrese una cantidad que esté entre 0 y 10,000 Lps")
            acreditar()
        }
    }

    fun leerEnteros(readLine: Scanner, mensaje:String, mensajeError:String):Int{
        var retval: Int = 0
        println(mensaje)

        while (!readLine.hasNextInt()) {
            readLine.nextLine();
            println(mensajeError);
        }
        retval = readLine.nextInt()
        readLine.nextLine()

        return retval

    }


    fun leerDecimales(readLine: Scanner, mensaje:String, mensajeError:String):Double{
        var retval: Double = 0.0
        println(mensaje)

        while (!readLine.hasNextDouble()) {
            readLine.nextLine();
            println(mensajeError);
        }
        retval = readLine.nextDouble()
        readLine.nextLine()

        return retval

    }

}

class PDM_2022_I_L1_EQUIPO4 {
}