package com.commons.services;

import com.commons.constants.CommonConstants;
import com.google.appengine.tools.cloudstorage.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;

public class GCSService {

    public String uploadFile(byte[] bytes, String fileName, String contentType) throws IOException {

        final GcsService gcsService = GcsServiceFactory.createGcsService();

        String appsGcsBucketName = CommonConstants.gcsBucketName();

        GcsFileOptions options = new GcsFileOptions.Builder()
                .mimeType(contentType)
                .acl("public-read")
                .build();

        GcsFilename gcsFileName = new GcsFilename(appsGcsBucketName, fileName);

        GcsOutputChannel writeChannel = gcsService.createOrReplace(gcsFileName, options);

        writeChannel.write(ByteBuffer.wrap(bytes));
        writeChannel.close();

        String imageFilePath = "https://storage.googleapis.com/" + appsGcsBucketName + "/" + fileName;

        return imageFilePath;
    }

    public String readFile(String fileName) throws IOException {

        final GcsService gcsService = GcsServiceFactory.createGcsService();

        String appsGcsBucketName = CommonConstants.gcsBucketName();

        GcsFilename gcsFileName = new GcsFilename(appsGcsBucketName, fileName);
        GcsInputChannel readChannel = gcsService.openPrefetchingReadChannel(gcsFileName, 0, 1024 * 1024);

        StringWriter writer = new StringWriter();
        IOUtils.copy(Channels.newInputStream(readChannel), writer, "UTF-8");
        return writer.toString();
    }
}
