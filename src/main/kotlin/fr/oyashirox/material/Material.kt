package fr.oyashirox.material

import fr.oyashirox.raytracing.HitData
import fr.oyashirox.raytracing.Ray
import fr.oyashirox.raytracing.Scatter

interface Material {
    fun scatter(inRay: Ray, hitData: HitData): Scatter
}