package org.example.Controller;

import com.google.gson.Gson;
import org.example.Models.*;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.example.Models.Response;
import org.example.Models.Tools;

public class Processor {
    private static Processor processor;

    public static Processor getInstance() {
        if (processor == null) {
            processor = new Processor();
        }
        return processor;
    }

    public Response process(Request request, Socket client) throws URISyntaxException, IOException {
        Response response = new Response();
        if (request.getAction() == RequestType.ReFresh) {
            response = Refresh(request);
        }
        else if (request.getAction() == RequestType.Play) {
            response = Play(request, client);
        }

        return response;
    }

    private Response Refresh(Request request) throws URISyntaxException {
        Response response = new Response();
        response.setStatus_code(200);
        response.setMessage(new Gson().toJson(getAllSongs()));
        return response;
    }

    private Response Play(Request request, Socket client) throws URISyntaxException, IOException {
        Response response = new Response();
        String name = (String) request.getParams().get("name");
        if (!getAllSongs().contains(name)) {
            response.setStatus_code(404);
            response.setMessage("not found!");
            return response;
        }
//        TODO: smt
        response.setStatus_code(200);
        return response;
    }

    ArrayList<String> getAllSongs() {
        File folder = null;
        try {
            folder = new File( getClass().getResource("/musics/").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        File[] files = folder.listFiles();

        ArrayList<String> res = new ArrayList<>();
        for (File file : files) {
            if (file.isFile()) {
                res.add(file.getName());
            }
        }
        return res;
    }
}
