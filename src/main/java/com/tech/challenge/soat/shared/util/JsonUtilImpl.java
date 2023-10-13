package com.tech.challenge.soat.shared.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JsonUtilImpl implements JsonUtil{
    @Override
    public String obterValorChaveJson(String json, String chave) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            Optional<JsonNode> value = findNode(rootNode, chave);
            return value.map(JsonNode::toString).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Optional<JsonNode> findNode(JsonNode node, String key) {
        if (node.isObject()) {
            if (node.has(key)) {
                return Optional.of(node.get(key));
            } else {
                for (JsonNode childNode : node) {
                    Optional<JsonNode> found = findNode(childNode, key);
                    if (found.isPresent()) {
                        return found;
                    }
                }
            }
        } else if (node.isArray()) {
            for (JsonNode childNode : node) {
                Optional<JsonNode> found = findNode(childNode, key);
                if (found.isPresent()) {
                    return found;
                }
            }
        }
        return Optional.empty();
    }
}
