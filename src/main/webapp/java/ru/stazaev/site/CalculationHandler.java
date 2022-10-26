package ru.stazaev.site;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.stazaev.site.controller.FileConverter;
import ru.stazaev.site.controller.FormatConverter;
import ru.stazaev.site.controller.ValueHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CalculationHandler {
    private final String BASE_PATH = "D:/10m.txt";
    private final String BASE_PATH_WRIGHT = "D:/folder/d";
    private FormatConverter formatConverter;
    private FileConverter fileConverter;
    private ValueHandler valueHandler;
    private List<Integer> list = new ArrayList<>();

    @Autowired
    public CalculationHandler(FormatConverter formatConverter, FileConverter fileConverter, ValueHandler valueHandler) {
        this.formatConverter = formatConverter;
        this.fileConverter = fileConverter;
        this.valueHandler = valueHandler;
    }

    @RequestMapping(value = "/go", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    public String post(@RequestBody String jsonString) {
        String path = formatConverter.getJsonValueByString(jsonString, "file_path");
        this.list = (fileConverter.readFromFile(path));
        if (formatConverter.isContains("operation")) {
            String operation = formatConverter.getJsonValueByString(jsonString, "operation");
            switch (operation) {
                case "get_max_value":
                    fileConverter.writeToFile(BASE_PATH_WRIGHT, formatConverter.convertToJson(valueHandler.getMax(list), operation));
                    return formatConverter.convertToJson(BASE_PATH_WRIGHT + ".json", operation).toString();
                case "get_min_value":
                    fileConverter.writeToFile(BASE_PATH_WRIGHT, formatConverter.convertToJson(valueHandler.getMin(list), operation));
                    return formatConverter.convertToJson(BASE_PATH_WRIGHT + ".json", operation).toString();
                case "get_median":
                    fileConverter.writeToFile(BASE_PATH_WRIGHT, formatConverter.convertToJson(valueHandler.getMedian(list), operation));
                    return formatConverter.convertToJson(BASE_PATH_WRIGHT + ".json", operation).toString();
                case "get_average":
                    fileConverter.writeToFile(BASE_PATH_WRIGHT, formatConverter.convertToJson(valueHandler.getAverage(list), operation));
                    return formatConverter.convertToJson(BASE_PATH_WRIGHT + ".json", operation).toString();
                case "get_increasing_numbers":
                    fileConverter.writeToFile(BASE_PATH_WRIGHT, formatConverter.convertListToJson(valueHandler.increasingNumbers(list), operation));
                    return formatConverter.convertToJson(BASE_PATH_WRIGHT + ".json", operation).toString();
                case "get_decreasing_numbers":
                    fileConverter.writeToFile(BASE_PATH_WRIGHT + "json", formatConverter.convertListToJson(valueHandler.decreasingNumbers(list), operation));
                    return formatConverter.convertToJson(BASE_PATH_WRIGHT + ".json", operation).toString();

            }
        }
        return list.toString();
    }

    @RequestMapping(value = "/get_max_value", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public String get_max_value(@RequestHeader Map<String, String> headers) {
        if (list.isEmpty()) {
            list = (fileConverter.readFromFile(BASE_PATH));
        }
        String name = "max_value";
        if (headers.get("accept-application").equals("application/xml")) {
            fileConverter.writeToFile(BASE_PATH_WRIGHT, formatConverter.convertValueToXML(name, valueHandler.getMax(list)));
            return formatConverter.convertToJson(BASE_PATH_WRIGHT + ".xml", name).toString();
        } else {
            fileConverter.writeToFile(BASE_PATH_WRIGHT, formatConverter.convertToJson(valueHandler.getMax(list), name));
            return formatConverter.convertToJson(BASE_PATH_WRIGHT + ".json", name).toString();

        }
    }


    @RequestMapping(value = "/get_min_value", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public String get_min_value(@RequestHeader Map<String, String> headers) {
        if (list.isEmpty()) {
            list = (fileConverter.readFromFile(BASE_PATH));
        }
        String name = "min_value";
        if (headers.get("accept-application").equals("application/xml")) {
            fileConverter.writeToFile(BASE_PATH_WRIGHT, formatConverter.convertValueToXML(name, valueHandler.getMin(list)));
            return formatConverter.convertToJson(BASE_PATH_WRIGHT + ".xml", name).toString();
        } else {
            fileConverter.writeToFile(BASE_PATH_WRIGHT, formatConverter.convertToJson(valueHandler.getMin(list), name));
            return formatConverter.convertToJson(BASE_PATH_WRIGHT + ".json", name).toString();

        }
    }

    @RequestMapping(value = "/get_median", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public String get_median(@RequestHeader Map<String, String> headers) {
        if (list.isEmpty()) {
            list = (fileConverter.readFromFile(BASE_PATH));
        }
        String name = "median";
        if (headers.get("accept-application").equals("application/xml")) {
            fileConverter.writeToFile(BASE_PATH_WRIGHT, formatConverter.convertValueToXML(name, valueHandler.getMedian(list)));
            return formatConverter.convertToJson(BASE_PATH_WRIGHT + ".xml", name).toString();
        } else {
            fileConverter.writeToFile(BASE_PATH_WRIGHT, formatConverter.convertToJson(valueHandler.getMedian(list), name));
            return formatConverter.convertToJson(BASE_PATH_WRIGHT + ".json", name).toString();

        }
    }

    @RequestMapping(value = "/get_average", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public String get_average(@RequestHeader Map<String, String> headers) {
        if (list.isEmpty()) {
            list = (fileConverter.readFromFile(BASE_PATH));
        }
        String name = "average";
        if (headers.get("accept-application").equals("application/xml")) {
            fileConverter.writeToFile(BASE_PATH_WRIGHT, formatConverter.convertValueToXML(name, valueHandler.getAverage(list)));
            return formatConverter.convertToJson(BASE_PATH_WRIGHT + ".xml", name).toString();
        } else {
            fileConverter.writeToFile(BASE_PATH_WRIGHT, formatConverter.convertToJson(valueHandler.getAverage(list), name));
            return formatConverter.convertToJson(BASE_PATH_WRIGHT + ".json", name).toString();

        }

    }


    @RequestMapping(value = "/get_increasing_numbers", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public String get_increasing_numb(@RequestHeader Map<String, String> headers) {
        if (list.isEmpty()) {
            list = (fileConverter.readFromFile(BASE_PATH));
        }
        String name = "increasing_numbers";
        if (headers.get("accept-application").equals("application/xml")) {
            fileConverter.writeToFile(BASE_PATH_WRIGHT, formatConverter.convertListToXML(name, valueHandler.increasingNumbers(list)));
            return formatConverter.convertToJson(BASE_PATH_WRIGHT + ".xml", name).toString();
        } else {
            fileConverter.writeToFile(BASE_PATH_WRIGHT, formatConverter.convertListToJson(valueHandler.increasingNumbers(list), name));
            return formatConverter.convertToJson(BASE_PATH_WRIGHT + ".json", name).toString();

        }

    }

    @RequestMapping(value = "/get_decreasing_numbers", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public String get_decreasing_numb(@RequestHeader Map<String, String> headers) {
        if (list.isEmpty()) {
            list = (fileConverter.readFromFile(BASE_PATH));
        }
        String name = "decreasing_numbers";
        if (headers.get("accept-application").equals("application/xml")) {
            fileConverter.writeToFile(BASE_PATH_WRIGHT + ".xml", formatConverter.convertListToXML(name, valueHandler.decreasingNumbers(list)));
            return formatConverter.convertToJson(BASE_PATH_WRIGHT + ".xml", name).toString();
        } else {
            fileConverter.writeToFile(BASE_PATH_WRIGHT + "json", formatConverter.convertListToJson(valueHandler.decreasingNumbers(list), name));
            return formatConverter.convertToJson(BASE_PATH_WRIGHT + ".json", name).toString();
        }
    }

}
