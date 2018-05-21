package fr.oyashirox.math

fun map(value: Double, start: Double, end: Double, newStart: Double, newEnd: Double): Double {
    val slope = (newEnd - newStart) / (end - start)
    return newStart + slope * (value - start)
}