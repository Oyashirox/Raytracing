package fr.oyashirox

import fr.oyashirox.entity.Camera
import java.awt.Desktop
import java.awt.image.BufferedImage
import java.nio.file.Paths
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    val camera = Camera()
    val renderer = Renderer(camera)

    val image = BufferedImage(1280, 720, BufferedImage.TYPE_INT_ARGB)

    for (x in 0 until image.width) {
        for (y in 0 until image.height) {
            val u: Double = x.toDouble() / image.width.toDouble()
            // Invert Y-axis (world coordinates are Y positive going up, while image is Y positive going down)
            val v: Double = 1.0 - y.toDouble() / image.width.toDouble()
            val ray = camera.trace(u, v)
            val color = renderer.color(ray)
            image.setRGB(x, y, color.argbColor)
        }
    }

    val file = Paths.get(".", "generated.png").toAbsolutePath().normalize().toFile()
    println("Image saved to : ${file.absolutePath}")
    ImageIO.write(image, "png", file)
    Desktop.getDesktop().open(file)
}