package com.gk.rwsendpoints.util.api;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by engineer on 7/31/14.
 */
public interface PagerAPI<T> {

    public List<T> getPage(Collection<T> input, int pageNumber, int pageSize);
    public List<T> getPage(Iterator<T> inputIter, int pageNumber, int pageSize);
}
