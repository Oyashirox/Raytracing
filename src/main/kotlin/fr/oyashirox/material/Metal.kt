package fr.oyashirox.material

import fr.oyashirox.math.Random
import fr.oyashirox.math.Vector
import fr.oyashirox.math.times
import fr.oyashirox.raytracing.*

@Suppress("MemberVisibilityCanBePrivate")

/** @param albedo attenuation vector
 *  @param fuzziness How fuzzy it is. [0,1]*/
class Metal(val albedo: Vector, fuzziness: Double) : Material {
    val random = Random()
    val fuzz = if (fuzziness < 1) fuzziness else 1.0

    override fun scatter(inRay: Ray, hitData: HitData): Scatter {
        val reflectedDirection = inRay.direction.normalize().reflect(hitData.normal.normalize())
        val scatteredRay = Ray(hitData.position, reflectedDirection + fuzz * random.unitSphere())
        return if (reflectedDirection.dot(hitData.normal) > 0) {
            ScatterData(scatteredRay, albedo)
        } else {
            NoScatter
        }
    }
}