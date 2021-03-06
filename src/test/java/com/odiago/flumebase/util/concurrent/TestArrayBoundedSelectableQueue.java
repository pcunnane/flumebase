/**
 * Licensed to Odiago, Inc. under one or more contributor license
 * agreements.  See the NOTICE.txt file distributed with this work for
 * additional information regarding copyright ownership.  Odiago, Inc.
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.odiago.flumebase.util.concurrent;

import org.testng.annotations.Test;

public class TestArrayBoundedSelectableQueue extends QueueTestCase {
  @Test
  public void TestSimple() {
    // 1 producer, 1 consumer, bigger buffer than we need.
    runTest(new ArrayBoundedSelectableQueue<Long>(500), 1, 1, 100);

    // 1 producer, 1 consumer, smaller buffer than we need.
    runTest(new ArrayBoundedSelectableQueue<Long>(20), 1, 1, 100);
  }

  @Test
  public void testFanOut() {
    // 1 producer, 2 consumers
    runTest(new ArrayBoundedSelectableQueue<Long>(100), 1, 2, 10000);

    // 1 producer, 4 consumers
    runTest(new ArrayBoundedSelectableQueue<Long>(1500), 1, 4, 25000);
  }

  @Test
  public void testFanIn() {
    // 2 producers, 1 consumer
    runTest(new ArrayBoundedSelectableQueue<Long>(100), 2, 1, 10000);

    // 1 producer, 4 consumers
    runTest(new ArrayBoundedSelectableQueue<Long>(1500), 4, 1, 25000);
  }

  @Test
  public void testMultiMulti() {
    // 2 producers, 2 consumers
    runTest(new ArrayBoundedSelectableQueue<Long>(100), 2, 2, 10000);

    // 3 producers, 3 consumers
    runTest(new ArrayBoundedSelectableQueue<Long>(1500), 3, 3, 25000);
  }
}
