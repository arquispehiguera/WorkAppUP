package com.arturo.appwork.Entidades

class Solicitud {
    var IdSolicitud: Int = 0
    var IdCliente: Int = 0
    var IdProveedor: Int = 0
    var IdServicio: Int =0
    lateinit var FechaRegistro: String
    var IdEstadoSol: Int = 0
    var Valoracion: Int = 0
    lateinit var Observaciones: String

    lateinit var nombreUsuario: String
    lateinit var nombreProveedor: String
    lateinit var nombreServicio: String
}