package org.example.Controller;

import com.google.gson.Gson;
import org.example.Models.Response;

import java.util.ArrayList;
import java.util.Collections;

public class Processor {
    private static Processor processor;

    public static Processor getInstance() {
        if (processor == null) {
            processor = new Processor();
        }
        return processor;
    }

    public Response process(Models.Request request) {
        Response response = new Response();
//        if (request.getAction() == RequestType.Login) {
//            response = Login(request);
//        } else if (request.getAction() == RequestType.Register) {
//            response = Register(request);
//        } else if (request.getAction() == RequestType.ShowProfile) {
//            response = ShowProfile(request);
//        } else if (request.getAction() == RequestType.AllAdvertising) {
//            response = getAllAdvertising(request);
//        } else if (request.getAction() == RequestType.FavoriteAdvertising) {
//            response = getAllFavoritesAdvertising(request);
//        } else if (request.getAction() == RequestType.search) {
//            response = search(request);
//        } else if (request.getAction() == RequestType.addFavorite) {
//            response = addFavorite(request);
//        } else if (request.getAction() == RequestType.RemoveFavorite) {
//            response = RemoveFavorite(request);
//        } else if (request.getAction() == RequestType.AllAdvertisingInAscendingOrder) {
//            response = AllAdvertisingInAscendingPriceOrder(request);
//        } else if (request.getAction() == RequestType.AllAdvertisingInDeAscendingOrder) {
//            response = AllAdvertisingInDeAscendingPriceOrder(request);
//        } else if (request.getAction() == RequestType.NewAdvertising) {
//            response = AddNewAdvertising(request);
//        } else if (request.getAction() == RequestType.getAdvertise) {
//            response = getAdvertise(request);
//        } else if (request.getAction() == RequestType.AdvertisingInRange) {
//            response = getInRange(request);
//        } else if (request.getAction() == RequestType.ChangeUsername) {
//            response = changeUserName(request);
//        } else if (request.getAction() == RequestType.ChangePassword) {
//            response = changePassword(request);
//        } else if (request.getAction() == RequestType.ChangeProfilePhoto) {
//            response = changeProfilePhoto(request);
//        } else if (request.getAction() == RequestType.ChangeEmail) {
//            response = changeEmail(request);
//        } else if (request.getAction() == RequestType.ChangeFirstName) {
//            response = changeFirstName(request);
//        } else if (request.getAction() == RequestType.ChangeLastName) {
//            response = changeLastName(request);
//        }

        return response;
    }
}
