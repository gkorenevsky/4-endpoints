package com.gk.rwsendpoints.util.impl;

import com.gk.rwsendpoints.util.api.PagerAPI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by engineer on 7/31/14.
 */
public class PagerImpl<T> implements PagerAPI<T> {

    @Override
    public List<T> getPage(Collection<T> input, int pageNumber, int pageSize) {

        if (input == null) {
            throw  new IllegalArgumentException("input is null");
        }

        Iterator<T> inputIter = input.iterator();
        return getPage(inputIter, pageNumber, pageSize);
    }

    @Override
    public List<T> getPage(Iterator<T> inputIter, int pageNumber, int pageSize) {

        ArrayList<T> result = new ArrayList<T>();

        if (inputIter == null) {
            throw  new IllegalArgumentException("input is null");
        }

        if (pageNumber <= 0) {
            throw  new IllegalArgumentException("pageNumber must be positive");
        }

        if (pageSize <= 0) {
            throw  new IllegalArgumentException("pageSize must be positive");
        }

        // Get to the requested page
        for (int skipCount = (pageNumber - 1) * pageSize;
             inputIter.hasNext() && skipCount > 0;
             skipCount--) {

            inputIter.next();
        }

        for (int resultSize = pageSize;
             resultSize > 0 && inputIter.hasNext();
             resultSize--) {
            result.add(inputIter.next());
        }

        return result;
    }
}
