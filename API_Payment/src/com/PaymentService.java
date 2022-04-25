package com;

import model.Payment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")
public class PaymentService {
	Payment PaymentObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return PaymentObj.readPayment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(
	@FormParam("payment_method") String payment_method,
	@FormParam("name_on_card") String name_on_card,
	@FormParam("card_number") String card_number,
	@FormParam("cvc") String cvc,
	@FormParam("exp") String exp) {
		String output = PaymentObj.insertPayment(payment_method, name_on_card, card_number,  cvc, exp);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePayment(String paymentData) { 
		// Convert the input string to a JSON object
		JsonObject paymentSObject = new JsonParser().parse(paymentData).getAsJsonObject();

		// Read the values from the JSON object
		String Payment_id = paymentSObject.get("Payment_id").getAsString();
		String payment_method = paymentSObject.get("payment_method").getAsString();
		String name_on_card = paymentSObject.get("name_on_card").getAsString();
		String card_number = paymentSObject.get("card_number").getAsString();
		String cvc = paymentSObject.get("cvc").getAsString();
		String exp = paymentSObject.get("exp").getAsString();
		
		String output = PaymentObj.updatePayment(Payment_id, payment_method, name_on_card, card_number, cvc, exp);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		// Read the value from the element
		String Payment_id = doc.select("Payment_id").text();
		String output = PaymentObj.deletePayment(Payment_id);
		return output;
	}
}
