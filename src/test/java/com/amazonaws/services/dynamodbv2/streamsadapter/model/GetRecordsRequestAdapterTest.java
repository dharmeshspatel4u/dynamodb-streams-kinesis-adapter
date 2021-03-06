/*
 * Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Amazon Software License (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/asl/
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.services.dynamodbv2.streamsadapter.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amazonaws.services.kinesis.model.GetRecordsRequest;

public class GetRecordsRequestAdapterTest {
    private final String TEST_STRING = "TestString";
    private final Integer TEST_INT = 42;

    @Mock
    private GetRecordsRequest mockRequest;

    private GetRecordsRequestAdapter adapter;

    @Before
    public void setUpTest() {
        MockitoAnnotations.initMocks(this);
        adapter = new GetRecordsRequestAdapter(mockRequest);
    }

    @Test
    public void testGetLimit() {
        when(mockRequest.getLimit()).thenReturn(TEST_INT);
        Integer actual = adapter.getLimit();
        assertEquals(TEST_INT, actual);
    }

    @Test
    public void testSetLimit() {
        adapter.setLimit(TEST_INT);
        verify(mockRequest, times(1)).setLimit(TEST_INT);
    }

    @Test
    public void testWithLimit() {
        Object actual = adapter.withLimit(TEST_INT);
        assertEquals(adapter, actual);
    }

    @Test
    public void testGetShardIterator() {
        when(mockRequest.getShardIterator()).thenReturn(TEST_STRING);
        String actual = adapter.getShardIterator();
        assertEquals(TEST_STRING, actual);
    }

    @Test
    public void testSetShardIterator() {
        adapter.setShardIterator(TEST_STRING);
        verify(mockRequest, times(1)).setShardIterator(TEST_STRING);
    }

    @Test
    public void testWithShardIterator() {
        Object actual = adapter.withShardIterator(TEST_STRING);
        assertEquals(adapter, actual);
    }

    @Test
    public void testRealData() {
        GetRecordsRequest request = createRequest();
        GetRecordsRequestAdapter requestAdapter = new GetRecordsRequestAdapter(request);
        assertEquals(request.getShardIterator(), requestAdapter.getShardIterator());
        assertEquals(request.getLimit(), requestAdapter.getLimit());
    }

    private GetRecordsRequest createRequest() {
        return new GetRecordsRequest().withLimit(TEST_INT).withShardIterator(TEST_STRING);
    }

}
