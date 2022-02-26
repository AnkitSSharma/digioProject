package digio.R1.digio.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import digio.R1.digio.entities.RequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@RestController
@RequestMapping("/v2/client/document")
public class createSignRequestController {

    @Autowired
    RestTemplate restTemplate;

    private HttpHeaders createHttpHeaders(String user, String password)
    {
        String notEncoded = user + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(notEncoded.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic " + encodedAuth);
        return headers;
    }




    @GetMapping("/")
    public Object getDocumentApi(@RequestParam("document_id") String documentId, @RequestParam("client_id") String clientId, @RequestParam("client_secret") String clientSecret) throws JsonProcessingException {
        try{
            String url = "https://ext.digio.in:444/v2/client/document/"+documentId;
            HttpHeaders headers = createHttpHeaders(clientId,clientSecret);
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response;}
        catch(Exception e){
            return e.getMessage();
        }
    }


    @GetMapping("/download")
    public Object getDocumentApiPdf(@RequestParam("document_id") String documentId, @RequestParam("client_id") String clientId, @RequestParam("client_secret") String clientSecret) throws JsonProcessingException {
        try{String url = "https://ext.digio.in:444/v2/client/document/download?document_id="+documentId;
        HttpHeaders headers = createHttpHeaders(clientId,clientSecret);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity,String.class);}
        catch(Exception e){
            return e.getMessage();
        }


    }

    @PostMapping("/upload")
    public ResponseEntity<String> createSignRequest(@RequestBody RequestDto requestDto, @RequestParam("client_id") String clientId, @RequestParam("client_secret") String clientSecret){
        String url = "https://ext.digio.in:444/v2/client/document/upload";
        HttpHeaders headers = createHttpHeaders(clientId,clientSecret);
        HttpEntity<String> entity = new HttpEntity<String>(requestDto.toString(), headers);
        return restTemplate.exchange(url, HttpMethod.POST, entity,String.class);
    }




}
