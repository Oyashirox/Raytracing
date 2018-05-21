package fr.oyashirox.shape

import fr.oyashirox.math.Vector
import fr.oyashirox.raytracing.Ray

interface Hitable {
    /** Return the parameter on the ray if hit, -1 if does not */
    fun hit(ray: Ray): Double

    fun normalAt(position: Vector): Vector
}