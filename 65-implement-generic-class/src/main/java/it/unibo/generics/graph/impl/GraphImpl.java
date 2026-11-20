package it.unibo.generics.graph.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {

    private Map<N, Set<N>> graph;

    public GraphImpl() {
        this.graph = new HashMap<>();
    }

    @Override
    public void addNode(N node) {
        if (!Objects.isNull(node)) {
            this.graph.putIfAbsent(node, new HashSet<>());
        }
    }

    @Override
    public void addEdge(N source, N target) {
        if (!Objects.isNull(source) && !Objects.isNull(target) && this.graph.containsKey(source)
                && this.graph.containsKey(target)) {
            this.graph.get(source).add(target);
        }
    }

    @Override
    public Set<N> nodeSet() {
        return new HashSet<>(this.graph.keySet());
    }

    @Override
    public Set<N> linkedNodes(N node) {
        if (!Objects.isNull(node) && this.graph.containsKey(node)) {
            return new HashSet<>(this.graph.get(node));
        }
        return new HashSet<>();
    }

    @Override
    public List<N> getPath(N source, N target) {
        boolean isContained = false;
        if (Objects.isNull(source) || Objects.isNull(target) || !this.graph.containsKey(source)
                || !this.graph.containsKey(target)) {
            return new ArrayList<>();
        }
        for (Set<N> valueSet : this.graph.values()) {
            if (valueSet.contains(target)) {
                isContained = true;
            }
        }
        if (!isContained) {
            return new ArrayList<>();
        }
        return result(source, target);
    }

    private List<N> result(N source, N target) {
        List<N> resultList = new ArrayList<>();
        if (this.graph.get(source).contains(target)) {
            resultList.addFirst(target);
            resultList.addFirst(source);
            return resultList;
        } else {
            for (N value : this.graph.get(source)) {
                List<N> list = (result(value, target));
                int size = list.size();
                if (size != 0) {
                    list.addFirst(source);
                    return list;
                }
            }
        }
        return resultList;
    }
}
