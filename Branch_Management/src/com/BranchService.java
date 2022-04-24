package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Branch;

@Path("/Branch")
public class BranchService {
	Branch BranchObj = new Branch();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBranch() {
		return BranchObj.readBranch();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBranch(
	@FormParam("BranchName") String BranchName,			
	 @FormParam("Category") String Category,
	 @FormParam("Location") String Location,
	 @FormParam("PowerCapacity") String PowerCapacity,
	 @FormParam("HeadEngineer") String HeadEngineer)
	{
	 String output = BranchObj.insertBranch(BranchName, Category, Location, PowerCapacity, HeadEngineer);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBranch(String branchData)
	{
	//Convert the input string to a JSON object
	 JsonObject branchObject = new JsonParser().parse(branchData).getAsJsonObject();
	//Read the values from the JSON object
	 String BranchID = branchObject.get("BranchID").getAsString();
	 String BranchName = branchObject.get("BranchName").getAsString();
	 String Category = branchObject.get("Category").getAsString();
	 String Location = branchObject.get("Location").getAsString();
	 String PowerCapacity = branchObject.get("PowerCapacity").getAsString();
	 String HeadEngineer = branchObject.get("HeadEngineer").getAsString();
	 String output = BranchObj.updateBranch(BranchID, BranchName, Category, Location, PowerCapacity, HeadEngineer);
	return output;
	} 
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBranch(String branchData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(branchData, "", Parser.xmlParser());

	 
	//Read the value from the element 
	 String BranchID = doc.select("BranchID").text();
	 String output = BranchObj.deleteBranch(BranchID);
	return output;
	}
	
}
