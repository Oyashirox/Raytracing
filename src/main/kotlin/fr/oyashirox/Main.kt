package fr.oyashirox

import fr.oyashirox.math.Vector
import fr.oyashirox.raytracing.Camera
import fr.oyashirox.raytracing.Renderer
import fr.oyashirox.shape.HitableList
import fr.oyashirox.shape.Sphere
import java.awt.Desktop
import java.awt.image.BufferedImage
import java.nio.file.Paths
import java.util.*
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    val aaSample = 100
    val sphere = Sphere(Vector(0.0, 0.0, -1.0), 0.5)
    val floorSphere = Sphere(Vector(0.0, -100.5, -1.0), 100.0)
    val world = HitableList(sphere, floorSphere)

    val camera = Camera()
    val renderer = Renderer(camera, world)

    val image = BufferedImage(1920, 1080, BufferedImage.TYPE_INT_ARGB)
    val random = Random()

    for (x in 0 until image.width) {
        for (y in 0 until image.height) {
            var colorVector = Vector()

            for (n in 0 until aaSample) {
                // Map x to [0,1], but also add a random value (less than 1) to send multiple rays per pixel
                val u: Double = (x.toDouble() + random.nextDouble())/ image.width.toDouble()
                // Invert Y-axis (world coordinates are Y positive going up, while image is Y positive going down)
                val v: Double = 1.0 - (y.toDouble() + random.nextDouble()) / image.height.toDouble()
                val ray = camera.trace(u, v)
                colorVector += renderer.color(ray).toVector()
            }

            // Compute the average of the samples for the current pixel
            val color = (colorVector / aaSample.toDouble()).toColor()
            image.setRGB(x, y, color.gamma2().argbColor)
        }
    }

    val file = Paths.get(".", "generated.png").toAbsolutePath().normalize().toFile()
    println("Image saved to : ${file.absolutePath}")
    ImageIO.write(image, "png", file)
    Desktop.getDesktop().open(file)
}