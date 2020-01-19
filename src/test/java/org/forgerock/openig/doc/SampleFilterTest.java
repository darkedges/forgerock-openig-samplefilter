package org.forgerock.openig.doc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.forgerock.http.protocol.Status.FOUND;
import static org.forgerock.json.JsonValue.field;
import static org.forgerock.json.JsonValue.json;
import static org.forgerock.json.JsonValue.object;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.net.URI;
import java.util.Collections;

import org.forgerock.http.Handler;
import org.forgerock.http.protocol.Request;
import org.forgerock.http.protocol.Response;
import org.forgerock.http.routing.UriRouterContext;
import org.forgerock.json.JsonValue;
import org.forgerock.json.JsonValueException;
import org.forgerock.openig.heap.HeapException;
import org.forgerock.openig.heap.HeapImpl;
import org.forgerock.openig.heap.Name;
import org.forgerock.services.context.RootContext;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class SampleFilterTest {
	private static final String RESOURCE_URI = "http://example.com/index.html";
	private static final String MYHEADERNAME = "myHeaderName";
	private static final String MYHEADERVALUE = "myHeaderValue";
	private static final String MYSAMPLEFILTER = "mySampleFilter";
	private static final String MYHEAPNAME = "myHeap";
	private Request request;
	private UriRouterContext context;
	@Mock
	private Handler next;

	@Captor
	private ArgumentCaptor<Request> captor;

	@BeforeMethod
	public void setUp() throws Exception {
		initMocks(this);
		request = new Request();
		request.setMethod("GET").setUri(RESOURCE_URI);
		context = new UriRouterContext(new RootContext(), "", "", Collections.<String, String>emptyMap(),
				new URI(RESOURCE_URI));
	}

	/**
	 * Create an array of invalid configuration JSON Objects to test all the
	 * possible outcomes of invalid configurations
	 * 
	 * @return array of invalid configuration JSON objects
	 */
	@DataProvider
	private static Object[][] invalidConfigurations() {
		return new Object[][] {
				/* Missing name and value. */
				{ json(object()) },
				/* Missing name. */
				{ json(object(field("name", MYHEADERNAME))) },
				/* Missing value. */
				{ json(object(field("value", MYHEADERVALUE))) } };
	}

	/**
	 * Create an array of valid configuration JSON Objects to test all the possible
	 * outcomes of valid configurations
	 * 
	 * @return array of valid configuration JSON objects
	 */
	@DataProvider
	private static Object[][] validConfigurations() {
		return new Object[][] { { json(object(field("name", MYHEADERNAME), field("value", MYHEADERVALUE))) } };
	}

	/**
	 * Confirm that JsonValueException and / or HeapException are thrown when using
	 * the invalid JSON Configurations
	 * 
	 * @param invalidConfiguration Configuration Object being tested
	 * @throws Exception
	 */
	@Test(dataProvider = "invalidConfigurations", expectedExceptions = { JsonValueException.class,
			HeapException.class })
	public void shouldFailToCreateHeaplet(final JsonValue invalidConfiguration) throws Exception {
		final SampleFilter.Heaplet heaplet = new SampleFilter.Heaplet();
		heaplet.create(Name.of(MYSAMPLEFILTER), invalidConfiguration, buildDefaultHeap());
	}

	/**
	 * Confirm that no exception is thrown when using valid JSON Configurations
	 * 
	 * @param validConfiguration Configuration Object being tested
	 * @throws Exception
	 */

	@Test(dataProvider = "validConfigurations")
	public void shouldSucceedToCreateHeaplet(final JsonValue validConfiguration) throws Exception {
		final SampleFilter.Heaplet heaplet = new SampleFilter.Heaplet();
		heaplet.create(Name.of(MYSAMPLEFILTER), validConfiguration, buildDefaultHeap());
	}

	/**
	 * Need to test that filter is working as designed. First we check that the
	 * request object has the additional header added Next we check that the
	 * response contains the additional header
	 * 
	 * @param validConfiguration validConfiguration Configuration Object being
	 *                           tested
	 * @throws Exception
	 */
	@Test(dataProvider = "validConfigurations")
	public void shouldSucceed(final JsonValue validConfiguration) throws Exception {
		// Given
		// Create the SampleFilter Heaplet
		final SampleFilter.Heaplet heaplet = new SampleFilter.Heaplet();
		// create the filter from the heaplet create method with the valid
		// coinfiguration options
		SampleFilter filter = (SampleFilter) heaplet.create(Name.of(MYSAMPLEFILTER), validConfiguration,
				buildDefaultHeap());
		// This returns a default respomnse when the Filter is called
		when(next.handle(eq(context), any(Request.class))).thenReturn(Response.newResponsePromise(new Response(FOUND)));

		// When
		// Execute the filter request as it would be done in the HEAP
		final Response filteredResponse = filter.filter(context, request, next).get();

		// Then
		// This ensures that we can capture the result of the Request object inside the
		// filter.
		// We need to do this to ensure it has added the correct filter detaails
		verify(next).handle(eq(context), captor.capture());
		// Verify request
		// Confirm that we have 1 header and it is the one using the details in the
		// configuration
		assertThat(captor.getValue().getHeaders().size()).isEqualTo(1);
		assertThat(captor.getValue().getHeaders().get(MYHEADERNAME).getFirstValue()).isEqualTo(MYHEADERVALUE);
		// Verify response
		// Confirm that we have a reponse and that it contains 1 header and it is the
		// one using the details in the configuration
		assertThat(filteredResponse.getStatus()).isEqualTo(FOUND);
		assertThat(filteredResponse.getHeaders().size()).isEqualTo(1);
		assertThat(filteredResponse.getHeaders().get(MYHEADERNAME).getFirstValue()).isEqualTo(MYHEADERVALUE);
	}

	/**
	 * Build a default heap
	 * 
	 * @return HeapImpl
	 */
	private static HeapImpl buildDefaultHeap() {
		return new HeapImpl(Name.of(MYHEAPNAME));
	}
}
