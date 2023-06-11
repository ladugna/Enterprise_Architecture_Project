package client.client;

import client.contract.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import java.util.Base64;


@Component
public class client {

    @Autowired
    RestTemplate restTemplate;


    private String serverUrl = "http://localhost:9000";
    private String username = "username_test";
    private String password = "elom20ce";


    public void sendRequest() throws JsonProcessingException {

        // add new product
        for(int i = 1; i < 11 ; i++){
            addNewItem("Apples"+i, "Good Apples", i, 90.0+i, 10, "Pants");
        }

        // create user
        createCustomer();


        //fetch user token
        String token = fetchUserToken(username,password);


        // fetch customer card
        fetchCustomerCard(token);


        for(int i = 1; i <11 ; i++){
            if(i % 2 == 0){
                addElementsToCart(token, i,i);
            }
        }

        fetchCustomerCard(token);

        // process user checkout
        checkout(token);

    }

    public void addNewItem(String name, String description, int barcodeNumber, double price, int quantityInStock, String category){
        restTemplate.postForLocation(serverUrl+"/items", new CreateItemRequest(
                name,
                description,
                barcodeNumber,
                price,
                quantityInStock,
                category
        ));
    }

    public void addElementsToCart(String token, int barcodeNumber, int qty){
        String url = serverUrl+"/lineItem/add";

        // Use the access token for authentication
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        AddItemToCartRequest  addItemToCartRequest =new AddItemToCartRequest(barcodeNumber, qty);
        HttpEntity<AddItemToCartRequest> requestEntity = new HttpEntity<>(addItemToCartRequest, headers);
        restTemplate.postForLocation(url, requestEntity);

    }

    public void checkout(String token){
        String url = serverUrl+"/order/checkout";
        // Use the access token for authentication
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    }

    public void createCustomer(){
                restTemplate.postForLocation(serverUrl+"/customers", new UserCreationRequest(
                "elom",
                "emmmanuel",
                password,
                username,
                "emialfortest@gmail"
        ));
    }

    public String fetchUserToken(String username, String password){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String auth = "client" + ":" + "server";
        String encodedCredentials = new String(Base64.getEncoder().encode(auth.getBytes()));
        headers.add("Authorization", "Basic " + encodedCredentials);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>() {};

        body.add("grant_type", "password");
        body.add("scope", "webclient");
        body.add("username", username);
        body.add("password", password);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<UserTokenResponse> responseEntity = restTemplate.exchange(
                serverUrl+"/oauth/token",
                HttpMethod.POST,
                requestEntity,
                UserTokenResponse.class
        );


        UserTokenResponse accessTokenResponse = responseEntity.getBody();
        return accessTokenResponse.getAccess_token();
    }

    public void fetchCustomerCard(String token) throws JsonProcessingException {

        String url = serverUrl+"/cart";

        // Use the access token for authentication
        HttpHeaders headers1 = new HttpHeaders();
        headers1.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers1);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        ShoppingCartResponse userCart = objectMapper.readValue(response.getBody(), ShoppingCartResponse.class);
        System.out.println(userCart);
    }

}
