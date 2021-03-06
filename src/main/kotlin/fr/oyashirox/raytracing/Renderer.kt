package fr.oyashirox.raytracing

import fr.oyashirox.math.Color
import fr.oyashirox.math.Vector
import fr.oyashirox.math.times
import fr.oyashirox.shape.Hitable

class Renderer(private val world: Hitable) {
    private val maxDepth = 50
    private val backgroundTopColor = Color(0.5, 0.7, 1.0)
    private val backgroundBottomColor = Color(1.0, 1.0, 1.0)

    fun color(ray: Ray, depth: Int = 0): Color {
        val hit = world.hit(ray, 0.001, Double.MAX_VALUE)
        return when (hit) {
            is NoHit -> backgroundColor(ray)
            is HitData -> {
                if(depth < maxDepth) {
                    val scatter = hit.material.scatter(ray, hit)
                    when (scatter) {
                        is ScatterData -> return scatter.attenuation * color(scatter.scatteredRay, depth + 1)
                    }
                }
                return Color(0,0,0)
            }
        }
    }

    /** Get background color depending on y coordinate */
    private fun backgroundColor(ray: Ray): Color {
        val mappedValue = 0.5 * (ray.direction.normalize().y + 1.0)
        return gradient(mappedValue)
    }

//    private fun bookBackgroundColor(ray: Ray): Color {
//        val unit = ray.direction.normalize()
//        val t = 0.5 * (unit.y + 1.0)
//        return gradient(t)
//    }

    /** @param y vertical coordinate on the screen. `[0,1]`. 1 is top */
    private fun gradient(y: Double): Color {
        return (1.0 - y) * backgroundBottomColor + y * backgroundTopColor
    }

    /** Gives a color depending on a normal vector.
     * @param normal The normal vector (normalized)*/
    @Suppress("unused")
    private fun normalColor(normal: Vector): Color {
        return 0.5 * Color(normal.x + 1.0, normal.y + 1.0, normal.z + 1.0)
    }
}