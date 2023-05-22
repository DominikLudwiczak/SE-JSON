package pl.put.poznan.json.rest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.json.logic.*;

import java.util.Arrays;
import java.util.Set;


@RestController
public class JSONController {

    private static final Logger logger = LoggerFactory.getLogger(JSONController.class);

    private JSONProcessor jsonFile = new DefaultJSONProcessor();

    @RequestMapping(method = RequestMethod.POST, path="/minify", produces = "application/string")
    public String minify(@RequestBody String jsonContent) {
        logger.debug(jsonContent);

        JSONProcessor jsonProcessor = new JSONMinifier(this.jsonFile);
        return jsonProcessor.processJSON(jsonContent);
    }

    @RequestMapping(method = RequestMethod.POST, path="/unminify", produces = "application/string")
    public String unminify(@RequestBody String jsonContent) {
        logger.debug(jsonContent);

        JSONProcessor jsonProcessor = new JSONUnminifier(this.jsonFile);
        return jsonProcessor.processJSON(jsonContent);
    }

    @RequestMapping(method = RequestMethod.POST, path="/filter/exclude", produces = "application/string")
    public String filter_exclude(@RequestBody ObjectNode body) {
        var fields = body.fields();
        String jsonContent = fields.next().getValue().toString();
        String[] keys = fields.next().getValue().textValue().split(",");

        logger.debug(jsonContent);
        logger.debug(Arrays.toString(keys));

        JSONProcessor jsonProcessor = new JSONFilter(this.jsonFile);
        return jsonProcessor.processJSON(jsonContent, Set.of(keys));
    }

    @RequestMapping(method = RequestMethod.POST, path="/filter/select", produces = "application/string")
    public String filter_select(@RequestBody ObjectNode body) {
        var fields = body.fields();
        String jsonContent = fields.next().getValue().toString();
        String[] keys = fields.next().getValue().textValue().split(",");

        logger.debug(jsonContent);
        logger.debug(Arrays.toString(keys));

        JSONProcessor jsonProcessor = new JSONSelectFilter(this.jsonFile);
        return jsonProcessor.processJSON(jsonContent, Set.of(keys));
    }

    @RequestMapping(method = RequestMethod.POST, path="/compare", produces = "application/string")
    public String compare(@RequestBody ObjectNode body) {
        var fields = body.fields();
        String jsonContent = fields.next().getValue().toString();
        String jsonToCompare = fields.next().getValue().toString();

        logger.debug(jsonContent);
        logger.debug(jsonToCompare);

        JSONProcessor jsonProcessor = new JSONComparator(this.jsonFile);
        return jsonProcessor.processJSON(jsonContent, jsonToCompare);
    }
}


