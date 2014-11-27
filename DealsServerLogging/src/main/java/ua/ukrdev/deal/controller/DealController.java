package ua.ukrdev.deal.controller;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.ukrdev.deal.form.Deal;
import ua.ukrdev.deal.service.DealService;
import ua.ukrdev.deal.util.Net;
import ua.ukrdev.deal.util.Serv;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@Controller
public class DealController {
    final static Logger logger = Logger.getLogger(DealController.class);
    ResourceBundle resource = ResourceBundle.getBundle("urls");
    String url1 = resource.getString("url1");
    String url2 = resource.getString("url2");
    String system_idValue = "web";

    @Autowired
    private DealService dealService;
    Serv serv = new Serv();







    @RequestMapping("/testreq")
    public String testreq(@RequestParam Map<String, String> requestParams) throws IOException {
        Map data = new HashMap();
        //data.put("MasterDealerID", "33");
        data.put("MasterDealerID", 1);
        data.put("DealerID", "1029392020");
        data.put("AgentID", "12345");
        data.put("AgentName", "Eugene Salmin");
        data.put("MSISDN", "306975339511");
        data.put("Amount", "12312");
        data.put("Currency", "EUR");
        Net net = new Net();
        String resp = net.send("POST", "http", "127.0.0.1", 8080, "/topup", data);


        return "OK";
    }



    @RequestMapping(value = {"/topup", "Server/topup"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> topup(@RequestParam Map<String, String> requestParams) throws IOException {
        String agentName, currency, MSISDN;
        Integer masterDealerID, dealerID, agentID, amount;
        try {
             agentName = requestParams.get("AgentName");
             currency = requestParams.get("Currency");
             masterDealerID = Integer.parseInt(requestParams.get("MasterDealerID"));
             dealerID = Integer.parseInt(requestParams.get("DealerID"));
             agentID = Integer.parseInt(requestParams.get("AgentID"));
             MSISDN = requestParams.get("MSISDN");
             amount = Integer.parseInt(requestParams.get("Amount"));
        } catch (Exception ex) {
            return new ResponseEntity<String>("Params missing or incorrect",HttpStatus.BAD_REQUEST);
        }

        if (agentName==null && currency==null && masterDealerID==null && dealerID==null && agentID==null && MSISDN==null && amount==null) return new ResponseEntity<String>("Params missing or incorrect",HttpStatus.BAD_REQUEST);

        Deal dealToSave = new Deal();
        dealToSave.setMasterDealerID(masterDealerID);
        dealToSave.setDealerID(dealerID);
        dealToSave.setAgentID(agentID);
        dealToSave.setAgentName(agentName);
        dealToSave.setMSISDN(Double.parseDouble(MSISDN));
        dealToSave.setAmount(amount);
        dealToSave.setSystem_id(system_idValue);
        Date date = new java.util.Date();
        dealToSave.setDay(date);
        dealToSave.setDtime(date);



        Net n1=new Net();
        String resp1=null;
        try {
            String params1 = "MSISDN="+MSISDN+"&System_id="+system_idValue;
            resp1 =n1.send("POST", url1, params1);
            if (resp1!=null) logger.info("Respnse 1 received");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }




        JSONParser parser = new JSONParser();
        JSONObject jsonResponse = null;
        try {
            jsonResponse = (JSONObject) parser.parse(resp1);
            String status = jsonResponse.get("Status").toString();
            dealToSave.setErr_code(Integer.parseInt(status));
            dealToSave.setErr_description(serv.getErrDescr(Integer.parseInt(status)));
            String subscriberState = jsonResponse.get("subscriberState").toString();
            String tariffPlan = jsonResponse.get("tariffPlan").toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Net n2=new Net();
        String resp2=null;
        try {
           String params2 = "MSISDN="+MSISDN+"&System_id="+system_idValue+"&Currency="+currency+"&Amount="+amount;
            resp2 =n2.send("POST", url2, params2);
            if (resp2!=null) logger.info("Respnse 2 received");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        try {
            JSONParser parser1 = new JSONParser();
            JSONObject jsonResponse1 = (JSONObject) parser1.parse(resp2);
            String tranState = jsonResponse1.get("tranState").toString();
            dealToSave.setTranstate(tranState);
        } catch (ParseException e) {
            e.printStackTrace();
        }





        if (dealService.addDeal(dealToSave)!=null) {
            String logRec = "|MasterDealerID: "+masterDealerID+" |DealerID: " + dealerID+" |AgentID: "+agentID+" |AgentName: "+agentName+" |MSISDN: "+MSISDN+"|Amount: "+amount+" |Currency: "+currency+" |tansTate: "+dealToSave.getTranstate()+" |err_code: "+dealToSave.getErr_code()+" |"+date+System.getProperty("line.separator");
            serv.writeToLog(logRec);
            logger.info(logRec);

            return new ResponseEntity<String>("Request received", HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }


//    @RequestMapping(value = {"/test1", "/Server/test1"})
//    @ResponseBody
//    public ResponseEntity<String> test1(@RequestParam Map<String, String> requestParams) throws IOException {
//        JSONObject respObj=new JSONObject();
//        respObj.put("Status", 0);
//        respObj.put("subscriberState", "ACTIVE");
//        respObj.put("tariffPlan", "VeryExpensive");
//        return new ResponseEntity<String>(respObj.toString(), HttpStatus.OK);
//    }
//
//
//    @RequestMapping(value = {"/test2", "/Server/test2"})
//    @ResponseBody
//    public ResponseEntity<String> test2(@RequestParam Map<String, String> requestParams) throws IOException {
//        JSONObject respObj=new JSONObject();
//        respObj.put("tranState", "SUCCESS");
//        return new ResponseEntity<String>(respObj.toString(), HttpStatus.OK);
//    }
//
//
//    @RequestMapping(value = {"/urltest", "/Server/urltest"})
//    @ResponseBody
//    public ResponseEntity<String> urltest(@RequestParam Map<String, String> requestParams) throws IOException {
//        JSONObject respObj=new JSONObject();
//        respObj.put("url1", url1);
//        respObj.put("url2", url2);
//
//
//        return new ResponseEntity<String>(respObj.toString(), HttpStatus.OK);
//    }



}
