package com.gk.rwsendpoints.services.api;

import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.util.List;

/**
 * Created by greg korenevsky on 7/30/14.
 */
public interface FileListServiceAPI {

    public Pair<File, List<File>> getFileList(String directoryPath) throws Exception;
}
