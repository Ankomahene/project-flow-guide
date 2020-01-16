package io.turntabl.orc.jsonToORC.controller;

import com.google.gson.Gson;
import io.turntabl.orc.jsonToORC.model.Device;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;


public class fromJsonToORC {

    public static final String FILE_EXTENSION_ORC = ".orc";

    private File convertToORC(List<Device> list) {
        JavaSparkContext sparkContext = null;
        File tempFile = null;

        try (SparkSession spark = SparkSession.builder()
                .master("local[4]")
                .appName("sample-application")
                .getOrCreate()) {

            tempFile = this.createTempFile();

            Gson gson = new Gson();
            List<String> data = Arrays.asList(gson.toJson(list));
            sparkContext = JavaSparkContext.fromSparkContext(SparkContext.getOrCreate());
            Dataset<String> stringDataSet = spark.createDataset(data, Encoders.STRING());
            Dataset<Row> orcDataSet = spark.read().json(stringDataSet);
//            log.info("Inserted json conversion schema and value");
            orcDataSet.printSchema();
            orcDataSet.show();
            if (tempFile != null) {
                orcDataSet.write().orc(tempFile.getPath());
                tempFile = this.retrieveORCFileFromPath(tempFile);
            }
        } catch (Exception ex) {
//            log.error("Stack Trace: {}", ex);
        } finally {
            if (sparkContext != null) {
                sparkContext.close();
            }
        }
        return tempFile;
    }

    //Create the temp file path to copy converted parquet data
    private File createTempFile() throws IOException {
        Path tempPath = Files.createTempDirectory("");
        File tempFile = tempPath.toFile();
        if (tempFile != null && tempFile.exists()) {
            String tempFilePath = tempFile.getAbsolutePath();
            tempFile.deleteOnExit();
            Files.deleteIfExists(tempFile.toPath());
//            log.debug("Deleted tempFile[ {} ]}", tempFilePath);
        }
        return tempFile;
    }

    //To get the converted .orc file path
    private File retrieveORCFileFromPath(File tempFilePath) {
        List<File> files = Arrays.asList(tempFilePath.listFiles());
        return files.stream()
                .filter(
                        tmpFile -> tmpFile.getPath().contains(FILE_EXTENSION_ORC) && tmpFile.getPath().endsWith(FILE_EXTENSION_ORC))
                .findAny()
                .orElse(null);
    }
}
