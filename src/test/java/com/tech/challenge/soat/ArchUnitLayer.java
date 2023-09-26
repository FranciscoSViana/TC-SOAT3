package com.tech.challenge.soat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArchUnitLayer {
    private String name;

    private Set<String> layers;

    public String[] getLayersAsVarargs() {
        if (ObjectUtils.isEmpty(this.layers)) {
            return new String[0];
        }

        return this.layers.stream()
                .map(item -> ".." + this.name + "." + item + "..")
                .toArray(String[]::new);
    }
}
