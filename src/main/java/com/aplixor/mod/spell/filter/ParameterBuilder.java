package com.aplixor.mod.spell.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class ParameterBuilder {

    // name: String
    // radius: Integer
    // amount: Double

    List<Function<DataCarrier, ?>> methods = new ArrayList<>();

    public <S> void registerDataType(Function<DataCarrier, S> func) {

        Function<DataCarrier, Double> funky = (dataCarrier -> Double.valueOf(dataCarrier.get("hashmap")));
    };

    public <T1, T2> void apply(Function<T1, T2> dd,DataCarrier dataCarrier) {

        for (var method : methods) {
            var a = method.apply(dataCarrier);

        }

    }

    class ParameterInterior {
        public <T1> ParameterInterior(T1 a) {
            super();
        }
        public <T1, T2> ParameterInterior(T1 a, T2 b) {
            super();
        }
    };

    class DataCarrier extends HashMap<String, String> {};
}
