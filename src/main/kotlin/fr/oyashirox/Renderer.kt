package fr.oyashirox

import fr.oyashirox.entity.Camera
import fr.oyashirox.entity.Color
import fr.oyashirox.entity.Ray
import fr.oyashirox.entity.map
import fr.oyashirox.entity.times


class Renderer(private val camera: Camera) {

    private val backgroundTopColor = Color(0.5, 0.7, 1.0)
    private val backgroundBottomColor = Color(1.0, 1.0, 1.0)

    fun color(ray: Ray): Color {
        val minY = camera.lowerLeft.y
        val maxY = (camera.lowerLeft + camera.vertical).y
        val mappedValue = map(ray.direction.y, minY, maxY, 0.0, 1.0)
        return backgroundColor(mappedValue)
    }

    /** Get background color depending on y coordinate
     *
     * @param y vertical coordinate on the screen. `[0,1]`. 1 is top*/
    private fun backgroundColor(y: Double): Color {
        return (1.0 - y) * backgroundBottomColor + y * backgroundTopColor
    }
}