package com.edu.statsfoot

import android.widget.ImageView

class Player {
    lateinit var image: ImageView
    var position: Posiciones
    var shoot: Int
    var dribble: Int
    var technical: Int
    var defense: Int
    var speed: Int
    var endurance: Int
    var control: Int
    var name: String

    constructor(
        name: String,
        position: Posiciones,
        shoot: Int,
        dribble: Int,
        technical: Int,
        defense: Int,
        speed: Int,
        endurance: Int,
        control: Int
    ) {
        this.name = name
        this.position = position
        this.shoot = shoot
        this.dribble = dribble
        this.technical = technical
        this.defense = defense
        this.speed = speed
        this.endurance = endurance
        this.control = control
    }

    constructor(
        name: String,
        image: ImageView,
        position: Posiciones,
        shoot: Int,
        dribble: Int,
        technical: Int,
        defense: Int,
        speed: Int,
        endurance: Int,
        control: Int
    ) {
        this.name = name
        this.position = position
        this.shoot = shoot
        this.dribble = dribble
        this.technical = technical
        this.defense = defense
        this.speed = speed
        this.endurance = endurance
        this.control = control
        this.image = image
    }

    fun actualizar(
        name: String,
        shoot: Int,
        dribble: Int,
        technical: Int,
        defense: Int,
        speed: Int,
        endurance: Int,
        control: Int
    ) {
        this.name = name
        this.shoot = shoot
        this.dribble = dribble
        this.technical = technical
        this.defense = defense
        this.speed = speed
        this.endurance = endurance
        this.control = control
    }
    fun cambiarTiro(valor:Int){
        this.shoot=valor
    }
    fun cambiarRegate(valor:Int){
        this.dribble=valor
    }
    fun cambiarTecnica(valor:Int){
        this.technical=valor
    }
    fun cambiarDefensa(valor:Int){
        this.defense=valor
        }
    fun cambiarVelocidad(valor:Int){
        this.speed=valor
    }
    fun cambiarAguante(valor:Int){
        this.endurance=valor
    }
    fun cambiarControl(valor:Int){
        this.control=valor
    }
    fun cambiarNombre(valor:String){
        this.name=valor
    }
    fun establecerImagen(valor:ImageView){
        this.image=valor
    }
    fun cambiarPosicion(valor:Posiciones){
        this.position=valor

    }

}