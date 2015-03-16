/*
 * Copyright 2015 Yann Le Moigne
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.javatic.mongo.jacksonCodec.javaTime.deserializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import de.undercouch.bson4jackson.BsonConstants;
import de.undercouch.bson4jackson.BsonParser;
import de.undercouch.bson4jackson.deserializers.BsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class ZonedDateTimeDeserializer extends BsonDeserializer<ZonedDateTime> {
    @Override
    public ZonedDateTime deserialize(BsonParser bsonParser, DeserializationContext ctxt) throws IOException,
        JsonProcessingException {
        if (bsonParser.getCurrentToken() != JsonToken.VALUE_EMBEDDED_OBJECT ||
            bsonParser.getCurrentBsonType() != BsonConstants.TYPE_DATETIME) {
            throw ctxt.mappingException(Date.class);
        }

        Object obj = bsonParser.getEmbeddedObject();
        if (obj == null) {
            return null;
        }

        Date dt = (Date) obj;
        return Instant.ofEpochMilli(dt.getTime()).atZone(ZoneId.of("UTC"));
    }
}
