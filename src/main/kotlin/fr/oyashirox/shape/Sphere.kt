package fr.oyashirox.shape

import fr.oyashirox.math.Vector
import fr.oyashirox.raytracing.Hit
import fr.oyashirox.raytracing.HitData
import fr.oyashirox.raytracing.NoHit
import fr.oyashirox.raytracing.Ray
import kotlin.math.sqrt

@Suppress("MemberVisibilityCanBePrivate")
class Sphere(val center: Vector, val radius: Double) : Hitable {
    /* See [this link][https://www.scratchapixel.com/lessons/3d-basic-rendering/minimal-ray-tracer-rendering-simple-shapes/ray-sphere-intersection]
     * for logic.*/
    override fun hit(ray: Ray, min: Double, max: Double): Hit {
        // O: Ray origin, D: Ray direction, C: sphere center, R: sphere radius
        //(O + tD - C)² - R² = 0
        //O² + D²t² + C² + 2(ODt - OC - DCt) - R² = 0
        //D²t + 2D(O - C)t + O² + C² -2OC -R² = 0
        //D²t + 2D(O - C)t + (O - C)² - R² = 0
        //
        //a = D² = 1 (if D is normalized)
        //b = 2D(O - C)
        //c = (O - C)² - R²

        val rayOffset = ray.origin - center
        val a = ray.direction dot ray.direction
        val b = 2.0 * (ray.direction dot rayOffset)
        val c = (rayOffset dot rayOffset) - (radius * radius)

        val delta = b * b - 4 * a * c
        var result: Hit = NoHit

        if (delta > 0) {
            // Test closest point first
            val distance = (-b - sqrt(delta)) / (2.0 * a)
            if (distance > min && distance < max) {
                val position = ray.positionAt(distance)
                result = HitData(distance, position, normalAt(position))
            } else {
                // Test other root
                val distance2 = (-b + sqrt(delta)) / (2.0 * a)
                if (distance2 > min && distance2 < max) {
                    val position = ray.positionAt(distance2)
                    result = HitData(distance2, position, normalAt(position))
                }
            }
        }

        return result
    }

    private fun normalAt(position: Vector) = (position - center).normalize()
}