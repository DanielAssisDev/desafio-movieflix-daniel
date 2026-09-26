package com.devsuperior.movieflix.utils;

import com.devsuperior.movieflix.projections.IdProjection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static <ID> List<? extends IdProjection<ID>> replace(List<? extends IdProjection<ID>> ordered, List<? extends IdProjection<ID>> unordered) {

        Map<ID, IdProjection<ID>> map = new HashMap<>();
        for (IdProjection<ID> item : unordered) {
            map.put(item.getId(), item);
        }

        List<IdProjection<ID>> result = new ArrayList<>();
        for (IdProjection<ID> item : ordered) {
            result.add(map.get(item.getId()));
        }

        return result;
    }
}
