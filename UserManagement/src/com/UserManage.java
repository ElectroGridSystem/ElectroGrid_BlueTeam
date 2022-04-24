package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.User;

@Path("/User")
public class UserManage {
	User UserObj = new User();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser() {
		return UserObj.readUser();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(
	@FormParam("Name") String Name,			
	 @FormParam("ACC_Num") String ACC_Num,
	 @FormParam("Email") String Email,
	 @FormParam("Password") String Password,
	 @FormParam("Phone") String Phone)
	{
	 String output = UserObj.insertUser(Name, ACC_Num, Email, Password, Phone);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)
	{
	//Convert the input string to a JSON object
	 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
	//Read the values from the JSON object
	 String UID = userObject.get("UID").getAsString();
	 String Name = userObject.get("Name").getAsString();
	 String ACC_Num = userObject.get("ACC_Num").getAsString();
	 String Email = userObject.get("Email").getAsString();
	 String Password = userObject.get("Password").getAsString();
	 String Phone = userObject.get("Phone").getAsString();
	 String output = UserObj.updateUser(UID, Name, ACC_Num, Email, Password, Phone);
	return output;
	} 
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

	//Read the value from the element 
	 String UID = doc.select("UID").text();
	 String output = UserObj.deleteUser(UID);
	return output;
	}
	
}
