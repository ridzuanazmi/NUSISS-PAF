package nusiss.paf.day28airbnb.Repository;

import org.bson.Document;
import org.springframework.core.convert.converter.Converter;

public class NumberDoubleToDoubleConverter implements Converter<Document, Double> {
    
    @Override
    public Double convert(Document source) {
        if (source == null) {
            return null;
        }

        Object value = source.get("$numberDouble");
        if (value instanceof String) {
            return Double.valueOf((String) value);
        }

        return null;
    }
}
