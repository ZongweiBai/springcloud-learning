package com.github.baymin.idgenerator.endpoint;

import com.baidu.fsg.uid.UidGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.baymin.idgenerator.payload.UidInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zongwei
 */
@Slf4j
@RequestMapping("uid")
@RestController
public class IdGeneratorEndpoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UidGenerator uidGenerator;

    @GetMapping
    public UidInfo getUser() throws JsonProcessingException {
        long uid = uidGenerator.getUID();
        String result = uidGenerator.parseUID(uid);
        return objectMapper.readValue(result, UidInfo.class);
    }

}
