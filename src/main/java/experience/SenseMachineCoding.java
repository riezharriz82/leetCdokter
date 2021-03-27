package experience;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.RFC4180Parser;
import com.opencsv.RFC4180ParserBuilder;
import com.opencsv.exceptions.CsvValidationException;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SenseMachineCoding {
    String directory = "/Users/asishroy/Downloads/instacart_2017_05_01/";
    Map<Integer, String> departmentIdsToDepartmentName = new HashMap<>(); //department_id -> department
    Map<Integer, Integer> productsToDepartmentIds = new HashMap<>(); //product_id -> department_id
    Map<Integer, Pair<Integer, Integer>> orders = new HashMap<>(); //order_id -> {order_dow, order_hour_of_day}
    Map<Integer, Map<Integer, Map<Integer, Integer>>> frequencyMapping = new HashMap<>(); //dayOfWeek -> {hour -> {department_id -> frequency}}}
    Map<Integer, Map<Integer, Map<Integer, Set<Integer>>>> userMapping = new HashMap<>(); //dayOfWeek -> {hour -> {department_id -> userIds}}
    Map<Integer, Integer> orderToUserMapping = new HashMap<>(); //orderId -> userId

    public static void main(String[] args) throws IOException, CsvValidationException {
        //Maintain reverse index for required columns
        SenseMachineCoding solution = new SenseMachineCoding();
        solution.readDepartments();
        solution.readProducts();
        solution.readOrders();
        //init hashmaps
        for (int i = 0; i < 7; i++) {
            Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
            for (int j = 0; j < 24; j++) {
                map.put(j, new HashMap<>());
            }
            solution.frequencyMapping.put(i, map);
        }
        for (int i = 0; i < 7; i++) {
            Map<Integer, Map<Integer, Set<Integer>>> map = new HashMap<>();
            for (int j = 0; j < 24; j++) {
                map.put(j, new HashMap<>());
            }
            solution.userMapping.put(i, map);
        }
        solution.readOrderProducts();
        System.out.println("Done processing");
        //compute total frequencies for every hour of every day to quickly calculate percentages
        Map<Integer, Map<Integer, Integer>> totalFrequencies = new HashMap<>(); //dayOfWeek -> {hour -> totalFrequency}
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 24; j++) {
                Map<Integer, Integer> frequencies = solution.frequencyMapping.get(i).get(j);
                int total = 0;
                for (int value : frequencies.values()) {
                    total += value;
                }
                totalFrequencies.computeIfAbsent(i, __ -> new HashMap<>()).put(j, total);
            }
        }
        for (int i = 0; i < 7; i++) { //day
            for (int j = 0; j < 24; j++) { //hour
                Map<Integer, Integer> frequencies = solution.frequencyMapping.get(i).get(j);
                int finalI = i;
                int finalJ = j;
                int totalFrequency = totalFrequencies.get(i).get(j);
                frequencies.entrySet().stream()
                        .sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue())) //reverse sort based on frequencies
                        .forEach(k -> System.out.println(finalI + " " + finalJ + " " + solution.departmentIdsToDepartmentName.get(k.getKey()) + " "
                                + ((double) k.getValue() / totalFrequency) * 100.0));
            }
        }
    }

    private void readProducts() throws IOException, CsvValidationException {
        RFC4180Parser rfc4180Parser = new RFC4180ParserBuilder().build();
        try (CSVReader reader = new CSVReaderBuilder(
                new FileReader(directory + "products.csv"))
                .withCSVParser(rfc4180Parser)
                .withSkipLines(1)
                .build()) {
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                productsToDepartmentIds.put(Integer.parseInt(lineInArray[0]), Integer.parseInt(lineInArray[3]));
            }
        }
    }

    private void readOrders() throws IOException {
        Path path = Paths.get(directory + "orders.csv");
        BufferedReader reader = Files.newBufferedReader(path);
        boolean firstLine = true;
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            if (firstLine) {
                firstLine = false;
            } else {
                String[] splits = line.split(",");
                orders.put(Integer.parseInt(splits[0]), new Pair<>(Integer.parseInt(splits[4]), Integer.parseInt(splits[5])));
                orderToUserMapping.put(Integer.parseInt(splits[0]), Integer.parseInt(splits[1]));
            }
        }
    }

    private void readOrderProducts() throws IOException, CsvValidationException {
        RFC4180Parser rfc4180Parser = new RFC4180ParserBuilder().build();
        //this ensures correct parsing of files with special character like a,"this is a big string",10,20
        try (CSVReader reader = new CSVReaderBuilder(
                new FileReader(directory + "order_products__prior.csv"))
                .withCSVParser(rfc4180Parser)
                .withSkipLines(1)
                .build()) {
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                int productId = Integer.parseInt(lineInArray[1]);
                int departmentId = productsToDepartmentIds.get(productId);
                int orderId = Integer.parseInt(lineInArray[0]);
                Pair<Integer, Integer> timeInfo = orders.get(orderId);
                int dayOfWeek = timeInfo.getKey();
                int hourOfDay = timeInfo.getValue();
                int curUserId = orderToUserMapping.get(orderId);
                //dayOfWeek -> {hour -> {department_id -> frequency}}}
                Map<Integer, Integer> departmentFrequencies = frequencyMapping.get(dayOfWeek).get(hourOfDay);
                Set<Integer> userIds = userMapping.get(dayOfWeek).get(hourOfDay).computeIfAbsent(departmentId, __ -> new HashSet<>());
                if (!userIds.contains(curUserId)) {
                    //if user has not shopped in the current department during the current hour, then process it
                    departmentFrequencies.put(departmentId, departmentFrequencies.getOrDefault(departmentId, 0) + 1);
                    userIds.add(curUserId);
                }
            }
        }
    }

    /**
     * Instacart has open-sourced sample data about user purchases on their platform. Use
     * their dataset to generate a report that shows the most popular `Departments` purchased
     * from, segmented by hour for every day of the week.
     * <p>
     * Example report output:
     * Day 0 Hour 0 Frozen (20%)
     * Day 0 Hour 0 Bakery (15%)
     * Day 0 Hour 0 Milk (10%)
     * ...
     * ..
     * Day 1 Hour 0 Frozen (15%)
     * ...
     * Day 1 Hour 1 Vegetables (10%)
     * ...
     * <p>
     * Dataset: http://bit.ly/sense-code-test
     * Instacart’s data is a set of CSVs. You should inspect them and determine how each
     * table is related to each other in order to generate the report. Additionally, any
     * given user shouldn’t inflate the data for a given hour. Say “User A” buys from the
     * Frozen department multiple times in hour 10. That shouldn’t inflate the Frozen
     * department percentage for that hour.
     * <p>
     * Schema: https://gist.github.com/jeremystan/c3b39d947d9b88b3ccff3147dbcf6c6b
     * orders (3.4m rows, 206k users):
     * order_id: order identifier
     * user_id: customer identifier
     * eval_set: which evaluation set this order belongs in (see SET described below)
     * order_number: the order sequence number for this user (1 = first, n = nth)
     * order_dow: the day of the week the order was placed on
     * order_hour_of_day: the hour of the day the order was placed on
     * days_since_prior: days since the last order, capped at 30 (with NAs for order_number = 1)
     * <p>
     * products (50k rows):
     * product_id: product identifier
     * product_name: name of the product
     * aisle_id: foreign key
     * department_id: foreign key
     * <p>
     * departments (21 rows):
     * department_id: department identifier
     * department: the name of the department
     * <p>
     * order_products__SET (30m+ rows):
     * order_id: foreign key
     * product_id: foreign key
     * add_to_cart_order: order in which each product was added to cart
     * reordered: 1 if this product has been ordered by this user in the past, 0 otherwise
     * where SET is one of the four following evaluation sets (eval_set in orders):
     * <p>
     * "prior": orders prior to that users most recent order (~3.2m orders)
     * "train": training data supplied to participants (~131k orders)
     * "test": test data reserved for machine learning competitions (~75k orders)
     *
     * Verdict: No, could have been a yes with a better attitude. (2.5 / 4)
     * I think instead of showing humbleness, I showed a little cocky attitude which didn't sit right with the interviewer.
     * Also I missed the last part of problem statement which mentioned to not count the customer id's who have already bought from
     * the same department in the current hour. This also caused some deduction in points.
     */
    private void readDepartments() throws IOException {
        Path path = Paths.get(directory + "departments.csv");
        BufferedReader reader = Files.newBufferedReader(path);
        boolean firstLine = true;
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            if (firstLine) {
                firstLine = false;
            } else {
                String[] splits = line.split(",");
                departmentIdsToDepartmentName.put(Integer.parseInt(splits[0]), splits[1]);
            }
        }
    }
}
