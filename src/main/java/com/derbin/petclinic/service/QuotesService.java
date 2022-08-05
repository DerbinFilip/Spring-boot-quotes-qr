package com.derbin.petclinic.service;

import com.derbin.petclinic.model.Quotes;
import com.derbin.petclinic.repository.QuotesRepo;
import org.apache.commons.lang3.RandomUtils;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class QuotesService {
    private final QuotesRepo quotesRepo;

    public QuotesService(QuotesRepo quotesRepo) throws Exception {
        this.quotesRepo = quotesRepo;
        getQuotesFromJsonToMap();
    }

    public Quotes addQuotes(Quotes quotes) {
        return quotesRepo.save(quotes);
    }

    public void deleteQuotes(final Long id) {
        quotesRepo.deleteById(id);
    }

    public List<Quotes> getAllQuotes() {
        return quotesRepo.findAll();
    }

    public Quotes getQuotesById(final Long id) {
        return quotesRepo.findById(id).orElseThrow();
    }

    public Long randomQuotes() {
        return RandomUtils.nextLong(1L, getAllQuotes().size());
    }

    public void getQuotesFromJsonToMap() throws Exception {
        JSONArray myResponse = getQuotes();
        Map<Object, Object> map = new TreeMap<>();
        saveQuotes(myResponse, map);
    }

    private void saveQuotes(JSONArray myResponse, Map<Object, Object> map) {
        for (int i = 0; i < myResponse.length(); i++) {
            String author = String.valueOf(myResponse.getJSONObject(i).get("author"));
            String text = String.valueOf(myResponse.getJSONObject(i).get("text"));
            map.put(author, text);
        }
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Quotes quotes = new Quotes((String) entry.getValue(), (String) entry.getKey());
            addQuotes(quotes);
        }
    }

    private JSONArray getQuotes() throws IOException {
        String url = "https://type.fit/api/quotes";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return new JSONArray(response.toString());
    }
}
