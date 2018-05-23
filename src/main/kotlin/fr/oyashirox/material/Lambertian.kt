package fr.oyashirox.material

import fr.oyashirox.math.Vector
import fr.oyashirox.math.randomUnitSphere
import fr.oyashirox.raytracing.HitData
import fr.oyashirox.raytracing.Ray
import fr.oyashirox.raytracing.Scatter
import fr.oyashirox.raytracing.ScatterData

@Suppress("MemberVisibilityCanBePrivate")
class Lambertian(val albedo: Vector): Material {

    override fun scatter(inRay: Ray, hitData: HitData): Scatter {
        val target = hitData.position + hitData.normal + randomUnitSphere()
        val scatteredRay = Ray(hitData.position, target - hitData.position)
        return ScatterData(
                scatteredRay,
                albedo
        )
    }
}