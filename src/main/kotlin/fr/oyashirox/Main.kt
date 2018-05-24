package fr.oyashirox

import fr.oyashirox.material.Lambertian
import fr.oyashirox.material.Metal
import fr.oyashirox.math.Image
import fr.oyashirox.math.Vector
import fr.oyashirox.raytracing.Camera
import fr.oyashirox.raytracing.Renderer
import fr.oyashirox.shape.HitableList
import fr.oyashirox.shape.Sphere
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import java.awt.Desktop
import java.awt.image.BufferedImage
import java.nio.file.Paths
import java.util.*
import javax.imageio.ImageIO
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {

    val metalSphere = Sphere(
            Vector(-1.0, 0.0, -1.0),
            0.5,
            Metal(Vector(0.8, 0.8, 0.8), 0.05))
    val metalSphere2 = Sphere(
            Vector(1.0, 0.0, -1.0), 0.5,
            Metal(Vector(0.8, 0.6, 0.1), 0.3))
    val sphere = Sphere(
            Vector(0.0, 0.0, -1.0),
            0.5,
            Lambertian(Vector(0.5, 0.5, 0.5)))
    val floorSphere = Sphere(
            Vector(0.0, -100.5, -1.0),
            100.0,
            Lambertian(Vector(0.8, 0.8, 0.0)))
    val world = HitableList(metalSphere, sphere, metalSphere2, floorSphere)

    val camera = Camera(
            lookfrom = Vector(-2.0, 2.0, 1.0),
            lookat = Vector(0.0, 0.0, -1.0)
    )
    val renderer = Renderer(world)

    val aaSample = 100
    val width = 1920
    val height = 1080
    val random = SplittableRandom()

    val completeImage = Image(width, height)
    val executingTime = measureTimeMillis {
        val cores = Runtime.getRuntime().availableProcessors()
        val numberOfPixels = width * height / cores
        val jobs = List(cores) {
            launch {
                val threadTime = measureTimeMillis {
                    val min = it * numberOfPixels
                    val max = min + numberOfPixels - 1
                    println("Executing pixels $min to $max on ${Thread.currentThread().name}")
                    for (n in min..max) {
                        val x = n % width
                        val y = n / width
                        var colorVector = Vector()
                        for (sample in 0 until aaSample) {
                            // Map x to [0,1], but also add a random value (less than 1) to send multiple rays per pixel
                            val u: Double = (x.toDouble() + random.nextDouble()) / width.toDouble()
                            // Invert Y-axis (world coordinates are Y positive going up, while image is Y positive going down)
                            val v: Double = 1.0 - (y.toDouble() + random.nextDouble()) / height.toDouble()
                            val ray = camera.trace(u, v)

                            colorVector += renderer.color(ray).toVector()
                        }

                        completeImage[x, y] = colorVector / aaSample.toDouble()
                    }
                }

                println("Done executing thread $it in $threadTime ms")
            }
        }

        runBlocking {
            jobs.forEach { it.join() }
        }
    }

    println("generating time: $executingTime ms")

    val bufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    bufferedImage.setRGB(0, 0, width, height, completeImage.getColorArray().map { it.argbColor }.toIntArray(), 0, width)

    val file = Paths.get(".", "generated.png").toAbsolutePath().normalize().toFile()
    println("Image saved to : ${file.absolutePath}")
    ImageIO.write(bufferedImage, "png", file)
    Desktop.getDesktop().open(file)
}