package com.github.shinyashikis.rest;

import static org.elasticsearch.rest.RestRequest.Method.GET;
import static org.elasticsearch.rest.RestRequest.Method.PUT;
import static org.elasticsearch.rest.RestRequest.Method.DELETE;
import static org.elasticsearch.rest.RestStatus.OK;

import java.io.IOException;
import java.util.Date;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.BytesRestResponse;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestRequest;

public class RestUserdictAction extends BaseRestHandler {
	// TODO
	private static final String USER_DICT_DIR = "/usr/share/elasticsearch/userdict/";
	
    public RestUserdictAction(final Settings settings,
            final RestController controller) {
        super(settings);

        controller.registerHandler(GET, "/_userdict", this);
        controller.registerHandler(PUT, "/{index}/_userdict", this);
        controller.registerHandler(DELETE, "/{index}/_userdict", this);
    }

    @Override
    public String getName() {
        return "userdict_get";
    }

    @Override
    protected RestChannelConsumer prepareRequest(final RestRequest request,
            final NodeClient client) throws IOException {
    	final String index = request.param("index");
    	
    	return channel -> {
	    	final XContentBuilder builder = JsonXContent.contentBuilder();
	    	builder.startObject();
	    	builder.field("index", index);
	    	
	    	String dictFilePath = USER_DICT_DIR + String.format("userdict_%s_ja.txt", index);
	    	
	    	if (request.getHttpRequest().method().name().contentEquals("PUT")) {
	    		createUserDicionary(dictFilePath);
	    	} else if (request.getHttpRequest().method().name().contentEquals("DELETE")) {
	    		deleteUserDicionary(dictFilePath);
	    	} else {
				builder.field("description", "This is a elasticsearch-userdict response: " + new Date().toString());
	    	}
	    	
    		builder.endObject();
    		channel.sendResponse(new BytesRestResponse(OK, builder));
        };
    }

    private void createUserDicionary(String dictFilePath) throws IOException {
    	// TODO
    }

    private void deleteUserDicionary(String dictFilePath) throws IOException {
    	// TODO
    }

}
