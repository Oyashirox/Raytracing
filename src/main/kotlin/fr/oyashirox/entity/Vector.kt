@file:Suppress("NOTHING_TO_INLINE", "unused", "MemberVisibilityCanBePrivate")
package fr.oyashirox.entity

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

    inline operator fun plus(v: Double) = Vector(x + v, y + v, z + v)
    inline operator fun minus(v: Double) = Vector(x - v, y - v, z - v)
    inline operator fun times(v: Double) = Vector(x * v, y * v, z * v)
    inline operator fun div(v: Double) = Vector(x / v, y / v, z / v)

    inline operator fun plus(v: Vector) = Vector(x + v.x, y + v.y, z + v.z)
    inline operator fun minus(v: Vector) = Vector(x - v.x, y - v.y, z - v.z)
    inline operator fun times(v: Vector) = Vector(x * v.x, y * v.y, z * v.z)
    inline operator fun div(v: Vector) = Vector(x / v.x, y / v.y, z / v.z)

    inline fun abs() = Vector(abs(x), abs(y), abs(z))
    inline fun length() = sqrt(length2())
    inline fun length2() = x * x + y * y + z * z
    inline fun distanceTo(o: Vector) = (this - o).length()
    inline infix fun dot(o: Vector) = x * o.x + y * o.y + z * o.z

    fun normalize(): Vector {
        val l = 1.0f / length()
        return Vector(x * l, y * l, z * l)
    }
}

inline operator fun Double.plus(v: Vector) = Vector(this + v.x, this + v.y, this + v.z)
inline operator fun Double.minus(v: Vector) = Vector(this - v.x, this - v.y, this - v.z)
inline operator fun Double.times(v: Vector) = Vector(this * v.x, this * v.y, this * v.z)
inline operator fun Double.div(v: Vector) = Vector(this / v.x, this / v.y, this / v.z)
