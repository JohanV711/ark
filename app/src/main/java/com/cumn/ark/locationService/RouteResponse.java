package com.cumn.ark.locationService;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RouteResponse {
    @SerializedName("features")
    private List<Feature> features;

    public List<Feature> getFeatures() {
        return features;
    }
}

class Feature {
    @SerializedName("geometry")
    private Geometry geometry;

    public Geometry getGeometry() {
        return geometry;
    }
}

class Geometry {
    @SerializedName("coordinates")
    private List<List<Double>> coordinates;

    public List<List<Double>> getCoordinates() {
        return coordinates;
    }
}