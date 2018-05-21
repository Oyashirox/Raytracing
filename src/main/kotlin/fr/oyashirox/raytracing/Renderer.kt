package fr.oyashirox.raytracing

import fr.oyashirox.math.Color
import fr.oyashirox.math.map
import fr.oyashirox.math.times
import fr.oyashirox.shape.Hitable


class Renderer(private val camera: Camera, private val world: Hitable) {

    private val backgroundTopColor = Color(0.5, 0.7, 1.0)
    private val backgroundBottomColor = Color(1.0, 1.0, 1.0)
    private val hitColor = Color(1.0, 0.0, 0.0)

    fun color(ray: Ray): Color {
        if (world.hit(ray)) {
            return hitColor
        }
        return backgroundColor(ray)
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
}