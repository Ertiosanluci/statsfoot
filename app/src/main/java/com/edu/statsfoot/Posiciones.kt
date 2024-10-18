package com.edu.statsfoot

enum class Posiciones {
    Delantero,
    Defensa,
    Centrocampista,
    Portero,
    Lateral;

    override fun toString(): String {
        return this.name
    }
}