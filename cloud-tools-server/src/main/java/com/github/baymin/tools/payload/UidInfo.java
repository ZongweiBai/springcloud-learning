package com.github.baymin.tools.payload;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zongwei
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UidInfo {

    @JsonProperty("UID")
    private Long uid;

    private Map<String, Object> extension;

    @JsonAnyGetter
    public Map<String, Object> getExtension() {
        return this.extension;
    }

    @JsonAnySetter
    public void setExtension(String name, Object value) {
        if (this.extension == null) {
            this.extension = new HashMap();
        }

        this.extension.put(name, value);
    }


}
