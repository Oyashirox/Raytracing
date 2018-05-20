package fr.oyashirox.entity

@Suppress("unused", "MemberVisibilityCanBePrivate")
/**
 * Camera using right hand coordinates: X going right, Y going up, -Z going into the screen.
 *
 * Using hard coded 16:9 ratio, position at 0,0,0 & looking in front (0, 0, -1)
 * */
class Camera {
    private val origin = Vector()

    val lowerLeft: Vector = Vector(-8.0, -4.5, -1.0)
    val horizontal: Vector = Vector(16.0, 0.0, 0.0)
    val vertical: Vector = Vector(0.0, 9.0, 0.0)

    /**
     * create a ray
     *
     * @param u horizontal position on screen `[0,1]`. 0 is left, 1 is right
     * @param v vertical position on screen `[0,1]`. 0 is bottom, 1 is top (right hand coordinates, remember ?)
     * */
    fun trace(u: Double, v: Double): Ray {
        val direction = lowerLeft + u * horizontal + v * vertical
        return Ray(origin, direction)
    }

}