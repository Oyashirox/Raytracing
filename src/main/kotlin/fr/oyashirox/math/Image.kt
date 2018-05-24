package fr.oyashirox.math

@Suppress("NOTHING_TO_INLINE", "MemberVisibilityCanBePrivate")
class Image(val width: Int, val height: Int) {
    val data = Array(width * height) { Vector() }

    inline operator fun get(x: Int, y: Int) = data[x + y * width]
    inline operator fun set(x: Int, y: Int, color: Color) {
        data[x + y * width] = color.toVector()
    }
    inline operator fun set(x: Int, y: Int, vector: Vector) {
        data[x + y * width] = vector
    }

    operator fun plusAssign(o: Image) {
        for (x in 0 until width) {
            for (y in 0 until height) {
                this[x, y] = this[x, y] + o[x, y]
            }
        }
    }

    /** @return a color array gamma corrected of size [width] x [height] */
    fun getColorArray() = Array(width * height) {
        data[it].toColor().gamma2()
    }
}