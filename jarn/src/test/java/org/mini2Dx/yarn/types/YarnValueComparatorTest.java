/**
 * MIT License
 *
 * Copyright (c) 2017 Thomas Cashman
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.mini2Dx.yarn.types;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

/**
 *
 */
public class YarnValueComparatorTest {
	private final Mockery mockery = new Mockery();
	
	private YarnValue value1, value2;
	
	@Before
	public void setUp() {
		value1 = mockery.mock(YarnValue.class, "value1");
		value2 = mockery.mock(YarnValue.class, "value2");
		
		mockery.checking(new Expectations() {
			{
				oneOf(value1).compareTo(value2);
				will(returnValue(0));
				oneOf(value1).compareTo(value2);
				will(returnValue(1));
				oneOf(value1).compareTo(value2);
				will(returnValue(-1));
			}
		});
	}
	
	@After
	public void teardown() {
		mockery.assertIsSatisfied();
	}

	@Test
	public void testEquals() {
		Assert.assertEquals(true, YarnValueComparator.equals(value1, value2));
		Assert.assertEquals(false, YarnValueComparator.equals(value1, value2));
		Assert.assertEquals(false, YarnValueComparator.equals(value1, value2));
	}
	
	@Test
	public void testNotEquals() {
		Assert.assertEquals(false, YarnValueComparator.notEquals(value1, value2));
		Assert.assertEquals(true, YarnValueComparator.notEquals(value1, value2));
		Assert.assertEquals(true, YarnValueComparator.notEquals(value1, value2));
	}
	
	@Test
	public void testLessThan() {
		Assert.assertEquals(false, YarnValueComparator.lessThan(value1, value2));
		Assert.assertEquals(false, YarnValueComparator.lessThan(value1, value2));
		Assert.assertEquals(true, YarnValueComparator.lessThan(value1, value2));
	}
	
	@Test
	public void testLessThanOrEqualTo() {
		Assert.assertEquals(true, YarnValueComparator.lessThanOrEqualTo(value1, value2));
		Assert.assertEquals(false, YarnValueComparator.lessThanOrEqualTo(value1, value2));
		Assert.assertEquals(true, YarnValueComparator.lessThanOrEqualTo(value1, value2));
	}
	
	@Test
	public void testGreaterThan() {
		Assert.assertEquals(false, YarnValueComparator.greaterThan(value1, value2));
		Assert.assertEquals(true, YarnValueComparator.greaterThan(value1, value2));
		Assert.assertEquals(false, YarnValueComparator.greaterThan(value1, value2));
	}
	
	@Test
	public void testGreaterThanOrEqualTo() {
		Assert.assertEquals(true, YarnValueComparator.greaterThanOrEqualTo(value1, value2));
		Assert.assertEquals(true, YarnValueComparator.greaterThanOrEqualTo(value1, value2));
		Assert.assertEquals(false, YarnValueComparator.greaterThanOrEqualTo(value1, value2));
	}
}
