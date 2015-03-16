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

package fr.javatic.mongo.jacksonCodec.javaTime;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import fr.javatic.mongo.jacksonCodec.javaTime.deserializers.InstantDeserializer;
import fr.javatic.mongo.jacksonCodec.javaTime.deserializers.OffsetDateTimeDeserializer;
import fr.javatic.mongo.jacksonCodec.javaTime.deserializers.ZonedDateTimeDeserializer;
import fr.javatic.mongo.jacksonCodec.javaTime.serializers.InstantSerializer;
import fr.javatic.mongo.jacksonCodec.javaTime.serializers.OffsetDateTimeSerializer;
import fr.javatic.mongo.jacksonCodec.javaTime.serializers.ZonedDateTimeSerializer;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class JavaTimeBsonModule extends Module {
    @Override
    public String getModuleName() {
        return "JavaTimeBsonModule";
    }

    @Override
    public Version version() {
        return new Version(0, 1, 0, "", "", "");
    }

    @Override
    public void setupModule(SetupContext context) {
        context.addSerializers(new Java8TimeBsonSerializers());
        context.addDeserializers(new Java8TimeBsonDeserializers());
    }

    public static class Java8TimeBsonSerializers extends SimpleSerializers {
        public Java8TimeBsonSerializers() {
            addSerializer(Instant.class, new InstantSerializer());
            addSerializer(OffsetDateTime.class, new OffsetDateTimeSerializer());
            addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());
        }
    }

    public static class Java8TimeBsonDeserializers extends SimpleDeserializers {
        public Java8TimeBsonDeserializers() {
            addDeserializer(Instant.class, new InstantDeserializer());
            addDeserializer(OffsetDateTime.class, new OffsetDateTimeDeserializer());
            addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());
        }
    }
}
