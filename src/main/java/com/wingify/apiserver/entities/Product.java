
package com.wingify.apiserver.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "productid","images","title", "description", "productDetails", "groupDetails", "dimensions", "weight",
		"quantity", "pricing", "otherDetails" })
@Document(collection = "product")
@CompoundIndexes({ @CompoundIndex(background = true, name = "title", def = "{'title' : 1}"),
		@CompoundIndex(background = true, name = "brand", def = "{'brand' : 1}"),
		@CompoundIndex(background = true, name = "brand_productType", def = "{'productDetails.brand' : 1,'productDetails.productType':1}"),
		@CompoundIndex(background = true, name = "group", def = "{'groupDetails.groupName' : 1}"),
		@CompoundIndex(background = true, name = "group_subGroup", def = "{'groupDetails.groupName' : 1,'groupDetails.subGroup':1}"),
		@CompoundIndex(background = true, name = "productType", def = "{'productDetails.productType':1}}")
})
public class Product {

	@Id
	@JsonProperty("productid")
	private String productid;
	@JsonProperty("images")
	private List<String> images;
	@JsonProperty("title")
	private String title;
	@JsonProperty("description")
	private String description;
	@JsonProperty("productDetails")
	private ProductDetails productDetails;
	@JsonProperty("groupDetails")
	private GroupDetails groupDetails;
	@JsonProperty("dimensions")
	private Dimensions dimensions;
	@JsonProperty("weight")
	private Long weight;
	@JsonProperty("quantity")
	private Long quantity;
	@JsonProperty("pricing")
	private Pricing pricing;
	@JsonProperty("otherDetails")
	private Map<String, Object> otherDetails;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("productid")
	public String getProductid() {
		return productid;
	}

	@JsonProperty("productid")
	public void setProductid(String productid) {
		this.productid = productid;
	}

	@JsonProperty("images")
	public List<String> getImages() {
		return images;
	}
	@JsonProperty("images")
	public void setImages(List<String> images) {
		this.images = images;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("productDetails")
	public ProductDetails getProductDetails() {
		return productDetails;
	}

	@JsonProperty("productDetails")
	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	@JsonProperty("groupDetails")
	public GroupDetails getGroupDetails() {
		return groupDetails;
	}

	@JsonProperty("groupDetails")
	public void setGroupDetails(GroupDetails groupDetails) {
		this.groupDetails = groupDetails;
	}

	@JsonProperty("dimensions")
	public Dimensions getDimensions() {
		return dimensions;
	}

	@JsonProperty("dimensions")
	public void setDimensions(Dimensions dimensions) {
		this.dimensions = dimensions;
	}

	@JsonProperty("weight")
	public Long getWeight() {
		return weight;
	}

	@JsonProperty("weight")
	public void setWeight(Long weight) {
		this.weight = weight;
	}

	@JsonProperty("quantity")
	public Long getQuantity() {
		return quantity;
	}

	@JsonProperty("quantity")
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@JsonProperty("pricing")
	public Pricing getPricing() {
		return pricing;
	}

	@JsonProperty("pricing")
	public void setPricing(Pricing pricing) {
		this.pricing = pricing;
	}

	@JsonProperty("otherDetails")
	public Map<String, Object> getOtherDetails() {
		return otherDetails;
	}

	@JsonProperty("otherDetails")
	public void setOtherDetails(Map<String, Object> otherDetails) {
		this.otherDetails = otherDetails;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
