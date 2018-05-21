package fr.oyashirox.shape

import fr.oyashirox.raytracing.Hit
import fr.oyashirox.raytracing.HitData
import fr.oyashirox.raytracing.NoHit
import fr.oyashirox.raytracing.Ray

@Suppress("unused", "MemberVisibilityCanBePrivate")
class HitableList(vararg items: Hitable) : Hitable {
    val items: List<Hitable> = items.toList()

    constructor(items: List<Hitable>): this(*(items.toTypedArray()))

    override fun hit(ray: Ray, min: Double, max: Double): Hit {
        var closest = max
        var result: Hit = NoHit
        for (hitable in items) {
            val hit = hitable.hit(ray, min, closest)
            when (hit) {
                is HitData -> {
                    closest = hit.distance
                    result = hit
                }
            }
        }

        return result
    }

}