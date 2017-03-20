
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
    "mrp",
    "sellingprice"
})
public class Pricing {

    @JsonProperty("mrp")
    private Double mrp;
    @JsonProperty("sellingprice")
    private Double sellingprice;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("mrp")
    public Double getMrp() {
        return mrp;
    }

    @JsonProperty("mrp")
    public void setMrp(Double mrp) {
        this.mrp = mrp;
    }

    @JsonProperty("sellingprice")
    public Double getSellingprice() {
        return sellingprice;
    }

    @JsonProperty("sellingprice")
    public void setSellingprice(Double sellingprice) {
        this.sellingprice = sellingprice;
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
