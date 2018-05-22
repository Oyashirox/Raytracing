package fr.oyashirox.raytracing

import fr.oyashirox.math.Color
import fr.oyashirox.math.Vector
import fr.oyashirox.math.map
import fr.oyashirox.math.times
import fr.oyashirox.shape.Hitable
import java.util.*


class Renderer(private val camera: Camera, private val world: Hitable) {

    private val backgroundTopColor = Color(0.5, 0.7, 1.0)
    private val backgroundBottomColor = Color(1.0, 1.0, 1.0)

    fun color(ray: Ray): Color {
        val hit = world.hit(ray, 0.001, Double.MAX_VALUE)
        return when (hit) {
            is NoHit -> backgroundColor(ray)
            is HitData -> 0.5 * color(scatter(hit))
        }
    }

    private fun scatter(data: HitData) = Ray(data.position, data.position + data.normal + randomUnitSphere())

    private fun randomUnitSphere(): Vector {
        val random = Random()
        var vector: Vector
        do {
            // Generate a random point in [-1,1] for all component
            vector = 2.0 * Vector(random.nextDouble(), random.nextDouble(), random.nextDouble()) - Vector(1.0, 1.0, 1.0)
        } while (vector.length() >= 1.0)
        return vector
    }

    /** Get background color depending on y coordinate */
    private fun backgroundColor(ray: Ray): Color {
        val minY = camera.lowerLeft.y
        val maxY = (camera.lowerLeft + camera.vertical).y
        val mappedValue = map(ray.direction.y, minY, maxY, 0.0, 1.0)
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
    private fun normalColor(normal: Vector): Color {
        return 0.5 * Color(normal.x + 1.0, normal.y + 1.0, normal.z + 1.0)
    }
}