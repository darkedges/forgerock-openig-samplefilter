// Map to hold the JSON response
def headersMap = [
    headers: [:]
]
// Loop through all the headers of the request and add it to the HeadersMap.headers map
request.headers.asMapOfHeaders().each {
   headersMap.headers.put(it.key,it.value.values)
}
// Create an OK Response
response = new Response(Status.OK)
// Set the body of the response to be the JSON headersMap
response.entity = headersMap
// Set the response header 'content-type' to 'application/json'
response.headers.add("Content-Type","application/json")
//return the response
return response