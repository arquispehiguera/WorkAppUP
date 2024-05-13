package com.arturo.appwork.Entidades

class Usuario {
    var IdUsuario: Int = 0
    lateinit var Nombre: String
    lateinit var Dni: String
    lateinit var Telefono: String
    lateinit var Email: String
    lateinit var Password: String
    lateinit var ServiciosID: String
    var IdTipoUsuario: Int = 0

    lateinit var ServiciosDetalle: String
}
