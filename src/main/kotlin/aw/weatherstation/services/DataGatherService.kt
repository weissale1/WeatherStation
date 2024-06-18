package aw.weatherstation.services

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.io.IOException

//@Service
//class DataGatherService {
//    // Create a logger instance for your class
//    val logger: Logger = LoggerFactory.getLogger(DataGatherService::class.java)
//
//    @Scheduled(fixedRate = 300000) // 300,000 milliseconds = 5 minutes
//    fun runPythonScript() {
//        logger.info("Executing python script.")
//        try {
//            val pythonScriptPath = "./python/gather_data.py"
//            val process = Runtime.getRuntime().exec("python3 $pythonScriptPath")
//
//            val output = process.inputStream.bufferedReader().readText()
//            val error = process.errorStream.bufferedReader().readText()
//
//            if (output.isNotEmpty()) {
//                logger.info(output)
//            }
//            if (error.isNotEmpty()) {
//                logger.error(error)
//            }
//
//            process.waitFor()
//
//        } catch (e: IOException) {
//            logger.warn(e.message)
//        }
//    }
//
//}