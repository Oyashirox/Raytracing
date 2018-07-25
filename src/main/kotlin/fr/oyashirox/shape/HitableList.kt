package fr.oyashirox.shape

import fr.oyashirox.raytracing.Hit
import fr.oyashirox.raytracing.HitData
import fr.oyashirox.raytracing.NoHit
import fr.oyashirox.raytracing.Ray

@Suppress("unused", "MemberVisibilityCanBePrivate")
class HitableList(val items: List<Hitable>) : Hitable {
    constructor(vararg items: Hitable) : this(items.toList())

    override fun hit(ray: Ray, min: Double, max: Double): Hit {
        var closest = max
        var result: Hit = NoHit
        for (hitable in items) {
            val hit = hitable.hit(ray, min, closest)
            when (hit) {
                is HitData ->
                    if (hit.distance < closest) {
                    closest = hit.distance
                    result = hit
                }
            }
        }

        return result
    }

}