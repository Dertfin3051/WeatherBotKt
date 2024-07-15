package ru.dfhub.test

import org.json.JSONObject
import java.io.BufferedReader
import java.io.FileReader
import java.lang.StringBuilder

class Config {
    companion object {
        fun getConfig(): JSONObject {
            val buffer = BufferedReader(FileReader("./config.json"))
            var line: String?
            val configContent = StringBuilder()
            while (true) {
                line = buffer.readLine();
                if (line == null) break
                configContent.append(line)
            }

            return JSONObject(configContent.toString())
        }
    }
}