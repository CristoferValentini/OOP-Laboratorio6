package it.unibo.generics.graph.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
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
        if (source == null || target == null
                || !this.graph.containsKey(source)
                || !this.graph.containsKey(target)) {
            return Collections.emptyList();
        }
        Queue<N> queue = new LinkedList<>();
        Map<N, N> parentMap = new HashMap<>();

        queue.add(source);
        parentMap.put(source, null);

        while (!queue.isEmpty()) {
            N current = queue.poll();

            if (current.equals(target)) {
                break;
            }

            for (N neighbor : this.graph.get(current)) {
                if (!parentMap.containsKey(neighbor)) {
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        if (!parentMap.containsKey(target)) {
            return Collections.emptyList();
        }

        LinkedList<N> path = new LinkedList<>();
        for (N step = target; step != null; step = parentMap.get(step)) {
            path.addFirst(step);
        }

        return path;
    }
}
