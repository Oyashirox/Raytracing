package fr.oyashirox

import java.awt.Desktop
import java.awt.image.BufferedImage
import java.nio.file.Paths
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    val image = BufferedImage(1280, 720, BufferedImage.TYPE_INT_ARGB)

    for (x in 0 until image.width) {
        for (y in 0 until image.height) {
            val colorX = Color(x.toDouble()/image.width.toDouble(), 0.0, 0.0)
            val colorY = Color(0.0, y.toDouble()/image.height.toDouble(), 0.0)
            val combinedColor = colorX + colorY
            image.setRGB(x, y, combinedColor.argbColor)
        }
    }

    val file = Paths.get(".", "generated.png").toAbsolutePath().normalize().toFile()
    println("Image saved to : ${file.absolutePath}")
    ImageIO.write(image, "png", file)
    Desktop.getDesktop().open(file)
}