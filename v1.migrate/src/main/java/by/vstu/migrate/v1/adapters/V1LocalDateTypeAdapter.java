package by.vstu.migrate.v1.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

public class V1LocalDateTypeAdapter extends TypeAdapter<LocalDate> {

    @Override
    public void write(final JsonWriter jsonWriter, final LocalDate localDate) throws IOException {
        if (localDate == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.value(localDate.toString());
    }

    @Override
    public LocalDate read(final JsonReader in) throws IOException {
        if (in.hasNext()) {
            String dateString = in.nextString();
            return LocalDate.parse(dateString);
        }
        return null;
    }
}