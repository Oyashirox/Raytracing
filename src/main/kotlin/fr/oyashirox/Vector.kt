@file:Suppress("NOTHING_TO_INLINE", "unused")
package fr.oyashirox

import java.lang.Math.abs
import java.lang.Math.sqrt

class Vector(var x: Double = 0.0, var y: Double = 0.0, var z: Double = 0.0) {
    inline var r: Double
        get() = x
        set(value) {
            x = value
        }

    inline var g: Double
        get() = y
        set(value) {
            y = value
        }

    inline var b: Double
        get() = z
        set(value) {
            z = value
        }

    inline operator fun plus(v: Float) = Vector(x + v, y + v, z + v)
    inline operator fun minus(v: Float) = Vector(x - v, y - v, z - v)
    inline operator fun times(v: Float) = Vector(x * v, y * v, z * v)
    inline operator fun div(v: Float) = Vector(x / v, y / v, z / v)

    inline operator fun plus(v: Vector) = Vector(x + v.x, y + v.y, z + v.z)
    inline operator fun minus(v: Vector) = Vector(x - v.x, y - v.y, z - v.z)
    inline operator fun times(v: Vector) = Vector(x * v.x, y * v.y, z * v.z)
    inline operator fun div(v: Vector) = Vector(x / v.x, y / v.y, z / v.z)


    inline infix fun Vector.x(v: Vector): Vector {
        return Vector(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x)
    }

}

inline fun abs(v: Vector) = Vector(abs(v.x), abs(v.y), abs(v.z))
inline fun length(v: Vector) = sqrt(v.x * v.x + v.y * v.y + v.z * v.z)
inline fun length2(v: Vector) = v.x * v.x + v.y * v.y + v.z * v.z
inline fun distance(a: Vector, b: Vector) = length(a - b)
inline fun dot(a: Vector, b: Vector) = a.x * b.x + a.y * b.y + a.z * b.z

fun normalize(v: Vector): Vector {
    val l = 1.0f / length(v)
    return Vector(v.x * l, v.y * l, v.z * l)
}