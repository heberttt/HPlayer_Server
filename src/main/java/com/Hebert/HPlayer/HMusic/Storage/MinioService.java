package com.Hebert.HPlayer.HMusic.Storage;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MinioService {

    private final MinioClient minioClient;
    private final String bucket;

    public MinioService(
            @Value("${app.minio.endpoint}") String endpoint,
            @Value("${app.minio.accessKey}") String accessKey,
            @Value("${app.minio.secretKey}") String secretKey,
            @Value("${app.minio.bucket}") String bucket) {

        this.minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
        this.bucket = bucket;
    }

    /**
     *
     * @param filePath ex: "file/song.mp3"
     *
     */
    public boolean fileExists(String filePath){
        try{
            StatObjectResponse stat = minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucket)
                            .object(filePath)
                            .build()
            );

            return stat != null;

        }catch(Exception e){
            return false;
        }
    }

    public String getPresignedUrl(String filePath, int duration){
        try{
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucket)
                            .object(filePath)
                            .expiry(duration) // in seconds
                            .build()
            );
        } catch (Exception e) {
            System.out.println("Get presigned url error: " + e.toString());
            return null;
        }
    }

}