package com.petdoctor.outer.tool;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.petdoctor.outer.model.AppUser;
import com.petdoctor.outer.model.Role;
import lombok.experimental.UtilityClass;
import lombok.var;

import java.util.Objects;

@UtilityClass
public class JsonProcessor {

    private ObjectMapper objectMapper = new JsonMapper();

    public JsonNode parseToJsonNode(AppUser user) {

        try {
            ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
            String userJson = ow.writeValueAsString(user);
            return objectMapper.readTree(userJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public AppUser parseFromJsonNode(String username, JsonNode node) {

        return constructDtoFromJsonNode(username, node);
    }

    public JsonNode removeFieldFromJson(String fieldName, JsonNode node) {

        var iter = node.fields();

        while (iter.hasNext()) {

            var elem = iter.next();

            if (Objects.equals(elem.getKey(), fieldName)) {
                iter.remove();
                break;
            }
        }

        return node;
    }

    private AppUser constructDtoFromJsonNode(String username, JsonNode node) {

        return AppUser.builder()
                .username(username)
                .password(node.get("password").asText())
                .role(Role.valueOf(node.get("role").asText()))
                .build();
    }
}
