package ru.dfhub.test

import org.json.JSONObject
import java.nio.charset.Charset
import java.nio.file.Files
import kotlin.io.path.Path

object Config {
    fun getConfig(): JSONObject = if (Files.exists(Path("./config.json"))) {
        JSONObject(Files.readString(Path("./config.json")))
    } else {
        val resource = this::class.java.classLoader.getResource("config.json")!!.readBytes()
        Files.write(Path("./config.json"), resource)
        JSONObject(resource.toString(Charset.defaultCharset()))
    }
}
