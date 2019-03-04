package com.rimi.hbase;

import org.junit.Test;

import static org.junit.Assert.*;

public class HBaseFilterTest {

    @Test
    public void rowFilter() throws Exception {
        HBaseFilter hBaseFilter = new HBaseFilter();
        hBaseFilter.rowFilter();
    }
}