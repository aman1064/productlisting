
package com.wingify.apiserver.entities;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "brand",
    "modelNumber",
    "productType",
    "releaseDate",
    "lotNumber"
})
public class ProductDetails {

    @JsonProperty("brand")
    private String brand;
    @JsonProperty("productType")
    private String productType;
    @JsonProperty("modelNumber")
    private String modelNumber;
    @JsonProperty("releaseDate")
    private Long releaseDate;
    @JsonProperty("lotNumber")
    private String lotNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("brand")
    public String getBrand() {
        return brand;
    }

    @JsonProperty("brand")
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @JsonProperty("productType")
    public String getProductType() {
		return productType;
	}
    @JsonProperty("productType")
	public void setProductType(String productType) {
		this.productType = productType;
	}

	@JsonProperty("modelNumber")
    public String getModelNumber() {
        return modelNumber;
    }

    @JsonProperty("modelNumber")
    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    @JsonProperty("releaseDate")
    public Long getReleaseDate() {
        return releaseDate;
    }

    @JsonProperty("releaseDate")
    public void setReleaseDate(Long releaseDate) {
        this.releaseDate = releaseDate;
    }

    @JsonProperty("lotNumber")
    public String getLotNumber() {
        return lotNumber;
    }

    @JsonProperty("lotNumber")
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
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
