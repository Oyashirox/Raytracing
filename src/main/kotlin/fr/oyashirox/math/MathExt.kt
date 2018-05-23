package fr.oyashirox.math

import java.util.*

fun map(value: Double, start: Double, end: Double, newStart: Double, newEnd: Double): Double {
    val slope = (newEnd - newStart) / (end - start)
    return newStart + slope * (value - start)
}

fun randomUnitSphere(): Vector {
    val random = Random()
    var vector: Vector
    do {
        // Generate a random point in [-1,1] for all component
        vector = 2.0 * Vector(random.nextDouble(), random.nextDouble(), random.nextDouble()) - Vector(1.0, 1.0, 1.0)
    } while (vector.length() >= 1.0)
    return vector
}