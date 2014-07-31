package com.gk.rwsendpoints.services.impl;

import com.gk.rwsendpoints.services.api.FileListServiceAPI;
import org.apache.commons.lang3.tuple.Pair;

import javax.inject.Named;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by greg korenevsky on 7/30/14.
 */
@Named
public class FileListServiceImpl implements FileListServiceAPI {

    @Override
    public Pair<File, List<File>> getFileList(String directoryPath) throws Exception {

        File directory = new File(directoryPath);

        if (!directory.exists()) {
            throw new FileNotFoundException("Invalid path=" + directoryPath);
        }

        if (!directory.isDirectory()) {
            throw new FileNotFoundException("Not a directory, path=" + directoryPath);
        }

        if (!directory.canRead()) {
            throw new IOException("Cannot read directory, path=" + directoryPath);
        }

        File[] directoryContents = directory.listFiles();

        return Pair.of(directory, Arrays.asList(directoryContents));
    }
}
